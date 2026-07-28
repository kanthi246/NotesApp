// Top-level build file where you can add configuration options common to all sub-projects/modules.
// build.gradle.kts (root — the one at project level, not app level)
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false
}