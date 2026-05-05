plugins {
    `java-library`
    `maven-publish`
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.serialization") version "1.9.24"
}

group = "com.github.ArtemBotnev"
version = "1.0.0"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21

    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
    withSourcesJar()
}

repositories {
    mavenCentral()
}

val ktorVersion = "2.3.11"
val logbackVersion = "1.5.6"

dependencies {
    // Ktor Client Core & Engine
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-okhttp:$ktorVersion")

    // Serialization & Content Negotiation
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

    // Logging
    implementation("io.ktor:ktor-client-logging:$ktorVersion")
    // Logback for Java environment (should be excluded when used in Android apps)
//    implementation("ch.qos.logback:logback-classic:$logbackVersion")

    // Dependency Injection
    implementation("javax.inject:javax.inject:1")

    // Test
    testImplementation("junit:junit:4.13.2")
    testImplementation(kotlin("test-junit"))
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "21"
        freeCompilerArgs = listOf("-Xjsr305=strict")
    }
}

tasks.getByName<Test>("test") {
    useJUnit()
}

publishing {
    publications {
        register<MavenPublication>("maven") {
            from(components["java"])

            groupId = group.toString()
            artifactId = "weather-station-client"
            version = version.toString()
        }
    }
}