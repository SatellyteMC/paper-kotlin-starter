import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "1.8.20"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("maven-publish")
    id("io.papermc.paperweight.userdev") version "1.5.4"
    id("xyz.jpenilla.run-paper") version "2.0.1"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.3"
}

group = "net.satellyte"
version = "1.0.0"
description = "My Kotlin Paper Plugin"

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    implementation(kotlin("stdlib"))
    paperDevBundle("1.19.4-R0.1-SNAPSHOT")
    implementation("net.axay:kspigot:1.19.2")
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(17))

tasks {

    assemble {
        dependsOn(reobfJar)
    }

    compileJava {
        options.encoding = "UTF-8"
    }

    compileKotlin {
        kotlinOptions.jvmTarget = "17"
    }

    shadowJar { }
}

bukkit {
    name = "Starter"
    description = description
    main = "net.satellyte.starter.Starter"
    version = version
    apiVersion = "1.19"
}