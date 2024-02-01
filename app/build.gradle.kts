plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.genxsol.cakesapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.genxsol.cakesapp"
        minSdk = 23
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.5"
    }
}

dependencies {
    // modules
    implementation(project(Modules.utilities))

    // AndroidX
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.appcompat)
    implementation(Dependencies.material)
    implementation(Dependencies.constraintLayout)
    implementation(Dependencies.fragmentKtx)

    // Architectural Components
    implementation(Dependencies.lifecycleViewModelKtx)

    // Coroutines
    implementation(Dependencies.coroutinesCore)
    implementation(Dependencies.coroutinesAndroid)

    // Retrofit
    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitConverterGson)
    implementation(Dependencies.okHttpLoggingInterceptor)

    // Navigation Components
    implementation(Dependencies.navigationFragmentKtx)
    implementation(Dependencies.navigationUiKtx)

    // Dagger Hilt
    implementation(Dependencies.daggerHilt)
    kapt(Dependencies.hiltCompiler)
    implementation(Dependencies.hiltCommon)
    kapt(Dependencies.hiltCompilerCommon)

    // Paging 3
    implementation(Dependencies.paging)
    implementation(Dependencies.pagingCompose)

    // Compose
    implementation(Dependencies.activityCompose)
    implementation(Dependencies.composeBom)
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeMaterial)
    implementation(Dependencies.composeUiGraphics)
    implementation(Dependencies.composeUiToolingPreview)
    implementation(Dependencies.material3)
    implementation(Dependencies.lifecycleViewModelCompose)
    implementation(Dependencies.hiltNavigationCompose)
    implementation(Dependencies.navigationCompose)
    implementation(Dependencies.lifecycleRuntimeCompose)
    implementation(Dependencies.coilCompose)

    // Test
    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.junitExt)
    androidTestImplementation(Dependencies.espressoCore)
    testImplementation(Dependencies.mockitoCore)
    testImplementation(Dependencies.coreTesting)
    testImplementation(Dependencies.coroutinesTest)
    testImplementation(Dependencies.turbine)

}