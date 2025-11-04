// Event contracts using Avro schemas

plugins {
    alias(libs.plugins.avro)
}

dependencies {
    // Avro
    api(libs.avro)
}

// Configure Avro plugin to generate from .avsc files
tasks.named<com.github.davidmc24.gradle.plugin.avro.GenerateAvroJavaTask>("generateAvroJava") {
    source("src/main/avro")
    setOutputDir(file("build/generated-main-avro-java"))
}

// Add generated sources to main source set
sourceSets {
    main {
        java {
            srcDir("build/generated-main-avro-java")
        }
    }
}

group = "chiro.erp.platform"
version = "1.0.0-SNAPSHOT"

