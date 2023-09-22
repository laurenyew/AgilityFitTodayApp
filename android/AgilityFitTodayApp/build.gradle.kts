

// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.1.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.22")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${DependencyInjection.hiltVersion}")
        classpath("org.jlleitschuh.gradle:ktlint-gradle:11.3.2")
        classpath("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.23.1")
    }
}

plugins {
    id("io.gitlab.arturbosch.detekt") version "1.23.1"
}

allprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(plugin = "io.gitlab.arturbosch.detekt")

    repositories {
        google()
        mavenCentral()
    }

    // Optionally configure plugin
    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        debug.set(true)
        outputToConsole.set(true)
        outputColorName.set("RED")
        ignoreFailures.set(false)
    }
}

detekt {
    parallel = true
    debug = true
}
