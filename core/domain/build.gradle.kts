plugins {
    `java-library`
    alias(libs.plugins.kotlin.jvm)
    id("ham.icec.serialization")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

dependencies {
    implementation(libs.kotlin)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.javax.inject)
}
