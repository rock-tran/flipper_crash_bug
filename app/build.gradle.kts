plugins {
    id("com.android.application")
    id("manadr.dependencies")
    id("manadr.sonarqube.common")
    id("com.github.ben-manes.versions")
    kotlin("android")
    kotlin("kapt")
}

val VERSION_CODE = file("${project.rootDir}/release_info/version_code.txt").readText().toInt()
val VERSION_NAME = file("${project.rootDir}/release_info/version_name.txt").readText()

android {

    setupAndroidBasicConfigs()

    signingConfigs {
        create("debugs") {
            storeFile = file("keystore/debug.keystore")
            keyAlias = "androiddebugkey"
            keyPassword = "android"
            storePassword = "android"
        }
    }

    defaultConfig {
        applicationId = "com.base"
        versionCode = VERSION_CODE
        versionName = VERSION_NAME
        signingConfig = signingConfigs.getByName("debugs")
        multiDexEnabled = true

        addStringConfigField("GIT_REVISION", MaNaDrBuildPlugin.getRevision(project))
        addStringConfigField("GIT_BRANCH", MaNaDrBuildPlugin.getBranch(project))
        addStringConfigField("BUILD_TS", MaNaDrBuildPlugin.getBuildTimeStamp())
    }

    buildTypes {

        val pgFiles =
            fileTree("dir" to "proguards", "include" to listOf("*.pro")).files.toTypedArray()

        getByName("debug") {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("debugs")
        }

        getByName("release") {
            signingConfig = signingConfigs.getByName("debugs")
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(*pgFiles)
        }
    }

    lint {
        isCheckReleaseBuilds = false
        isCheckGeneratedSources = false
    }

    packagingOptions {
        resources.excludes.add("META-INF/core_release.kotlin_module")
    }
}

dependencies {
    implementation(fileTree("dir" to "libs", "include" to listOf("*.jar")))
    implementations(
        AndroidXDeps.DESIGN,
        //Fresco
        FrescoDeps.CORE,
        // Retrofit
        RetrofitDeps.CORE,
        RetrofitDeps.RX_ADAPTER,
        RetrofitDeps.GSON_CONVERTER,

        SingleLibs.OK_HTTP_LOG,
        //Stetho
        StethoDeps.CORE,
        StethoDeps.JS_RHINO,
        StethoDeps.OKHTTP3,

        SingleLibs.CHUCK
    )

    testImplementation(TestDeps.JUNIT)

    androidTestImplementation(AndroidXDeps.ANNOTATIONS)
    androidTestImplementation(TestDeps.RUNNER)

    kapt(AndroidXDeps.LifeCycle.COMPILER)

    // Configs for Dagger2
    kapt(DaggerDeps.ANDROID_PROCESSOR)
    kapt(ParcelerDeps.COMPILER)
    kapt(DaggerDeps.COMPILER)
    compileOnly("org.glassfish:javax.annotation:10.0-b28")

    implementation(project(":core"))
    implementation(project(":data"))

    // Only for debug
    debugImplementation(SingleLibs.LEAK_CANARY)
    debugImplementation(FlipperDeps.CORE)
    debugImplementation(FlipperDeps.NETWORK)
    debugImplementation(FlipperDeps.FRESCO)
    debugImplementation(FlipperDeps.SO_LOADER)
}

setupSonarQube {
    productFlavor = ""
    buildType = "debug"
}