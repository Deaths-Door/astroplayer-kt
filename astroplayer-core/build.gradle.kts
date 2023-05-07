plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("maven-publish")
}

object Metadata {
    val javaVersion = "11"
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
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        framework {
            baseName = "astroplayer-core"
        }
    }
    
    sourceSets {
        val commonMain by getting

        val androidMain by getting

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }

        val desktopMain by getting

        val jsMain by getting
    }
}

android {
    namespace = "com.deathsdoor.astroplayer"
    compileSdk = 33

    defaultConfig.minSdk = 21
    defaultConfig.targetSdk = 33

    val java =  JavaVersion.values().find { it.name.endsWith(Metadata.javaVersion) }

    compileOptions.sourceCompatibility = java
    compileOptions.targetCompatibility = java
}