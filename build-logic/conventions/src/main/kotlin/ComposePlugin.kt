import extension.android
import extension.implementation
import extension.library
import extension.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal class ComposePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            android {
                buildFeatures.compose = true
            }

            dependencies {
                implementation(platform(libs.library("androidx.compose.bom")))
                implementation(libs.library("androidx.compose.ui.graphics"))
                implementation(libs.library("androidx.compose.ui.tooling"))
                implementation(libs.library("androidx.compose.ui.tooling.preview"))
                implementation(libs.library("androidx.compose.foundation"))
                implementation(libs.library("androidx.compose.material3"))
                implementation(libs.library("androidx.compose.runtime"))
            }
        }
    }
}

// feature
                // compose
                // hilt-android
// android
    // => coroutine
// coroutine
// serialize

// module (design-system, domain, ui, viewmodel)
// library (coroutine, immutable
