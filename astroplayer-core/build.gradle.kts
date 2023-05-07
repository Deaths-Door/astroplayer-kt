plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("com.vanniktech.maven.publish") version "0.25.2"
}

object Metadata {
    const val javaVersion = "11"
    const val iosDeploymentTarget = "14.1"

    const val namespace = "com.deathsdoor.astroplayer"
    const val module = "astroplayer-core"
    const val version = "1.2.0"
    const val description = ""
    const val inceptionYear = "2023"
    const val url = "https://github.com/Deaths-Door/AstroPlayer"
    object License {
        const val name = "The Apache License, Version 2.0"
        const val url = "http://www.apache.org/licenses/LICENSE-2.0.txt"
        const val distribution = "http://www.apache.org/licenses/LICENSE-2.0.txt"
    }
    //const val
}

mavenPublishing {
    publishToMavenCentral()
    signAllPublications()
    coordinates(Metadata.namespace,Metadata.module,Metadata.version)
    pom{
        name.set(rootProject.name)
        licenses{
            license {
                name.set(Metadata.License.name)
                url.set(Metadata.License.url)
                distribution.set(Metadata.License.distribution)
            }
        }
    }
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
    namespace = Metadata.namespace
    compileSdk = 33

    defaultConfig.minSdk = 21
    defaultConfig.targetSdk = 33

    val java = JavaVersion.values().find { it.name.endsWith(Metadata.javaVersion) }

    compileOptions.sourceCompatibility = java
    compileOptions.targetCompatibility = java
}