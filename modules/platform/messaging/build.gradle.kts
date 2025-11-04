// Messaging platform module

dependencies {
    // Reference shared-kernel
    api(project(":modules:platform:shared-kernel"))
    
    // Kafka clients
    api("org.apache.kafka:kafka-clients:3.6.1")
    api("io.confluent:kafka-avro-serializer:7.5.0")
    
    // Logging
    implementation("org.jboss.logging:jboss-logging:3.5.3.Final")
}

group = "chiro.erp.platform"
version = "1.0.0-SNAPSHOT"

