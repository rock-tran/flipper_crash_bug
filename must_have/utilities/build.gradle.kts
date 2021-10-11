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

    api(KotlinDeps.JDK)
}