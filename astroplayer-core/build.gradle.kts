plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")    
    id("maven-publish")
}

repositories {
    google()
    mavenCentral()
}

val module = "astroplayer-core"
val description = "AstroPlayer is an open-source media player designed for the Kotlin Multiplatform Mobile (KMM) framework. It provides a simple API for audio playback and supports multiple media formats."

publishing {
    val repoName = "AstroPlayer"
    val groupName = "com.deathsdoor.astroplayer"
    val currentVersion = "0.1.0"
    val repoURL = "https://github.com/Deaths-Door/AstroPlayer"
    repositories {
        maven {
            url = uri(repoURL)
            name = repoName
            group = groupName
            version = currentVersion
        }
    }
    publications {
        register("maven", MavenPublication::class) {
            groupId = groupName
            artifactId = module
            version = currentVersion
            pom {
                name.set(repoName)
                description.set(description)
                url.set(repoURL)
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
            }
        }
    }
}

android {
    namespace = "com.deathsdoor.astroplayer"
    compileSdk = 33
    defaultConfig {
        minSdk = 21
        targetSdk = 32
    }
}

kotlin {
    android {
        publishLibraryVariants("release", "debug")
    }
    jvm("desktop")
    js(IR){
        browser()
        nodejs()
        binaries.executable()
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = description
        homepage = "https://www.google.comm"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        framework {
            baseName = module
        }
    }

    sourceSets {
        val commonMain by getting
        val commonTest by getting

        val androidMain by getting {
            dependencies {
                implementation("androidx.media3:media3-exoplayer:1.0.0")
            }
        }
        val androidTest by getting

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }

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
