import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.VariantDimension
import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*

class MaNaDrBuildPlugin : Plugin<Project> {

    override fun apply(target: Project) {

    }

    companion object {

        fun getBranch(project: Project): String {
            return getOutputOfCommand("git rev-parse --symbolic-full-name HEAD", project)
        }

        fun getRevision(project: Project): String {
            return getOutputOfCommand("git rev-parse HEAD", project)
        }

        fun getSubmodule(project: Project): String {
            val output = getOutputOfCommand("git submodule", project)
            val result = output.replace("\n", "\\\\n")
            print("ToanTK getSubmodule $result")
            return result
        }

        fun getBuildTimeStamp(): String {
            val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z", Locale.ENGLISH).apply {
                timeZone = TimeZone.getTimeZone("Asia/Ho_Chi_Minh")
            }

            return df.format(Date())
        }

        private fun getOutputOfCommand(fullCommandLine: String, project: Project): String {
            val byteOut = ByteArrayOutputStream()

            project.rootProject.exec {
                commandLine = fullCommandLine.split(" ")
                standardOutput = byteOut
            }

            return byteOut.toString().trim()
        }
    }
}

object AndroidBuildDeps {

    const val MIN_SDK = 21

    const val TARGET_SDK = 30

    const val COMPILE_SDK = 30

    const val BUILD_TOOLS = "30.0.3"

}

object KotlinDeps {

    const val JDK = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.4.32"

}

object AndroidXDeps {

    const val CORE_KTX = "androidx.core:core-ktx:1.3.2"

    const val ANNOTATIONS = "androidx.annotation:annotation:1.1.0"

    const val APP_COMPAT = "androidx.appcompat:appcompat:1.2.0"

    const val RECYCLER_VIEW = "androidx.recyclerview:recyclerview:1.1.0"

    const val CARD_VIEW = "androidx.cardview:cardview:1.0.0"

    const val DESIGN = "com.google.android.material:material:1.3.0"

    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:2.0.4"

    const val VIEW_PAGER_2 = "androidx.viewpager2:viewpager2:1.0.0"

    object LifeCycle {

        const val EXTENSION = "androidx.lifecycle:lifecycle-extensions:2.2.0"

        const val COMPILER = "androidx.lifecycle:lifecycle-compiler:2.3.1"

    }

    object Room {

        private const val VERSION_ROOM = "2.3.0"

        const val RUNTIME = "androidx.room:room-runtime:${VERSION_ROOM}"

        const val COMPILER = "androidx.room:room-compiler:${VERSION_ROOM}"

    }
}

object RxDeps {

    const val JAVA = "io.reactivex.rxjava3:rxjava:3.0.12"

    const val KOTLIN = "io.reactivex.rxjava3:rxkotlin:3.0.1"

    const val ANDROID = "io.reactivex.rxjava3:rxandroid:3.0.0"

}

object DaggerDeps {

    private const val VERSION_DAGGER = "2.38.1"

    const val CORE = "com.google.dagger:dagger:$VERSION_DAGGER"

    const val COMPILER = "com.google.dagger:dagger-compiler:$VERSION_DAGGER"

    const val ANDROID = "com.google.dagger:dagger-android:$VERSION_DAGGER"

    const val ANDROID_SUPPORT = "com.google.dagger:dagger-android-support:$VERSION_DAGGER"

    const val ANDROID_PROCESSOR = "com.google.dagger:dagger-android-processor:$VERSION_DAGGER"

}

fun DependencyHandlerScope.daggerCompiler() {
    "kapt"(DaggerDeps.COMPILER)
    "kapt"(DaggerDeps.ANDROID_PROCESSOR)
}

object TestDeps {

    const val EXT_JUNIT = "androidx.test.ext:junit:1.1.1"

    const val JUNIT = "junit:junit:4.13.2"

    const val RUNNER = "androidx.test:runner:1.4.0"

    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:3.2.0"

}

fun DependencyHandlerScope.setUpTestDeps() {
    "testImplementation"(TestDeps.JUNIT)
    "androidTestImplementation"(TestDeps.RUNNER)
    "androidTestImplementation"(TestDeps.ESPRESSO_CORE)
    "androidTestImplementation"(TestDeps.EXT_JUNIT)
}

object RetrofitDeps {

    private const val VERSION_RETROFIT = "2.9.0"

    const val CORE = "com.squareup.retrofit2:retrofit:$VERSION_RETROFIT"

    const val RX_ADAPTER = "com.squareup.retrofit2:adapter-rxjava3:$VERSION_RETROFIT"

