// Top-level build file where you can add configuration options common to all sub-projects/modules.

apply(from = "version.gradle.kts")

val classPathDeps: Map<String, String> by rootProject.extra

repositories {
    google()
    jcenter()
    gradlePluginPortal()
}

plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
}

group = "com.manadr.dependencies"
version = "SNAPSHOT"

dependencies {
    compileOnly(classPathDeps.getValue("androidGradlePlugin"))
    compileOnly(gradleApi())
    compileOnly(classPathDeps.getValue("kotlinGradlePlugin"))
    compileOnly(classPathDeps.getValue("sonarQubePlugin"))
    compileOnly(classPathDeps.getValue("detektPlugin"))
}

gradlePlugin {
    plugins {
        create("simplePlugin") {
            id = "manadr.dependencies"
            implementationClass = "MaNaDrBuildPlugin"
        }

        create("sonarQubeCommon") {
            id = "manadr.sonarqube.common"
            implementationClass = "SonarQubeBuildPlugin"
        }
    }
}