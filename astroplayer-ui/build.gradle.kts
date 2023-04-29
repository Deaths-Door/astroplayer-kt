plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.compose")
}

object MetaData {
    const val module = "astroplayer-ui"
    const val version = "0.1.0"
    const val namespace = "com.deathsdoor.astroplayer"
    const val description = "AstroPlayer is an open-source media player designed for the Kotlin Multiplatform. It provides a simple API for audio playback and supports multiple media formats while also providing an Jetpack Compose UI."
}

android {
    namespace = "com.deathsdoor.astroplayer"
    compileSdk = 33

    defaultConfig.minSdk = 21
    defaultConfig.targetSdk = 33

    compileOptions.sourceCompatibility = JavaVersion.VERSION_11
    compileOptions.targetCompatibility = JavaVersion.VERSION_11
}

kotlin {
    android()

    jvm("desktop")

    js(IR){
        browser()
        binaries.executable()
    }

    ios()

    cocoapods {
        summary = MetaData.description
        homepage = "https://www.google.com"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        framework {
            baseName = MetaData.module
        }
    }

    sourceSets {
        val commonMain by getting{
            dependencies {
                implementation(compose.ui)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.runtime)
                implementation(project(":astroplayer-core"))
            }
        }
    }
}