    const val RX_2_ADAPTER = "com.squareup.retrofit2:adapter-rxjava2:$VERSION_RETROFIT"

    const val GSON_CONVERTER = "com.squareup.retrofit2:converter-gson:$VERSION_RETROFIT"

}

object FrescoDeps {

    private const val VERSION_FRESCO = "2.5.0"

    const val CORE = "com.facebook.fresco:fresco:$VERSION_FRESCO"

    const val ANIMATED_GIF = "com.facebook.fresco:animated-gif:$VERSION_FRESCO"

    const val ANIMATED_WEBP = "com.facebook.fresco:animated-webp:$VERSION_FRESCO"

    const val WEBP_SUPPORT = "com.facebook.fresco:webpsupport:$VERSION_FRESCO"

    const val IMAGE_PIPELINE_OKHTTP_3 = "com.facebook.fresco:imagepipeline-okhttp3:$VERSION_FRESCO"

    const val PROCESSORS = "jp.wasabeef:fresco-processors:2.1.0"

}

object StethoDeps {

    private const val VERSION_STETHO = "1.6.0"

    const val CORE = "com.facebook.stetho:stetho:$VERSION_STETHO"

    const val JS_RHINO = "com.facebook.stetho:stetho-js-rhino:$VERSION_STETHO"

    const val OKHTTP3 = "com.facebook.stetho:stetho-okhttp3:$VERSION_STETHO"

    const val REALM = "com.uphyca:stetho_realm:2.3.0"
}

object FlipperDeps {

    private const val VERSION_FLIPPER = "0.113.0"

    const val SO_LOADER = "com.facebook.soloader:soloader:0.10.1"

    const val CORE = "com.facebook.flipper:flipper:$VERSION_FLIPPER"

    const val NETWORK = "com.facebook.flipper:flipper-network-plugin:$VERSION_FLIPPER"

    const val FRESCO = "com.facebook.flipper:flipper-fresco-plugin:$VERSION_FLIPPER"
}

object DebugDeps {

    val RECOMMEND_LIBS = setOf(
        StethoDeps.CORE,
        StethoDeps.OKHTTP3,
        FlipperDeps.SO_LOADER,
        FlipperDeps.CORE,
        FlipperDeps.NETWORK,
        FlipperDeps.FRESCO,
        SingleLibs.CHUCK
    )
}

fun DependencyHandlerScope.setupFirebase() {
    "implementation"(platform(FirebaseDeps.BOM))
    "implementation"(FirebaseDeps.CRASHLYTICS)
    "implementation"(FirebaseDeps.PERFORMANCE)
}

fun DependencyHandlerScope.implementations(vararg libs: String) {
    libs.forEach { "implementation"(it) }
}

fun DependencyHandlerScope.setupAirBnbDeepLink() {
    val version = "5.3.0"
    "implementation"("com.airbnb:deeplinkdispatch:$version")
    "kapt"("com.airbnb:deeplinkdispatch-processor:$version")
}

fun DependencyHandlerScope.setupDevelopmentLibs(vararg configs: String) {
    configs.forEach { config ->
        DebugDeps.RECOMMEND_LIBS.forEach { debugLib ->
            config(debugLib)
        }
    }
}

object SingleLibs {

    const val CHUCK = "com.github.chuckerteam.chucker:library:3.5.2"

    const val OK_HTTP_LOG = "com.squareup.okhttp3:logging-interceptor:4.9.1"

    const val GSON = "com.google.code.gson:gson:2.8.6"

    const val ANNOTATION_JSR_250 = "javax.annotation:jsr250-api:1.0"

    const val LEAK_CANARY = "com.squareup.leakcanary:leakcanary-android:2.7"

    const val EASY_IMAGE = "com.github.jkwiecien:EasyImage:1.2.1"

    const val NAMMU = "com.github.tajchert:nammu:1.2.0"

    const val MATERIAL_CALENDAR = "com.prolificinteractive:material-calendarview:1.4.3"

    const val MATERIAL_CALENDAR_1 = "com.github.prolificinteractive:material-calendarview:2.0.1"

    const val WEEK_VIEW = "com.github.alamkanak:android-week-view:1.2.6"

    const val BOTTOM_NAVIGATION = "com.aurelhubert:ahbottomnavigation:2.1.0"

    const val MULTI_VIEWPAGER = "com.pixplicity.multiviewpager:library:1.0"

    const val CROP_IMAGE = "com.soundcloud.android:android-crop:1.0.1@aar"

