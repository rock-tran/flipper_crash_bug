// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

    repositories {
        google()
        jcenter()
        gradlePluginPortal()
        mavenCentral()
    }

    apply(from = "buildPlugin/version.gradle.kts")

    val classPathDeps: Map<String, String> by rootProject.extra

    dependencies {
        classpath(classPathDeps.getValue("androidGradlePlugin"))
        classpath(classPathDeps.getValue("kotlinGradlePlugin"))
        classpath(classPathDeps.getValue("detektPlugin"))
        classpath(classPathDeps.getValue("versionChecker"))
        classpath(classPathDeps.getValue("sonarQubePlugin"))
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        gradlePluginPortal()
        mavenCentral()
    }
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}