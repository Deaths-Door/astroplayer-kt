plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("maven-publish")
}

object Metadata {
    const val module = "astroplayer-core"
    const val version = "0.1.1"
    const val namespace = "com.deathsdoor.astroplayer"
    const val description = "AstroPlayer is an open-source media player designed for the Kotlin Multiplatform. It provides a simple API for audio playback and supports multiple media formats while also providing an Jetpack Compose UI."
    const val repositoryURL = "https://github.com/Deaths-Door/AstroPlayer"
}

android {
    namespace = Metadata.namespace
    compileSdk = 33

    defaultConfig.minSdk = 21
    defaultConfig.targetSdk = 33

    compileOptions.sourceCompatibility = JavaVersion.VERSION_11
    compileOptions.targetCompatibility = JavaVersion.VERSION_11
}

kotlin {
    android {
        publishLibraryVariants("release")
    }

    jvm("desktop")

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
        val commonMain by getting
        val commonTest by getting

        val androidMain by getting {
            dependencies {
                implementation("androidx.media3:media3-exoplayer:1.0.1")
            }
        }

        val iosMain by getting

        val desktopMain by getting {
            dependencies {
                implementation("uk.co.caprica:vlcj:4.8.2")
            }
        }
        val desktopTest by getting

        val jsMain by getting {
            dependencies {
                implementation(npm("mediaelement", "4.2.16"))
            }
        }
        val jsTest by getting
    }
}
