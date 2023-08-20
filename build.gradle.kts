import org.jetbrains.kotlin.gradle.tasks.KotlinCompile // For `KotlinCompile` task below

plugins {
    id("org.springframework.boot") version "3.1.2"
    id("io.spring.dependency-management") version "1.1.2"
    id("org.openapi.generator") version "5.4.0"
    id("org.jlleitschuh.gradle.ktlint") version "10.0.0"
    kotlin("jvm") version "1.9.0" // The version of Kotlin to use
    kotlin("plugin.spring") version "1.9.0" // The Kotlin Spring plugin
}

allprojects {
    apply(plugin = "jacoco")
    apply(plugin = "java")
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(plugin = "org.springframework.boot")

    group = "com.jerin.ignatious"
    version = "0.0.1-SNAPSHOT"

    java.sourceCompatibility = JavaVersion.VERSION_17
    java.targetCompatibility = JavaVersion.VERSION_17

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-web")
    }

    tasks.withType<KotlinCompile> { // Settings for `KotlinCompile` tasks
        kotlinOptions { // Kotlin compiler options
            freeCompilerArgs = listOf("-Xjsr305=strict") // `-Xjsr305=strict` enables the strict mode for JSR-305 annotations
            jvmTarget = "17" // This option specifies the target version of the generated JVM bytecode
        }
    }

    ktlint {
        filter {
            exclude { element ->
                element.file.path.contains("protocol/http/client") || element.file.path.contains("protocol/http/server-stub") || element.file.path.contains(
                    "external-partners/api/client-reactive"
                )
            }
        }
    }
}
