// Security platform module

dependencies {
    api(project(":modules:platform:shared-kernel"))
    
    // JWT and security
    api("io.jsonwebtoken:jjwt-api:0.12.3")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.3")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.3")
}

group = "chiro.erp.platform"
version = "1.0.0-SNAPSHOT"

