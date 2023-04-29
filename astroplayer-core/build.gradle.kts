plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("maven-publish")
}

@Deprecated("")
object MetaData {
    const val module = "astroplayer-core"
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
        version = MetaData.version
        ios.deploymentTarget = "14.1"
        framework {
            baseName = MetaData.module
        }
    }

    sourceSets {
        val commonMain by getting
        val commonTest by getting

        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation("androidx.media3:media3-exoplayer:1.0.1")
            }
        }

        val iosMain by getting {
            dependsOn(commonMain)
        }

        val desktopMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation("uk.co.caprica:vlcj:4.8.2")
            }
        }
        val desktopTest by getting

        val jsMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(npm("mediaelement", "4.2.16"))
            }
        }
        val jsTest by getting
    }
}
