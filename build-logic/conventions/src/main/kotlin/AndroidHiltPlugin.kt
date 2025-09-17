import extension.implementation
import extension.ksp
import extension.library
import extension.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidHiltPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("dagger.hilt.android.plugin")
                apply("com.google.devtools.ksp")
            }

            dependencies {
                implementation(libs.library("hilt.android"))
                implementation(libs.library("hilt.navigation.compose"))

                ksp(libs.library("hilt.compiler"))
            }
        }
    }
}
