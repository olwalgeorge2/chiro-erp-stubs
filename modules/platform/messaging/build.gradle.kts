// Messaging platform module

dependencies {
    // Reference shared-kernel
    api(project(":modules:platform:shared-kernel"))
    
    // Kafka clients
    api(libs.kafka.clients)
    api(libs.kafka.avro.serializer)
    
    // Logging
    implementation(libs.jboss.logging)
}

group = "chiro.erp.platform"
version = "1.0.0-SNAPSHOT"

