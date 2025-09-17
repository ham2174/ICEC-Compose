package extension

import org.gradle.api.Project
import org.gradle.api.artifacts.ExternalModuleDependencyBundle
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.getByType
import org.gradle.plugin.use.PluginDependency

internal val Project.libs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal fun VersionCatalog.version(name: String): String = findVersion(name).get().requiredVersion

internal fun VersionCatalog.library(name: String): Provider<MinimalExternalModuleDependency> = findLibrary(name).get()

internal fun VersionCatalog.plugin(name: String): Provider<PluginDependency> = findPlugin(name).get()

internal fun VersionCatalog.bundle(name: String): Provider<ExternalModuleDependencyBundle> = findBundle(name).get()