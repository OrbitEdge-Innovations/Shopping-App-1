pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            library("android-application", "com.android.tools.build:gradle:8.1.0")
            library("kotlin-android", "org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.10")
            library("appcompat", "androidx.appcompat:appcompat:1.6.1")
            library("material", "com.google.android.material:material:1.9.0")
            library("activity", "androidx.activity:activity-ktx:1.7.2")
            library("constraintlayout", "androidx.constraintlayout:constraintlayout:2.1.4")
            library("junit", "junit:junit:4.13.2")
            library("ext-junit", "androidx.test.ext:junit:1.1.5")
            library("espresso-core", "androidx.test.espresso:espresso-core:3.5.1")
        }
    }
}

rootProject.name = "ShoppingApp"
include(":app")
 