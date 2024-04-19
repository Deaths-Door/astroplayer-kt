plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.documentation)
}

kotlin {
    applyDefaultHierarchyTemplate()

    jvmToolchain(11)

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }
    
    listOf(
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
}
