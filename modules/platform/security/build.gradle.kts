// Security platform module

dependencies {
    api(project(":modules:platform:shared-kernel"))
    
    // JWT and security
    api(libs.jwt.api)
    runtimeOnly(libs.jwt.impl)
    runtimeOnly(libs.jwt.jackson)
}

group = "chiro.erp.platform"
version = "1.0.0-SNAPSHOT"

