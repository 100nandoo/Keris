import java.io.FileInputStream
import java.util.Properties
import org.apache.tools.ant.taskdefs.condition.Os

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-android")
    id("dagger.hilt.android.plugin")
}

var keystorePropertiesFile = file("../../signing/keystore.properties")
if (Os.isFamily(Os.FAMILY_WINDOWS)) {
    keystorePropertiesFile = file("..\\..\\signing\\keystore.properties")
}
val keystoreProperties = Properties()
keystoreProperties.load(FileInputStream(keystorePropertiesFile))

android {
    compileSdk = Versions.COMPILE_SDK

    defaultConfig {
        applicationId = "org.hapley.keris"
        minSdk = Versions.MIN_SDK
        targetSdk = Versions.TARGET_SDK
        versionCode = Versions.versionCode
        versionName = Versions.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true

        javaCompileOptions {
            annotationProcessorOptions {
                arguments["dagger.hilt.disableModulesHaveInstallInCheck"] = "true"
            }
        }
    }

    signingConfigs {
        register("release") {
            keyAlias = keystoreProperties["keyAlias"] as String
            keyPassword = keystoreProperties["keyPassword"] as String
            storeFile = file(keystoreProperties["storeFile"] as String)
            storePassword = keystoreProperties["storePassword"] as String
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
        getByName("debug") {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
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
    kapt {
        correctErrorTypes = true
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.COMPOSE
    }
}

dependencies {
    api(platform(project(":depconstraints")))
    kapt(platform(project(":depconstraints")))
    implementation(project(":shared"))

    implementation(Libs.KOTLIN_STDLIB)

    // Android
    implementation(Libs.APP_COMPAT)
    implementation(Libs.CORE_KTX)
    implementation(Libs.MATERIAL)

    // Compose
    implementation(Libs.COMPOSE_UI)
    implementation(Libs.COMPOSE_MATERIAL)
    implementation(Libs.COMPOSE_UI_TOOLING)
    implementation(Libs.COMPOSE_ACTIVITY)

    // Lifecycle
    implementation(Libs.LIFECYCLE_RUN_TIME)

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
    testImplementation(Libs.HILT_TESTING)
    kaptTest(Libs.HILT_COMPILER)

    // Instrumentation Tests
    androidTestImplementation(Libs.HILT_TESTING)
    kaptAndroidTest(Libs.HILT_COMPILER)
}