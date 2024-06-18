plugins {
    id("ham.icec.android.library")
    id("ham.icec.android.library.compose")
    id("ham.icec.android.feature")
    id("ham.icec.android.hilt")
}

android {
    namespace = "com.ham.icec.compose.detect"
}

dependencies {
    implementation(projects.core.utilAndroid)

    implementation(libs.kotlinx.collections.immutable)
}