plugins {
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.allopen) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.quarkus) apply false
    alias(libs.plugins.ktlint) apply false
    alias(libs.plugins.avro) apply false
}

allprojects {
    repositories {
        mavenCentral()
        mavenLocal()
        maven { url = uri("https://packages.confluent.io/maven/") }
    }
    
    group = "chiro.erp"
    version = "1.0.0-SNAPSHOT"
}

subprojects {
    // Skip configuration for any build or intermediate directories
    if (project.name == "build" || project.name == "intermediates") {
        return@subprojects
    }
    
    // Apply Java plugin first before Kotlin to ensure dependency configurations exist
    pluginManager.apply("java-library")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.allopen")
    apply(plugin = "org.jetbrains.kotlin.plugin.serialization")
    
    // Only apply Quarkus to service modules, not platform modules
    if (project.path.startsWith(":modules:contexts:")) {
        apply(plugin = "io.quarkus")
    }
    
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    
    // Apply dependencies directly without afterEvaluate for Gradle 9.0 compatibility
    dependencies {
        // Kotlin standard library - common for all modules
        add("implementation", rootProject.libs.kotlin.stdlib)
        add("implementation", rootProject.libs.kotlin.reflect)
        
        // Quarkus BOM and base dependencies - only for service modules
        if (project.path.startsWith(":modules:contexts:")) {
            add("implementation", platform(rootProject.libs.quarkus.bom))
            
            // Base Quarkus dependencies applied to all services
            // Individual services can add specific bundles in their build.gradle.kts
            add("implementation", rootProject.libs.quarkus.kotlin)
            add("implementation", rootProject.libs.quarkus.arc)
            add("implementation", rootProject.libs.quarkus.health)
            
            // Quarkus testing dependencies
            add("testImplementation", rootProject.libs.quarkus.junit5)
            add("testImplementation", rootProject.libs.rest.assured)
        }
        
        // Common testing dependencies for all modules
        add("testImplementation", rootProject.libs.kotlin.test)
        add("testImplementation", rootProject.libs.junit.jupiter)
        add("testImplementation", rootProject.libs.mockk)
        add("testImplementation", rootProject.libs.assertj.core)
    }
    
    tasks.withType<Test> {
        useJUnitPlatform()
        systemProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager")
        jvmArgs("--add-opens", "java.base/java.lang=ALL-UNNAMED")
    }
    
    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        version.set("1.0.1")
        debug.set(false)
        verbose.set(false)
        android.set(false)
        outputToConsole.set(true)
        outputColorName.set("RED")
        ignoreFailures.set(true)
        
        filter {
            exclude("**/generated/**")
            exclude("**/build/**")
        }
    }
    
    // Kotlin compiler options (Gradle 9.0 + Kotlin 2.2 compilerOptions DSL)
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21)
            javaParameters.set(true)
            freeCompilerArgs.add("-Xjsr305=strict")
            freeCompilerArgs.add("-opt-in=kotlin.RequiresOptIn")
        }
    }
    
    // AllOpen configuration for Quarkus
    configure<org.jetbrains.kotlin.allopen.gradle.AllOpenExtension> {
        annotation("jakarta.ws.rs.Path")
        annotation("jakarta.enterprise.context.ApplicationScoped")
        annotation("jakarta.persistence.Entity")
        annotation("io.quarkus.test.junit.QuarkusTest")
    }
}

// Multi-module tasks
tasks.register("buildAllServices") {
    dependsOn(subprojects.filter { it.path.startsWith(":modules:contexts:") }.map { it.tasks.named("build") })
    description = "Build all microservices"
    group = "build"
}

tasks.register("cleanAllServices") {
    dependsOn(subprojects.map { it.tasks.named("clean") })
    description = "Clean all modules"
    group = "build"
}

tasks.register("ktlintCheckAll") {
    dependsOn(subprojects.map { it.tasks.named("ktlintCheck") })
    description = "Run ktlint check on all modules"
    group = "verification"
}

tasks.register("ktlintFormatAll") {
    dependsOn(subprojects.map { it.tasks.named("ktlintFormat") })
    description = "Run ktlint format on all modules"
    group = "formatting"
}
