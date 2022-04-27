import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    application
}

group = "com.bartlin"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":0-plugins:web"))
}

application {
    mainClass.set("com.bartlin.plugins.web.ApplicationKt")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "13"
}
