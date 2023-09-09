plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("com.google.dagger.hilt.android")
}

android {
    namespace = "com.mikeSTF.notesapp"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.mikeSTF.notesapp"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.1"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation ("androidx.core:core-ktx:1.9.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation ("androidx.activity:activity-compose:1.6.1")
    implementation ("androidx.compose.ui:ui:1.3.2")
    implementation ("androidx.compose.ui:ui-tooling-preview:1.3.2")
    implementation ("androidx.compose.material:material:1.3.1")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.4")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.0")
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4:1.3.2")
    debugImplementation ("androidx.compose.ui:ui-tooling:1.3.2")
    debugImplementation ("androidx.compose.ui:ui-test-manifest:1.3.2")

    // Compose Navigation
    implementation ("androidx.navigation:navigation-compose:2.5.3")

    // Room Components
    implementation ("androidx.room:room-runtime:2.4.3")
    kapt ("androidx.room:room-compiler:2.4.3")
    implementation ("androidx.room:room-ktx:2.4.3")
    implementation ("androidx.room:room-paging:2.4.3")

    // Dagger - Hilt
    implementation ("com.google.dagger:hilt-android:2.44")
    kapt ("androidx.hilt:hilt-compiler:1.0.0")
    kapt ("com.google.dagger:hilt-android-compiler:2.44")

    // Splash Screen
    implementation ("androidx.core:core-splashscreen:1.0.0")

    // Paging
    implementation ("androidx.paging:paging-compose:1.0.0-alpha17")

    // App Startup
    implementation ("androidx.startup:startup-runtime:1.1.1")
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}