plugins {
    id("ham.icec.android.library")
}

android {
    namespace = "com.ham.icec.compose.utilandroid"
}

dependencies {
    implementation(libs.kotlin.coroutines.core)
}