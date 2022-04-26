plugins {
    kotlin("jvm")
}

group = "com.bartlin"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven { url = uri("https://dl.bintray.com/kotlin/kotlinx") }
    maven { url = uri("https://dl.bintray.com/kotlin/ktor") }
}

dependencies {
    implementation(project(":1-adapters"))

    implementation(project(":0-plugins:persistence"))

    implementation("io.ktor:ktor-server-netty:1.6.7")
    implementation("io.ktor:ktor-html-builder:1.6.7")
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.3")
    implementation("org.slf4j:slf4j-simple:1.7.33")
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.3")
    implementation("org.slf4j:slf4j-simple:1.7.33")
}
