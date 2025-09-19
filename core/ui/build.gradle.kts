plugins {
    id("ham.icec.android.library")
    id("ham.icec.compose")
}

android {
    namespace = "com.ham.icec.compose.ui"
}

dependencies {
    implementation(projects.core.designSystem)

    implementation(libs.coil.compose)
}