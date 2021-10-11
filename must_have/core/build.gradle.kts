plugins {
    id("com.android.library")
    id("manadr.dependencies")
    kotlin("android")
    kotlin("kapt")
}

android {
    setupAndroidBasicConfigs()

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(fileTree("dir" to "libs", "include" to listOf("*.jar")))
    testImplementation(TestDeps.JUNIT)

    api(AndroidXDeps.RECYCLER_VIEW)
    api(AndroidXDeps.APP_COMPAT)
    api(AndroidXDeps.CONSTRAINT_LAYOUT)
    api(AndroidXDeps.LifeCycle.EXTENSION)
    kapt(AndroidXDeps.LifeCycle.COMPILER)

    api(RxDeps.KOTLIN)
    api(RxDeps.ANDROID)

    api(DaggerDeps.CORE)
    api(DaggerDeps.ANDROID)
    api(DaggerDeps.ANDROID_SUPPORT)

    api(SingleLibs.SLF4J)
    api(LogBackDeps.CORE)

    api(project(":utilities"))
}
