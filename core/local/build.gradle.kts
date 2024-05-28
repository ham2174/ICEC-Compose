plugins {
    id("ham.icec.android.library")
    id("ham.icec.android.hilt")
}

android {
    namespace = "com.ham.icec.compose.local"
}

dependencies {
    implementation(projects.core.data)
}