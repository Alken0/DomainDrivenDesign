import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm") version "1.6.10"
	application
}

group = "com.bartlin"
version = "1.0-SNAPSHOT"

repositories {
	mavenCentral()
	maven { url = uri("https://dl.bintray.com/kotlin/kotlinx") }
	maven { url = uri("https://dl.bintray.com/kotlin/ktor") }
}

dependencies {
	// JUNIT
	testImplementation(kotlin("test-junit5"))
	testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")

	// KTOR
	implementation("io.ktor:ktor-server-netty:1.6.7")
	implementation("io.ktor:ktor-html-builder:1.6.7")
	implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.3")
	implementation("org.slf4j:slf4j-simple:1.7.33")
	implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.3")
	implementation("org.slf4j:slf4j-simple:1.7.33")

	// DATABASE
	implementation("org.jetbrains.exposed:exposed-core:0.36.1")
	implementation("org.jetbrains.exposed:exposed-jdbc:0.36.1")
	implementation("org.jetbrains.exposed:exposed-dao:0.36.1")
	implementation("com.h2database:h2:1.4.200")
	implementation("com.zaxxer:HikariCP:5.0.1")
}

tasks.test {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions.jvmTarget = "13"
}

application {
	mainClass.set("com.bartlin.app.ApplicationKt")
}
