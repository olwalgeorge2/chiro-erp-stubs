// Shared kernel - Pure library module (no Quarkus application)

dependencies {
    // Jackson for JSON serialization
    api(libs.bundles.jackson)
    
    // Logging
    api(libs.jboss.logging)
}

group = "chiro.erp.platform"
version = "1.0.0-SNAPSHOT"

