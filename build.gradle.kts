import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.net.URI

val junitVersion = "5.8.2"
val kotlinxCoroutinesVersion = "1.6.2"
val ktorVersion = "2.0.3"
val kotlinxHtmlVersion = "0.7.5"        // ktor needs it
val logbackVersion = "1.2.11"

group = "dinkla"
version = "1.0"

plugins {
    kotlin("jvm") version "1.6.20"
    application
}

repositories {
    maven {
        url = URI.create("https://kotlin.bintray.com/kotlinx")
    }
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${kotlinxCoroutinesVersion}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing:${kotlinxCoroutinesVersion}")
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:${kotlinxHtmlVersion}")
    implementation("io.ktor:ktor-server-html-builder:$ktorVersion")
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-cio:$ktorVersion")
    implementation("ch.qos.logback:logback-classic:${logbackVersion}")

    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}
