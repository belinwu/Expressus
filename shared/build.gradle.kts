import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("buildlogic.plugins.kmp.library.android")
    id("kotlinx-serialization")
    id("kotlin-parcelize")
    kotlin("native.cocoapods")
}

version = "1.0"

android {
    namespace = "com.expressus"
}

kotlin {
    jvm()
    androidTarget()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Expressus, a multiplatform coffee machine!"
        homepage = "https://github.com/GuilhE/Expressus"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = false // SwiftUI preview requires dynamic framework
        }
    }

    sourceSets {
        all {
            languageSettings.apply {
                optIn("kotlin.RequiresOptIn")
                optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
            }
        }

        val jvmMain by getting

        val androidMain by getting {
            dependencies {
                implementation(libs.koin.android)
            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }

        val commonMain by getting {
            dependencies {
                implementation(libs.jetbrains.kotlinx.coroutines.core)
                implementation(libs.jetbrains.kotlinx.serialization)
                implementation(libs.jetbrains.kotlinx.atomicfu)
                api(libs.multiplatform.kermit)
                api(libs.multiplatform.mokoMvvm)
                api(libs.helpers.orbitMvi.core)
            }
        }

        val shared by creating {
            dependencies {
                api(libs.koin.core)
                implementation(libs.multiplatform.multiplatformSettings)
            }
            jvmMain.dependsOn(this)
            androidMain.dependsOn(this)
            iosMain.dependsOn(this)
            commonMain.dependsOn(this)
        }

        targets.withType<KotlinNativeTarget> {
            compilations["main"].kotlinOptions.freeCompilerArgs += "-Xexport-kdoc"
        }
    }
}