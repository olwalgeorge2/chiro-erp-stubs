// Observability platform module

dependencies {
    api(project(":modules:platform:shared-kernel"))
    
    // Micrometer
    api(libs.micrometer.core)
    api(libs.micrometer.prometheus)
}

group = "chiro.erp.platform"
version = "1.0.0-SNAPSHOT"

