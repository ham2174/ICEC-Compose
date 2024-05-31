plugins {
    id("ham.icec.android.library")
    id("ham.icec.android.hilt")
}

android {
    namespace = "com.ham.icec.compose.facedetection"
}

dependencies {
    implementation(libs.mlkit.face.detection)
}