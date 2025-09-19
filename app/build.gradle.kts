plugins {
    id("ham.icec.android.application")
    id("ham.icec.android.hilt")
    id("ham.icec.compose")
    id("ham.icec.serialization")
}

android {
    namespace = "com.ham.icec.compose"

    defaultConfig {
        applicationId = "com.ham.icec.compose"
    }

    buildFeatures {
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

    // library
    implementation(libs.androidx.compose.activity)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.viewmodel.savedstate)
}