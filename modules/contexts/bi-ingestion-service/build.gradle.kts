// BI Ingestion Service - Event-driven Quarkus application (Messaging only)

dependencies {
    // Platform modules - only what's needed for event processing
    implementation(project(":modules:platform:shared-kernel"))
    implementation(project(":modules:platform:messaging"))
    implementation(project(":modules:platform:observability"))
    implementation(project(":modules:platform:contracts"))
    
    // Quarkus messaging service bundle (Event-driven, no REST API)
    implementation(libs.bundles.quarkus.messaging.service)
    
    // Testing
    testImplementation(libs.bundles.quarkus.testing)
}

group = "chiro.erp.contexts"
version = "1.0.0-SNAPSHOT"

