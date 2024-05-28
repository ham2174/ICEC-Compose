plugins {
    id("ham.icec.android.library")
    id("ham.icec.android.library.compose")
}

android {
    namespace = "com.ham.icec.compose.ui"
}

dependencies {
    implementation(projects.core.designSystem)
}