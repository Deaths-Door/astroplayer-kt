plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

repositories {
    google()
    mavenCentral()
    maven("https://maven.webjars.io") // Add the WebJars Maven repository
    maven("https://repo.maven.apache.org/maven2/org/openjfx") // for javafx-media repository
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
    android()
    jvm()
    js(IR){
        browser()
        nodejs()
        binaries.executable()
    }
    ios()
    iosX64()
    iosArm64()
    sourceSets {
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val androidMain by getting {
            dependencies {
                implementation("androidx.media3:media3-exoplayer:1.0.0")
            }
        }

        val iosMain by getting {
            dependencies {
           //     implementation("com.apple:MediaPlayer-framework:1.0.0")
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation("uk.co.caprica:vlcj:4.8.2")
            }
        }

        val jvmTest by getting {
            dependsOn(commonTest)
        }

        val jsMain by getting {
            dependencies {
                implementation(npm("mediaelement", "4.2.16"))
            }
        }

        val jsTest by getting {
            dependsOn(commonTest)
        }
    }
}