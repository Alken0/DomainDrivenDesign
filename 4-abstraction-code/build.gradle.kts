plugins {
    kotlin("jvm")
}

group = "com.bartlin"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    api(kotlin("stdlib"))
}

tasks.test {
    useJUnitPlatform()
}
