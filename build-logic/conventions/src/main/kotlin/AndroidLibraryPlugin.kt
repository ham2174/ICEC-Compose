import extension.android
import extension.implementation
import extension.kotlin
import extension.library
import extension.libs
import extension.version
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal class AndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            android {
                compileSdk = libs.version("compileSdk").toInt()

                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_21
                    targetCompatibility = JavaVersion.VERSION_21
                }
            }

            kotlin {
                jvmToolchain(21)
            }

            dependencies {
                implementation(libs.library("kotlinx.coroutines.android"))
                implementation(libs.library("kotlinx.coroutines.core"))
            }
        }
    }
}
