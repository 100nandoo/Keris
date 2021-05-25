buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.1.0-alpha01")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}")
    }
}

plugins {
    id("com.diffplug.spotless") version "5.10.0"
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}