    const val STRIPE = "com.stripe:stripe-android:8.0.0"

    const val BLUR_VIEW = "com.github.mmin18:realtimeblurview:1.1.2"

    const val TIME_AGO = "com.github.curioustechizen.android-ago:library:1.4.0"

    const val SHORTCUT_BADGER = "me.leolin:ShortcutBadger:1.1.22@aar"

    const val IO_CARD = "io.card:android-sdk:5.5.1"

    const val GIF_DRAWABLE = "pl.droidsonroids.gif:android-gif-drawable:1.2.16"

    const val EDMODO_IMAGE_CROPPER = "com.theartofdev.edmodo:android-image-cropper:2.7.0"

    const val SCROLL_LIKE_IOS = "me.everything:overscroll-decor-android:1.1.0"

    const val IMAGE_ZOOM = "it.sephiroth.android.library.imagezoom:imagezoom:2.3.0"

    const val LOCATION_PICKER = "com.schibstedspain.android:leku:8.0.0"

    const val BRANCH_SDK = "io.branch.sdk.android:library:4.1.2"

    const val EASY_PERMISSIONS = "pub.devrel:easypermissions:2.0.0"

    const val APMEM_FLOW_LAYOUT = "org.apmem.tools:layouts:1.10@aar"

    const val PDF_VIEWER = "com.github.barteksc:android-pdf-viewer:2.8.2"

    const val JWT = "com.auth0:java-jwt:3.4.0"

    const val TOOLTIP_BALLOON = "com.github.skydoves:balloon:1.2.5"

    const val MP_CHART = "com.github.PhilJay:MPAndroidChart:v3.1.0"

    const val FILE_PICKER = "com.droidninja:filepicker:2.2.4"

    const val AUTO_SCROLL_VIEWPAGER = "com.github.pzienowicz:androidx-auto-scroll-view-pager:1.1.0"

    const val MATERIAL_DATE_PICKER = "com.wdullaer:materialdatetimepicker:4.2.3"

    const val PUBNUB_GSON = "com.pubnub:pubnub-gson:4.36.0"

    const val CIRCULAR_PROGRESS = "com.github.rahatarmanahmed:circularprogressview:2.5.0"

    const val IMAGE_GALLERY = "com.github.lawloretienne:imagegallery:0.1.0"

    const val PHOTO_DRAWEE_VIEW = "me.relex:photodraweeview:2.0.0"

    const val CIRCLE_INDICATOR = "me.relex:circleindicator:2.1.4"

    const val COMMONS_LANG = "org.apache.commons:commons-lang3:3.11"

    const val COMMONS_IO = "commons-io:commons-io:2.8.0"

    const val REALM_ADAPTER = "io.realm:android-adapters:4.0.0"

    const val EVENT_BUS = "org.greenrobot:eventbus:3.1.1"

    const val SHIMMER = "com.facebook.shimmer:shimmer:0.5.0"

    const val TIMBER = "com.jakewharton.timber:timber:4.7.1"

    const val MATERIAL_DIALOGS = "com.afollestad.material-dialogs:core:0.9.6.0"

    const val SLF4J = "org.slf4j:slf4j-api:1.7.32"

    const val ADVANCED_RECYCLER_VIEW =
        "com.h6ah4i.android.widget.advrecyclerview:advrecyclerview:1.0.0@aar"

    const val EASY_DEVICE_INFO = "com.github.nisrulz:easydeviceinfo:2.4.1"

    const val MATERIALISH_PROGRESS = "com.pnikosis:materialish-progress:1.7"

    const val MATERIAL_PROGRESS_BAR = "me.zhanghai.android.materialprogressbar:library:1.4.1"

    const val COMMON_MARK = "com.atlassian.commonmark:commonmark:0.9.0"

    const val ANIM_BACKGROUND = "com.wang.avi:library:2.1.3"

    const val NEX3Z_FLOW_LAYOUT = "com.nex3z:flow-layout:1.2.2"

    const val VIEW_PAGER_INDICATOR = "com.romandanylyk:pageindicatorview:0.2.0"

    const val QR_CODE_GENERATOR = "com.bottlerocketstudios:barcode:1.0.3@aar"

    const val E_SIGNATURE = "com.simplify:ink:1.0.2"

    const val NUMBER_PICKER = "com.shawnlin:number-picker:2.4.6"

    const val SQUARE_CALENDAR = "com.squareup:android-times-square:1.7.10"

    const val FLEX_BOX = "com.google.android:flexbox:2.0.1"

