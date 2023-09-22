plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {

    namespace = "com.laurenyew.agilityfittodayapp"

    compileSdk = AndroidSdk.compile
    defaultConfig {
        applicationId = "com.laurenyew.agilityfittodayapp"
        minSdk = AndroidSdk.min
        targetSdk = AndroidSdk.target
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }

    buildFeatures {
        // Enables Jetpack Compose for this module
        compose = true
        viewBinding = true
    }

    buildTypes {
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

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.8"
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Lifecycle

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Jetpack.lifecycleVersion}")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:${Jetpack.lifecycleVersion}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Jetpack.lifecycleVersion}")
    kapt("androidx.lifecycle:lifecycle-common-java8:${Jetpack.lifecycleVersion}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:${Jetpack.lifecycleVersion}")
    implementation("androidx.lifecycle:lifecycle-process:${Jetpack.lifecycleVersion}")

    // Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:${Jetpack.navigationVersion}")
    implementation("androidx.navigation:navigation-ui-ktx:${Jetpack.navigationVersion}")
    implementation("androidx.navigation:navigation-fragment-ktx:${Jetpack.navigationVersion}")
    implementation("androidx.navigation:navigation-ui-ktx:${Jetpack.navigationVersion}")
    implementation("androidx.navigation:navigation-compose:${Jetpack.navigationVersion}")

    // Hilt
    implementation("com.google.dagger:hilt-android:${DependencyInjection.hiltVersion}")
    kapt("com.google.dagger:hilt-android-compiler:${DependencyInjection.hiltVersion}")
    kapt("androidx.hilt:hilt-compiler:1.0.0")

    // Jetpack Compose
    val composeBom = platform("androidx.compose:compose-bom:2023.01.00")
    implementation(composeBom)
    androidTestImplementation(composeBom)
    implementation("androidx.compose.compiler:compiler:1.5.3")
    implementation("androidx.compose.ui:ui")
    // Tooling support (Previews, etc.)
    implementation("androidx.compose.ui:ui-tooling")
    // View Models
    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    implementation("androidx.compose.foundation:foundation")
    // Material Design
    implementation("androidx.compose.material:material")
    // Material design icons
    implementation("androidx.compose.material:material-icons-core")
    implementation("androidx.compose.material:material-icons-extended")
    // Integration with observables
    implementation("androidx.compose.runtime:runtime")
    implementation("androidx.compose.runtime:runtime-livedata")
    implementation("androidx.compose.runtime:runtime-rxjava2")
    // Navigation
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0-alpha01")
    // Activity
    implementation("androidx.activity:activity-compose:1.7.2")

    implementation("androidx.compose.animation:animation:1.5.1")

    // Jetpack Paging
    implementation("androidx.paging:paging-runtime-ktx:${Jetpack.pagingVersion}")
    implementation("androidx.paging:paging-compose:${Jetpack.pagingVersion}")
    implementation("androidx.paging:paging-common-ktx:${Jetpack.pagingVersion}")

    // Kotlin Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Coroutines.version}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Coroutines.version}")

    // Image Loading
    implementation("io.coil-kt:coil-compose:1.3.2")
    implementation("com.google.accompanist:accompanist-glide:0.12.0")
    implementation("com.google.accompanist:accompanist-swiperefresh:0.14.0")

    // Networking
    api("com.squareup.retrofit2:retrofit:${Networking.retrofitVersion}")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.1")

    // Parse JSON
    api("com.squareup.moshi:moshi:1.11.0")
    api("com.squareup.moshi:moshi-kotlin:1.10.0")
    api("com.squareup.retrofit2:converter-moshi:${Networking.retrofitVersion}")

    // Room
    kapt("androidx.room:room-compiler:${Room.version}")
    implementation("androidx.room:room-runtime:${Room.version}")
    implementation("androidx.room:room-common:${Room.version}")
    implementation("androidx.room:room-ktx:${Room.version}")
    implementation("androidx.room:room-paging:${Room.version}")
    testImplementation("androidx.room:room-testing:${Room.version}")

    // Logging
    implementation("com.jakewharton.timber:timber:4.7.1")

    testImplementation("junit:junit:4.13.2")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${Coroutines.version}")
    testImplementation("com.google.truth:truth:1.1.3")

    // UI Tests
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
