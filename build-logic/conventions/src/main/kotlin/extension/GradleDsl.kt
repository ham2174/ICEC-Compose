package extension

import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.implementation(
    artifact: Dependency,
) {
    add("implementation", artifact)
}

fun DependencyHandlerScope.implementation(
    artifact: Project,
) {
    add("implementation", artifact)
}

fun DependencyHandlerScope.implementation(
    artifact: Provider<MinimalExternalModuleDependency>,
) {
    add("implementation", artifact)
}

fun DependencyHandlerScope.debugImplementation(
    artifact: Provider<MinimalExternalModuleDependency>,
) {
    add("debugImplementation", artifact)
}

fun DependencyHandlerScope.ksp(
    artifact: Provider<MinimalExternalModuleDependency>,
) {
    add("ksp", artifact)
}
