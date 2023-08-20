plugins {
    id("org.springframework.boot") version "3.1.2"
    id("io.spring.dependency-management") version "1.1.2"
    kotlin("jvm") version "1.9.0" // The version of Kotlin to use
    kotlin("plugin.spring") version "1.9.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
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
        "--spring.config.additional-location= file:${project.projectDir.absolutePath}/../"
    )
}
