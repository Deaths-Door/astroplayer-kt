import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.documentation)

}

kotlin {
    applyDefaultHierarchyTemplate()
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
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }

    jvm("desktop")

    listOf(
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
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
dependencies {
    implementation(libs.androidx.annotation.jvm)
}
