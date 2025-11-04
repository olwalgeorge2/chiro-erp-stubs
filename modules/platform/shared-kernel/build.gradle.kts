// Shared kernel - Pure library module (no Quarkus application)

dependencies {
    // Jackson for JSON serialization
    api("com.fasterxml.jackson.core:jackson-databind:2.16.1")
    api("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.16.1")
    api("com.fasterxml.jackson.module:jackson-module-kotlin:2.16.1")
    
    // Logging
    api("org.jboss.logging:jboss-logging:3.5.3.Final")
}

group = "chiro.erp.platform"
version = "1.0.0-SNAPSHOT"

