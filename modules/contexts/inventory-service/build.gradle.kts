// Inventory Service - Web Quarkus application (REST + Database)

dependencies {
    // Platform modules - selective for web service
    implementation(project(":modules:platform:shared-kernel"))
    implementation(project(":modules:platform:security"))
    implementation(project(":modules:platform:observability"))
    
    // Quarkus web service bundle (REST + Database, no messaging)
    implementation(libs.bundles.quarkus.web.service)
    
    // Testing
    testImplementation(libs.bundles.quarkus.testing)
}

group = "chiro.erp.contexts"
version = "1.0.0-SNAPSHOT"

