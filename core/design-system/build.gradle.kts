plugins {
    id("ham.icec.android.library")
    id("ham.icec.android.library.compose")
}

android {
    namespace = "com.ham.icec.compose.designsystem"
}

dependencies {
    api(libs.compose.glide)

    api(platform(libs.androidx.compose.bom))
    api(libs.androidx.compose.ui)
    api(libs.androidx.compose.ui.tooling)
    api(libs.androidx.compose.ui.graphics)
    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.lifecycle)
}