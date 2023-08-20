import org.jetbrains.kotlin.gradle.tasks.KotlinCompile // For `KotlinCompile` task below

plugins {
    id("org.springframework.boot") version "3.1.2"
    id("io.spring.dependency-management") version "1.1.2"
    kotlin("jvm") version "1.9.0" // The version of Kotlin to use
    kotlin("plugin.spring") version "1.9.0" // The Kotlin Spring plugin
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

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

tasks.withType<Test> {
    useJUnitPlatform()
}
tasks.bootJar {
    mainClass.set("jerin.ignatious.MainApplication")
}

val bootRun: org.springframework.boot.gradle.tasks.run.BootRun by tasks
bootRun.apply {
    val bootRunJvmArgs = emptyList<String>()
    jvmArgs = bootRunJvmArgs
    args = getDefaultJVMArgs()
}

fun getDefaultJVMArgs(): List<String> {
    return listOf(
        "--spring.config.additional-location=" +
                "file:${project.projectDir.absolutePath}/../"
    )
}