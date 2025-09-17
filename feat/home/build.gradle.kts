plugins {
    id("ham.icec.android.library")
    id("ham.icec.android.hilt")
    id("ham.icec.compose")
    id("ham.icec.serialization")
}

android {
    namespace = "com.ham.icec.compose.home"
}

dependencies {
    implementation(projects.core.utilAndroid)
    implementation(projects.core.designSystem)
    implementation(projects.core.ui)
    implementation(projects.core.domain)

    implementation(libs.kotlinx.collections.immutable)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.navigation.compose)
}