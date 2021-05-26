plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = Versions.COMPILE_SDK

    defaultConfig {
        minSdk = Versions.MIN_SDK
        targetSdk = Versions.TARGET_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        getByName("release") {
        }
        getByName("debug") {
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        val options = this
        options.jvmTarget = "1.8"
    }
}

dependencies {
    api(platform(project(":depconstraints")))
    kapt(platform(project(":depconstraints")))

    // Android
    implementation(Libs.APP_COMPAT)
    implementation(Libs.CORE_KTX)
    implementation(Libs.MATERIAL)

    // Utils
    api(Libs.TIMBER)

    // Retrofit
    implementation(Libs.OKHTTP)
    api(Libs.RETROFIT)
    api(Libs.MOSHI)
    kapt(Libs.MOSHI_CODEGEN)

    api(Libs.MOSHI_RETROFIT)

    // Kotlin
    implementation(Libs.KOTLIN_STDLIB)
    api(Libs.COROUTINES)

    // Debug
    debugImplementation(Libs.LEAK_CANARY)
    implementation(Libs.OKHTTP_LOGGING_INTERCEPTOR)

    debugImplementation(Libs.FLIPPER)
    debugImplementation(Libs.SOLOADER)
    debugImplementation(Libs.FLIPPER_LEAK_CANARY)
    debugImplementation(Libs.FLIPPER_NETWORK)

    // Hilt
    implementation(Libs.HILT_ANDROID)
    kapt(Libs.HILT_COMPILER)

    // Unit Tests
    testImplementation(Libs.JUNIT)

    testImplementation(Libs.HILT_TESTING)
    kaptTest(Libs.HILT_COMPILER)

    // Instrumentation Tests
    androidTestImplementation(Libs.HILT_TESTING)
    kaptAndroidTest(Libs.HILT_COMPILER)
}