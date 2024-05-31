plugins {
    id("ham.icec.android.library")
    id("ham.icec.android.hilt")
}

android {
    namespace = "com.ham.icec.compose.data"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.faceDetection)
}