package extension

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

fun Project.androidApplication(action: ApplicationExtension.() -> Unit) {
    extensions.configure(action)
}

fun Project.android(action: LibraryExtension.() -> Unit) {
    extensions.configure(action)
}

fun Project.kotlin(action: KotlinAndroidProjectExtension.() -> Unit) {
    extensions.configure(action)
}