    const val DRAWABLE_TOOLBOX = "com.github.duanhong169:drawabletoolbox:1.0.7"

    const val GG_LIB_PHONE_NUMBER = "com.googlecode.libphonenumber:libphonenumber:8.12.14"
}

object GoogleAPIsDeps {

    const val API_CLIENT = "com.google.api-client:google-api-client:1.31.1"
    const val API_CLIENT_ANDROID = "com.google.api-client:google-api-client-android:1.31.1"

}

object MLKitDeps {

    const val BAR_CODE = "com.google.mlkit:barcode-scanning:16.1.1"

}

object LogBackDeps {
    const val CORE = "com.github.tony19:logback-android:2.0.0"
}

object GlideDeps {

    private const val VERSION_GLIDE = "4.11.0"

    const val GLIDE = "com.github.bumptech.glide:glide:$VERSION_GLIDE"

    const val COMPILER = "com.github.bumptech.glide:compiler:$VERSION_GLIDE"

}

object FirebaseDeps {

    const val BOM = "com.google.firebase:firebase-bom:27.1.0"

    const val FCM = "com.google.firebase:firebase-messaging"

    const val ANALYTICS = "com.google.firebase:firebase-analytics-ktx"

    const val CRASHLYTICS = "com.google.firebase:firebase-crashlytics"

    const val PERFORMANCE = "com.google.firebase:firebase-perf"

    const val REMOTE_CONFIG = "com.google.firebase:firebase-config"

    const val JOB_DISPATCHER = "com.firebase:firebase-jobdispatcher:0.8.5"

    const val DYNAMIC_LINKS = "com.google.firebase:firebase-dynamic-links"

}

object ZxingDeps {

    const val CORE = "com.google.zxing:core:3.4.1"

    const val ANDROID_CORE = "com.google.zxing:android-core:3.3.0"

}

object RealmDeps {

    const val VERSION_REALM = "7.0.8"

    const val ANNOTATIONS = "io.realm:realm-annotations:$VERSION_REALM"

    const val ANNOTATIONS_PROCESSOR = "io.realm:realm-annotations-processor:$VERSION_REALM"

}

object ParcelerDeps {

    private const val VERSION_PARCELER = "1.1.13"

    const val CORE = "org.parceler:parceler-api:$VERSION_PARCELER"

    const val COMPILER = "org.parceler:parceler:$VERSION_PARCELER"
}

fun DependencyHandlerScope.realmCompiler() {
    "kapt"(RealmDeps.ANNOTATIONS)
    "kapt"(RealmDeps.ANNOTATIONS_PROCESSOR)
}

fun DependencyHandlerScope.jettfierCompiler() {
    "kapt"("com.android.tools.build.jetifier:jetifier-core:1.0.0-beta10")
    "annotationProcessor"("com.android.tools.build.jetifier:jetifier-core:1.0.0-beta10")
}

fun DependencyHandlerScope.setupButterKnife() {
    val verionButterKnife = "10.0.0"
    "implementation"("com.jakewharton:butterknife:$verionButterKnife")
    "kapt"("com.jakewharton:butterknife-compiler:$verionButterKnife")
}

fun VariantDimension.addStringConstant(key: String, value: String) {
    addManifestPlaceholders(mapOf(key to value))
    buildConfigField("String", key, "\"$value\"")
}

fun VariantDimension.addStringConfigField(key: String, value: String?) {
    buildConfigField("String", key, "\"$value\"")
}

fun BaseExtension.setupAndroidBasicConfigs() {

    compileSdkVersion(AndroidBuildDeps.COMPILE_SDK)
    buildToolsVersion(AndroidBuildDeps.BUILD_TOOLS)

    defaultConfig {
        minSdk = AndroidBuildDeps.MIN_SDK
        targetSdk = AndroidBuildDeps.TARGET_SDK
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    if (this is CommonExtension<*, *, *, *>) {
        buildFeatures {
            viewBinding = true
        }

        lint {
            isAbortOnError = false
        }
    }

    customKotlinOptions {
        jvmTarget = "11"
    }
}

fun BaseExtension.customKotlinOptions(configure: KotlinJvmOptions.() -> Unit) {
    val kotlinOptions = (this as ExtensionAware).extensions.findByName("kotlinOptions")
    if (kotlinOptions !is KotlinJvmOptions) return
    configure.invoke(kotlinOptions)
}


object ExoPlayer {
    const val CORE = "com.google.android.exoplayer:exoplayer-core:2.13.3"
    const val UI = "com.google.android.exoplayer:exoplayer-ui:2.13.3"
}