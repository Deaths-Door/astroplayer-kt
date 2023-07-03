plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")

    kotlin("plugin.serialization")
}

object Metadata {
    const val javaVersion = "11"
    val asJavaVersionEnum = JavaVersion.values().find { it.name.endsWith(javaVersion) }
    const val minSDK = 21
    const val maxSDK = 33

    const val iosDeploymentTarget = "14.1"

    const val namespace = "com.deathsdoor.astroplayer.ui"
    const val module = "astroplayer-core"
    const val version = "1.2.0"
    const val description = "AstroPlayer is an open-source media player designed for the Kotlin Multiplatform. It provides a simple API for audio playback and supports multiple media formats while also providing an Jetpack Compose UI."
    const val url = "https://github.com/Deaths-Door/AstroPlayer"
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = Metadata.javaVersion
            }
        }
    }

    jvm("desktop"){
        compilations.all {
            kotlinOptions {
                jvmTarget = Metadata.javaVersion
            }
        }
    }

    js(IR){
        browser()
        binaries.executable()
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = Metadata.description
        homepage = Metadata.url
        version = Metadata.version
        ios.deploymentTarget = Metadata.iosDeploymentTarget
        framework {
            baseName = Metadata.module
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                listOf(":uuid",":uri").forEach { implementation(project(it)) }

                //For Serialization / Deserialization
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")

            }
        }

        val androidMain by getting {
            dependencies {
                implementation("androidx.media3:media3-exoplayer:1.0.2")
            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }

        val desktopMain by getting {
            dependencies {
                listOf("media","base").forEach {
                    implementation(files("libs/javafx-$it-20-win.jar"))
                }
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(npm("mediaelement", "4.2.16"))
            }
        }
    }
}

android {
    namespace = Metadata.namespace
    compileSdk = Metadata.maxSDK

    defaultConfig.minSdk = Metadata.minSDK
    defaultConfig.targetSdk = Metadata.maxSDK

    compileOptions.sourceCompatibility = Metadata.asJavaVersionEnum
    compileOptions.targetCompatibility = Metadata.asJavaVersionEnum
}