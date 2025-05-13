plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.google.gms.google.services)
}
android {
    namespace = "com.ffskin.vault.elitecustomizer"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.ffskin.vault.elitecustomizer"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding = true
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.material)
    implementation(libs.core.ktx)
    implementation(libs.ssp.android)
    implementation(libs.sdp.android)
    implementation(libs.firebase.config)
    implementation(libs.browser)
    implementation(libs.glide)
    implementation(libs.gson)
    implementation("com.onesignal:OneSignal:[5.1.6, 5.1.99]")
    implementation(libs.appcompat)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

}