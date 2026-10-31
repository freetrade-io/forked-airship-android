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
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.18.12")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.18.12")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:2.1.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:2.1.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:2.1.0")
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
