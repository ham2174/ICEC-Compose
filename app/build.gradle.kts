plugins {
    id("ham.icec.android.application")
    id("ham.icec.android.application.compose")
    id("ham.icec.android.hilt")
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.ham.icec.compose"

    defaultConfig {
        applicationId = "com.ham.icec.compose"
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    hilt {
        enableAggregatingTask = true
    }
}

dependencies {
    // feature
    implementation(projects.feat.home)
    implementation(projects.feat.gallery)
    implementation(projects.feat.mosaic)
    implementation(projects.feat.detect)
    implementation(projects.feat.result)

    // core
    implementation(projects.core.designSystem)
    implementation(projects.core.data)
    implementation(projects.core.domain)
    implementation(projects.core.faceDetection)
    implementation(projects.core.utilAndroid)

    // compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.lifecycle)
    implementation(libs.androidx.compose.foundation)

    // hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    androidTestImplementation(libs.hilt.testing)
    kspAndroidTest(libs.hilt.compiler)

    // serialization
    implementation(libs.kotlinx.serialization.json)

    // unit test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}