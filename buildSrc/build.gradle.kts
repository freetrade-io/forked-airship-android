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
    implementation(kotlin("gradle-plugin", version = "1.5.31"))
    implementation("io.github.gradle-nexus:publish-plugin:1.1.0")
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:2.3.0")

    constraints {
        implementation("org.jetbrains.kotlin:kotlin-stdlib:1.6.0")
        implementation("com.fasterxml.jackson.core:jackson-core:2.22.3")
        runtimeOnly("org.jetbrains.kotlin:kotlin-stdlib-jdk7:2.1.0")
        runtimeOnly("org.jetbrains.kotlin:kotlin-stdlib-jdk8:2.1.0")
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

// Keep kotlin-stdlib above the vulnerable 1.5.31 (CVE-2022-24329) on every configuration,
// including the Kotlin compiler classpaths that are not covered by the dependency constraints.
configurations.configureEach {
    resolutionStrategy.eachDependency {
        if (requested.group == "org.jetbrains.kotlin" && requested.name == "kotlin-stdlib" &&
            requested.version?.startsWith("1.") == true
        ) {
            useVersion("1.6.0")
        }
    }
}
