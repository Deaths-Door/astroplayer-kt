plugins {
    id("com.android.library") version "8.0.0" apply false
    kotlin("multiplatform") version "1.8.10"  apply false
}

buildscript {
    dependencies  {
        classpath("com.android.tools.build:gradle:8.0.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.21")
    }
}