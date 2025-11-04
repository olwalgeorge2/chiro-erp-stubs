// Observability platform module

dependencies {
    api(project(":modules:platform:shared-kernel"))
    
    // Micrometer
    api("io.micrometer:micrometer-core:1.12.2")
    api("io.micrometer:micrometer-registry-prometheus:1.12.2")
}

group = "chiro.erp.platform"
version = "1.0.0-SNAPSHOT"

