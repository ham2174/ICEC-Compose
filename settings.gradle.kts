@file:Suppress("UnstableApiUsage")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    includeBuild("build-logic")

    repositories {
        google()

        mavenCentral {
            content {
                includeGroup("com.google.dagger")
                includeGroup("com.google.dagger.hilt.android")
            }
        }

        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()

        mavenCentral()
    }
}

rootProject.name = "icec-compose"
include(":app")

include(":core:design-system")
include(":core:domain")
include(":core:data")
include(":core:ui")

include(":feat:home")
include(":feat:gallery")
include(":feat:mosaic")
include(":feat:detect")