// Commerce Service - Full Quarkus application (Hybrid: REST + Database + Messaging)

dependencies {
    // Platform modules - selective inclusion
    implementation(project(":modules:platform:shared-kernel"))
    implementation(project(":modules:platform:messaging"))
    implementation(project(":modules:platform:security"))
    implementation(project(":modules:platform:observability"))
    implementation(project(":modules:platform:contracts"))
    
    // Quarkus hybrid service bundle (REST + DB + Messaging)
    implementation(libs.bundles.quarkus.hybrid.service)
    
    // Testing
    testImplementation(libs.bundles.quarkus.testing)
}

group = "chiro.erp.contexts"
version = "1.0.0-SNAPSHOT"

