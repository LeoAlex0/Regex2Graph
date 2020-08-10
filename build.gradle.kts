import org.gradle.jvm.tasks.Jar

group = "tk.zleoalex"
version = "1.0-SNAPSHOT"

plugins {
    kotlin("jvm") version "1.3.72"
}

tasks {
    register<Jar>("fatJar") {
        group = "application"
        baseName = "${project.name}-fat"
        manifest {
            attributes["Main-Class"] = "tk.zleoalex.MainKt"
        }
        from(configurations.runtimeClasspath.get().map({ if (it.isDirectory) it else zipTree(it) }))
        with(jar.get() as CopySpec)
    }
    "build" {
        dependsOn("fatJar")
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    // https://mvnrepository.com/artifact/guru.nidi/graphviz-kotlin
    implementation("guru.nidi", "graphviz-kotlin", "0.17.0")
}