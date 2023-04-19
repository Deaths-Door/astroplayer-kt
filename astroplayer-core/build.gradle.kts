plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("maven-publish")
}

repositories {
    google()
    mavenCentral()
    //TODO maybe remove it cuz maybe not be useful anymore
    maven("https://maven.webjars.io") // Add the WebJars Maven repository
   // maven("https://repo.maven.apache.org/maven2/org/openjfx") // for javafx-media repository
}

publishing {
    repositories {
        maven {
            url = uri("https://github.com/Deaths-Door/AstroPlayer")
            name = "AstroPlayer"
            group = "com.deathsdoor.astroplayer"
            version = "0.1.0"
        }
    }
    publications {
        register("maven", MavenPublication::class) {
            from(components["kotlin"])
            groupId = "com.deathsdoor.astroplayer"
            artifactId = "astroplayer"
            version = "0.1.0"
            pom {
                name.set("AstroPlayer")
                description.set("AstroPlayer is an open-source media player designed for the Kotlin Multiplatform Mobile (KMM) framework. It provides a simple API for audio playback and supports multiple media formats.")
                url.set("https://github.com/Deaths-Door/AstroPlayer")
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
    android()
    jvm()
    js(IR){
        browser()
        nodejs()
        binaries.executable()
    }
    /*
    ios()
    iosX64()
    iosArm64()
    */
    sourceSets {
       /* val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }*/

        val androidMain by getting {
            dependencies {
                implementation("androidx.media3:media3-exoplayer:1.0.0")
            }
        }

       /* val iosMain by getting {
            dependencies {
           //     implementation("com.apple:MediaPlayer-framework:1.0.0")
            }
        }*/

        val jvmMain by getting {
            dependencies {
                implementation("uk.co.caprica:vlcj:4.8.2")
            }
        }

        /*val jvmTest by getting {
            dependsOn(commonTest)
        }*/

        val jsMain by getting {
            dependencies {
                implementation(npm("mediaelement", "4.2.16"))
            }
        }

      /*  val jsTest by getting {
            dependsOn(commonTest)
        }*/
    }
}