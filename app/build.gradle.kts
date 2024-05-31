plugins {
    id("ham.icec.android.application")
    id("ham.icec.android.application.compose")
    id("ham.icec.android.hilt")
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

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
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

    // core
    implementation(projects.core.designSystem)
    implementation(projects.core.data)
    implementation(projects.core.domain)
    implementation(projects.core.faceDetection)

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

    // unit test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}