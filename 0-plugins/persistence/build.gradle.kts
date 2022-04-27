plugins {
    kotlin("jvm")
}

group = "com.bartlin"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":1-adapters"))

    implementation("org.jetbrains.exposed:exposed-core:0.36.1")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.36.1")
    implementation("org.jetbrains.exposed:exposed-dao:0.36.1")
    implementation("com.h2database:h2:1.4.200")
    implementation("com.zaxxer:HikariCP:5.0.1")

    // TESTS
    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
    testImplementation("io.mockk:mockk:1.12.2")
}

tasks.test {
    useJUnitPlatform()
}
