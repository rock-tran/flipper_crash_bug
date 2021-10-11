val classPathDeps = mapOf(
    "androidGradlePlugin" to "com.android.tools.build:gradle:7.0.2",
    "kotlinGradlePlugin" to "org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.32",
    "googleServicePlugin" to "com.google.gms:google-services:4.3.10",
    "crashlyticsPlugin" to "com.google.firebase:firebase-crashlytics-gradle:2.7.1",
    "perfPlugin" to "com.google.firebase:perf-plugin:1.4.0",
    "sonarQubePlugin" to "org.sonarsource.scanner.gradle:sonarqube-gradle-plugin:3.2.0",
    "detektPlugin" to "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.18.0",
    "versionChecker" to "com.github.ben-manes:gradle-versions-plugin:0.39.0"
)

rootProject.extra.set("classPathDeps", classPathDeps)