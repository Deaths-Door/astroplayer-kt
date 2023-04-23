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
    val repoName = "AstroPlayer"
    val groupName = "com.deathsdoor.astroplayer"
    val currentVersion = "0.0.6"
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
            from(components["common"])
            groupId = groupName
            artifactId = "astroplayer-core"
            version = currentVersion
            pom {
                name.set(repoName)
                description.set("AstroPlayer is an open-source media player designed for the Kotlin Multiplatform Mobile (KMM) framework. It provides a simple API for audio playback and supports multiple media formats.")
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
			
        val commonMain by getting

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