// Test utilities

dependencies {
    api(project(":modules:platform:shared-kernel"))
    api(project(":modules:platform:contracts"))
    
    // Testcontainers
    api("org.testcontainers:testcontainers:1.19.3")
    api("org.testcontainers:postgresql:1.19.3")
    api("org.testcontainers:kafka:1.19.3")
    
    // Testing frameworks
    api("org.junit.jupiter:junit-jupiter:5.10.1")
    api("io.mockk:mockk:1.13.8")
    api("org.assertj:assertj-core:3.24.2")
}

group = "chiro.erp.platform"
version = "1.0.0-SNAPSHOT"

