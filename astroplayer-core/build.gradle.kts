import org.jetbrains.dokka.gradle.DokkaMultiModuleTask
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.documentation)

    id("maven-publish")
    id("signing")
}

kotlin {
    applyDefaultHierarchyTemplate()

    jvmToolchain(11)

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        moduleName = "astroplayer_core"
        browser {
            commonWebpackConfig {
                outputFileName = "astroplayer_core.js"
            }
        }
        binaries.executable()
    }

    androidTarget {
        publishLibraryVariants("release","debug")
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }

    jvm("desktop")

    if(System.getProperty("os.name") == "Mac OS X") listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "astroplayer-core"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            api(libs.multiplatform.uri)
            implementation(libs.kotlinx.coroutines.core)

        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        val androidMain by getting {
            dependencies {
                api(libs.androidx.media3.exoplayer)
                api(libs.androidx.media3.session)
            }
        }

        val desktopMain by getting {
            dependencies {
                api(libs.vlcj)
            }
        }

        val wasmJsMain by getting {
            dependencies {
                implementation(npm("howler","2.2.4"))
            }
        }
    }
}

android {
    namespace = "com.deathsdoor.astroplayer.core"

    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.androidx.annotation.jvm)
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
                version = "0.1.0"

                name.set("astroplayer-core")
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