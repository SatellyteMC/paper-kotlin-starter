import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "1.7.0"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("maven-publish")
    id("io.papermc.paperweight.userdev") version "1.3.6"
    id("xyz.jpenilla.run-paper") version "1.0.6"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.1"
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
    paperDevBundle("1.19.1-R0.1-SNAPSHOT")
    implementation("net.axay:kspigot:1.19.0")
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

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = "starter"
            from(components["java"])
        }
    }
    repositories {
        maven {
            val releasesRepoUrl = "https://repo.example.com/releases"
            val snapshotsRepoUrl = "https://repo.example.com/snapshots"
            url = uri(if (project.hasProperty("release")) releasesRepoUrl else snapshotsRepoUrl)
            credentials {
                username = System.getenv("MAVEN_REPO_USERNAME")
                password = System.getenv("MAVEN_REPO_PASSWORD")
            }
        }
    }
}