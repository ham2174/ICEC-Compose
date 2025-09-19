plugins {
    `kotlin-dsl`
}

group = "com.ham.icec.compose.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

dependencies {
    compileOnly(libs.android.gradleplugin)
    compileOnly(libs.kotlin.gradleplugin)
    compileOnly(libs.kotlin.serialization.gradleplugin)
    compileOnly(libs.compose.compiler.gradleplugin)
    compileOnly(libs.ksp.gradleplugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "ham.icec.android.application"
            implementationClass = "AndroidApplicationPlugin"
        }
        register("androidLibrary") {
            id = "ham.icec.android.library"
            implementationClass = "AndroidLibraryPlugin"
        }
        register("androidHilt") {
            id = "ham.icec.android.hilt"
            implementationClass = "AndroidHiltPlugin"
        }
        register("compose") {
            id = "ham.icec.compose"
            implementationClass = "ComposePlugin"
        }
        register("serialization") {
            id = "ham.icec.serialization"
            implementationClass = "SerializationPlugin"
        }
    }
}