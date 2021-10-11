import com.android.build.gradle.BaseExtension
import com.android.build.gradle.api.AndroidSourceDirectorySet
import com.android.build.gradle.api.AndroidSourceSet
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.get
import org.gradle.testing.jacoco.plugins.JacocoPluginExtension
import org.gradle.testing.jacoco.tasks.JacocoReport
import org.sonarqube.gradle.SonarQubeExtension
import java.util.*

class SonarQubeBuildPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.plugins.apply("org.sonarqube")
        project.plugins.apply("jacoco")
        project.plugins.apply("io.gitlab.arturbosch.detekt")
    }
}

fun Project.setupSonarQube(clojure: MySonarQubeExt.() -> Unit) {
    val locale = Locale.ENGLISH
    val helper = SonarQubeHelper(
        this, MySonarQubeExt().apply(clojure)
    )
    helper.setup()
}

private fun String.capFirstChar(): String {
    val stringBuilder = StringBuilder(this)
    if (this.isEmpty()) return this
    val firstChar = get(0)
    val upperCaseChar = firstChar.toUpperCase()
    stringBuilder.setCharAt(0, upperCaseChar)
    return stringBuilder.toString()
}

private class SonarQubeHelper(
    private val project: Project,
    private val config: MySonarQubeExt
) {

    private val productFlavorFirstCap = config.productFlavor.capFirstChar()

    private val buildTypeFirstCap = config.buildType.capFirstChar()

    fun getVariant(): String {
        if (productFlavorFirstCap.isEmpty()) return config.buildType
        return "${config.productFlavor}$buildTypeFirstCap"
    }

    fun setup() {
        val sonarQubeExt = project.extensions.getByType(SonarQubeExtension::class.java)
        setupSonarQube().invoke(sonarQubeExt)
        val jacocoExt = project.extensions.getByType(JacocoPluginExtension::class.java)
        setupJacoco().invoke(jacocoExt)
        val androidExt = project.extensions.getByType(BaseExtension::class.java)
        setupAppAndroid().invoke(androidExt)
        val detektExt = project.extensions.getByType(DetektExtension::class.java)
        setupDetekt(project).invoke(detektExt)
    }

    private fun setupAppAndroid(): BaseExtension.() -> Unit = {
        testOptions {
            unitTests {
                isIncludeAndroidResources = true
                isReturnDefaultValues = true
            }
        }
    }

    private fun setupJacoco(): JacocoPluginExtension.() -> Unit = {
        val fileFilter = listOf(
            "**/*Parcel.class",
            "**/*\$CREATOR.class",
            "**/*Test*.*",
            "**/AutoValue_*.*",
            "**/*JavascriptBridge.class",
            "**/R.class",
            "**/R$*.class",
            "**/Manifest*.*",
            "android/**/*.*",
            "**/BuildConfig.*",
            "**/*\$ViewBinder*.*",
            "**/*\$ViewInjector*.*",
            "**/Lambda$*.class",
            "**/Lambda.class",
            "**/*Lambda.class",
            "**/*Lambda*.class",
            "**/*\$InjectAdapter.class",
            "**/*\$ModuleAdapter.class",
            "**/*\$ViewInjector*.class",
            "**/*_MembersInjector.class", //Dagger2 generated code
            "*/*_MembersInjector*.*", //Dagger2 generated code
            "**/*_*Factory*.*", //Dagger2 generated code
            "**/*Component*.*", //Dagger2 generated code
            "**/*Module*.*" //Dagger2 generated code
        )
        val buildDir = project.buildDir

        val debugTree = project.fileTree(
            mapOf(
                "dir" to "${buildDir}/intermediates/classes/javac/${getVariant()}",
                "excludes" to fileFilter
            )
        )
        val mainSrc = "${project.projectDir}/src/main/java"
        val kotlinDebugTree = project.fileTree(
            mapOf(
                "dir" to "${buildDir}/tmp/kotlin-classes/${getVariant()}",
                "excludes" to fileFilter
            )
        )

        val jacocoVersion = "0.8.7"
        toolVersion = jacocoVersion
        project.tasks.register("jacocoTestReport", JacocoReport::class.java) {
            dependsOn("test$productFlavorFirstCap${buildTypeFirstCap}UnitTest")
            group = "Reporting"
            description = "Generating Jacoco coverage reports"

            reports {
                xml.isEnabled = true
                html.isEnabled = true
                csv.isEnabled = true
                xml.destination = project.file("${buildDir}/reports/jacocoTestReport.xml")
                html.destination = project.file("${buildDir}/reports/jacoco")
                csv.destination = project.file("${buildDir}/reports/jacocoTestReport.csv")
            }

            sourceDirectories.setFrom(project.files(mainSrc))
            classDirectories.setFrom(project.files(debugTree, kotlinDebugTree))
            executionData.setFrom(
                project.fileTree(
                    mapOf(
                        "dir" to buildDir,
                        "includes" to listOf("**/*.exec", " **/*.ec")
                    )
                )
            )
        }
    }

    private fun setupSonarQube(): SonarQubeExtension.() -> Unit = {
        project.tasks["sonarqube"].dependsOn("jacocoTestReport")
        project.tasks["sonarqube"].dependsOn("lint$productFlavorFirstCap$buildTypeFirstCap")

        androidVariant = getVariant()

        properties {
            val androidExt = project.extensions.getByType(BaseExtension::class.java)
            val androidSDK = androidExt.sdkDirectory.path + "/platforms/android-30/android.jar"

            val allJavaSource = getAllJavaSourceSets()
            val allResSource = androidExt.getSourceSets(*getAllSourceSetText()) { res.getAllDirs() }
            val allManifestFiles = androidExt.getSourceSets(*getAllSourceSetText()) {
                val manifestFile = manifest.srcFile
                if (manifestFile.exists()) listOf(manifest.srcFile.parent) else listOf("")
            }
            val allSources = allJavaSource.toMutableList().apply {
                addAll(allResSource)
                addAll(allManifestFiles)
                distinct()
            }.removeWrapperString()

            val allTestJavaSource = androidExt.getSourceSets("test") { java.getAllDirs() }

            val hasTestSource = allTestJavaSource.isNotEmpty()
            if (hasTestSource) {
                property("sonar.tests", allTestJavaSource.joinToString())
                property(
                    "sonar.coverage.jacoco.xmlReportPaths",
                    "build/reports/jacocoTestReport.xml"
                )
            }

            property("sonar.libraries", androidSDK)

            property("sonar.verbose", true)

            property("sonar.issuesReport.html.enable", "true")
            property("sonar.issuesReport.console.enable", "true")

            property("sonar.kotlin.detekt.reportPaths", "build/reports/detekt/detekt.xml")

            property("sonar.sources", allSources.joinToString())
            property("sonar.exclusions", "**/*.png,**/*.jpg,**/*.gif,**/*.mp3,**/*.wav")

            property("sonar.java.coveragePlugin", "jacoco")
            property(
                "sonar.java.binaries",
                "build/intermediates/javac/${getVariant()}," + "build/tmp/kotlin-classes"
            )

            property(
                "sonar.androidLint.reportPaths",
                "build/reports/lint-results-${getVariant()}.xml"
            )
            property("sonar.sourceEncoding", "UTF-8")
            property("sonar.import_unknown_files", true)
        }
    }

    @Suppress("UnstableApiUsage")
    private fun BaseExtension.getSourceSets(
        vararg types: String,
        clojure: AndroidSourceSet.() -> List<String>
    ): List<String> {
        val allSourceSets = mutableListOf<String>()

        for (type in types) {
            if (type.isEmpty()) continue

            val sourceSet = try {
                clojure.invoke(sourceSets.getByName(type))
            } catch (e: Exception) {
                null
            } ?: continue

            allSourceSets.addAll(sourceSet)
        }

        return allSourceSets.distinct().filter { it.isNotEmpty() }
    }

    private fun getAllJavaSourceSets(): List<String> {
        val androidExt = project.extensions.getByType(BaseExtension::class.java)

        return androidExt.getSourceSets(*getAllSourceSetText()) { java.getAllDirs() }
    }

    fun AndroidSourceDirectorySet.getAllDirs(): List<String> {
        val results = mutableListOf<String>()
        getSourceDirectoryTrees().forEach {
            if (it.dir.exists()) results.add(it.dir.absolutePath)
        }
        return results
    }

    private fun getAllSourceSetText() = arrayOf(
        "main",
        config.buildType,
        config.productFlavor,
        getVariant()
    )

    private fun List<String>.removeWrapperString(): List<String> {
        val childItems = filter { it.isAWrapperStringOfAny(this) }
        return toMutableList().apply { removeAll(childItems) }
    }

    private fun String.isAWrapperStringOfAny(list: List<String>): Boolean {
        for (item in list) {
            if (item != this && this.contains(item, true)) return true
        }

        return false
    }

    private fun setupDetekt(project: Project): DetektExtension.() -> Unit = {
        toolVersion = "1.17.1"
        ignoredBuildTypes = listOf("release")
        val allJavaSourceDirs = getAllJavaSourceSets()
        input = project.files(*allJavaSourceDirs.toTypedArray())
        val rootProject = project.rootProject
        val configFile = rootProject.file("buildPlugin/config/detekt-config.yml")
        config = rootProject.files(configFile)
        autoCorrect = true
        failFast = false
        debug = false
    }
}