plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
}

dependencies {
    implementation("com.android.tools.build:gradle:7.2.1")
    implementation(kotlin("gradle-plugin", version = "2.1.0"))
    implementation("io.github.gradle-nexus:publish-plugin:1.1.0")
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:1.9.20")

    constraints {
        implementation("com.fasterxml.jackson.core:jackson-databind:2.18.12") {
            because("CVE-2025-52999, CVE-2026-54512, CVE-2020-36518, CVE-2026-54513, AIKIDO-2026-416948, CVE-2021-46877, CVE-2022-42003, CVE-2022-42004, CVE-2026-54515")
        }
        implementation("com.fasterxml.jackson.core:jackson-core:2.18.12") {
            because("CVE-2025-52999")
        }
        implementation("com.fasterxml.woodstox:woodstox-core:6.4.0") {
            because("CVE-2022-40152")
        }
        implementation("org.jsoup:jsoup:1.21.1") {
            because("CVE-2021-37714, CVE-2022-36033, AIKIDO-2025-10401")
        }
        implementation("org.jetbrains.kotlin:kotlin-stdlib:2.1.0") {
            because("AIKIDO-2026-550292, CVE-2022-24329")
        }
        implementation("org.jetbrains.kotlin:kotlin-stdlib-common:2.1.0") {
            because("AIKIDO-2026-550292")
        }
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:2.1.0") {
            because("AIKIDO-2026-550292")
        }
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:2.1.0") {
            because("AIKIDO-2026-550292")
        }
    }
}

group = "build"

gradlePlugin {
    plugins {
        register("airshipPublish") {
            id = "airship-publish"
            implementationClass = "AirshipPublishPlugin"
        }
        register("airshipDokka") {
            id = "airship-dokka"
            implementationClass = "AirshipDokkaPlugin"
        }
        register("airshipDoclava") {
            id = "airship-doclava"
            implementationClass = "AirshipDoclavaPlugin"
        }
        register("airshipModule") {
            id = "airship-module"
            implementationClass = "AirshipModulePlugin"
        }
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}
