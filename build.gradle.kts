import org.jetbrains.kotlin.config.KotlinCompilerVersion
import java.net.URI
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val junitVersion = "5.7.1"
val kotlinxCoroutinesVersion = "1.4.2"
val ktorVersion = "1.5.1"
val kotlinxHtmlVersion = "0.7.2"        // ktor needs it

group = "dinkla"
version = "1.0"

plugins {
    kotlin("jvm") version "1.4.32"
    application
}

repositories {
    jcenter()
    maven {
        url = URI.create("https://kotlin.bintray.com/kotlinx")
    }
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${kotlinxCoroutinesVersion}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing:${kotlinxCoroutinesVersion}")
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:${kotlinxHtmlVersion}")
    implementation("io.ktor:ktor-html-builder:$ktorVersion")
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-cio:$ktorVersion")
    implementation("ch.qos.logback:logback-classic:1.2.3")

    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}

application {
    mainClassName = "MainKt"
}