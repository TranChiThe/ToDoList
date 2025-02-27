// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
}
buildscript {
    repositories {
        google()
        mavenCentral() // ObjectBox nằm trên MavenCentral
    }
    dependencies {
        classpath("io.objectbox:objectbox-gradle-plugin:4.1.0") // Thêm plugin ObjectBox
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.44")
    }
}


