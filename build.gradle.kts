// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
}

// build.gradle.kts (Project-level)
buildscript {
    repositories {
        google() // Make sure this is present
        mavenCentral() // Make sure this is present
    }
    dependencies {
        // Required plugins with versions
        classpath("com.android.tools.build:gradle:8.0.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10") // Kotlin plugin version
        classpath("com.google.gms:google-services:4.3.10") // Google Services plugin version
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.1") // Firebase Crashlytics plugin version
    }
}
