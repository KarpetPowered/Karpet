import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    kotlin("jvm") version "1.6.20"
    application
    id("org.jetbrains.dokka") version "1.5.0"
    id("org.jlleitschuh.gradle.ktlint") version "10.3.0"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "dev.interfiber"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        url = uri("https://jitpack.io")
    }
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.github.Minestom:Minestom:f80f653ee0")
    implementation("io.github.microutils:kotlin-logging-jvm:2.1.20")
    implementation("com.moandjiezana.toml:toml4j:0.7.2")
    implementation("org.bstats:bstats-base:3.0.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("KarpetLauncherKt")
}

tasks {
    named<ShadowJar>("shadowJar") {
        mergeServiceFiles()
        relocate("org.bstats", "dev.interfiber.karpet.bstats")
    }
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "KarpetLauncherKt"
    }
}
