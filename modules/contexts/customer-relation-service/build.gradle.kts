// Customer Relation Service - Full Quarkus application

dependencies {
    // Platform modules
    implementation(project(":modules:platform:shared-kernel"))
    implementation(project(":modules:platform:messaging"))
    implementation(project(":modules:platform:security"))
    implementation(project(":modules:platform:observability"))
    implementation(project(":modules:platform:contracts"))
}

group = "chiro.erp.contexts"
version = "1.0.0-SNAPSHOT"

