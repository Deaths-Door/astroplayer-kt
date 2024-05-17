plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.documentation)

    id("maven-publish")
    id("signing")
}

kotlin {
    applyDefaultHierarchyTemplate()

    jvmToolchain(11)

    androidTarget {
        publishLibraryVariants("release","debug")
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }

    if(System.getProperty("os.name") == "Mac OS X") listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "astroplayer-ui"
            isStatic = true
        }
    }

    jvm("desktop")

    wasmJs {
        moduleName = "astroplayer_ui"
        browser {
            commonWebpackConfig {
                outputFileName = "astroplayer_ui.js"
            }
        }
        binaries.executable()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(project(":astroplayer-core"))

            implementation(compose.ui)
            implementation(compose.material3)
            implementation(compose.foundation)
            implementation(compose.runtime)
        }
    }
}

android {
    namespace = "com.deathsdoor.astroplayer.ui"

    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures.compose = true
    composeOptions.kotlinCompilerExtensionVersion = "1.5.8"

    dependencies {
        debugImplementation(compose.uiTooling)
        implementation(compose.preview)
    }
}

// Based on https://medium.com/kodein-koders/publish-a-kotlin-multiplatform-library-on-maven-central-6e8a394b7030
publishing {
    publications {
        repositories {
            maven {
                name="oss"

                val repositoryId = System.getenv("SONATYPE_REPOSITORY_ID")
                val releasesRepoUrl = uri("https://s01.oss.sonatype.org/service/local/staging/deployByRepositoryId/$repositoryId/")
                val snapshotsRepoUrl = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")

                url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl

                credentials {
                    username = System.getenv("SONATYPE_USERNAME")
                    password = System.getenv("SONATYPE_PASSWORD")
                }
            }

            mavenLocal()
        }

        withType<MavenPublication> {
            pom {
                groupId = "com.deathsdooor.astroplayer"
                version = "0.1.1.1-equalizer-SNAPSHOT"

                name.set("astroplayer-ui")
                description.set("AstroPlayer is an open-source media player designed for the Kotlin Multiplatform. It provides a simple API for audio playback and supports multiple media formats while also providing an Jetpack Compose UI.")

                url.set("https://github.com/Deaths-Door/astroplayer-kt")

                issueManagement {
                    system.set("Github")
                    url.set("${this@pom.url.get()}/issues")
                }

                scm {
                    connection.set("${this@pom.url.get()}.git")
                    url.set(this@pom.url.get())
                }
            }
        }
    }
}

// TODO : Enable this again when publishing to mavenCentral
/*
signing {
    useInMemoryPgpKeys(
        System.getenv("GPG_PRIVATE_KEY"),
        System.getenv("GPG_PRIVATE_PASSWORD")
    )

    sign(publishing.publications)
}*/