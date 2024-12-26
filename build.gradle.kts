plugins {
    kotlin("jvm") version "2.0.21"
    id("org.springframework.boot") version "3.3.5"
    kotlin("plugin.spring") version "1.9.23"
    id("com.bmuschko.docker-spring-boot-application") version "9.4.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.springframework.boot:spring-boot-dependencies:3.3.6"))
    implementation(platform("org.springframework.cloud:spring-cloud-dependencies:2023.0.4"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}

docker {
    springBootApplication{
        baseImage.set("openjdk:17-alpine")
    }
}

tasks {
    test {
        useJUnitPlatform()
    }
}