plugins {
    id("com.android.library") version "7.4.2" apply false
    kotlin("multiplatform") version "1.8.10"  apply false
}

buildscript {
    dependencies  {
        classpath("com.android.tools.build:gradle:7.4.2")
    }
}