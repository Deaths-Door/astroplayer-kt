plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("maven-publish")
}

object Metadata {
    const val module = "astroplayer-ui"
    const val version = "0.1.1"
    const val namespace = "com.deathsdoor.astroplayer"
    const val description = "AstroPlayer is an open-source media player designed for the Kotlin Multiplatform. It provides a simple API for audio playback and supports multiple media formats while also providing an Jetpack Compose UI."
    const val repositoryURL = "https://github.com/Deaths-Door/AstroPlayer"
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
    android {
        publishLibraryVariants("release")
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }

    jvm("desktop"){
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }

    js(IR){
        browser()
        binaries.executable()
    }

    ios()

    cocoapods {
        summary = Metadata.description
        homepage = Metadata.repositoryURL
        version = Metadata.version
        ios.deploymentTarget = "14.1"
        framework {
            baseName = Metadata.module
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