plugins {
    id("org.jetbrains.kotlin.jvm") version "2.2.20" apply false
    id("org.jetbrains.kotlin.plugin.allopen") version "2.2.20" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "2.2.20" apply false
    id("io.quarkus") apply false
    id("org.jlleitschuh.gradle.ktlint") version "12.1.2" apply false
    id("com.github.davidmc24.gradle.plugin.avro") version "1.9.1" apply false
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
    // Apply Java plugin first before Kotlin to ensure dependency configurations exist
    pluginManager.apply("java-library")
    pluginManager.apply("org.jetbrains.kotlin.jvm")
    pluginManager.apply("org.jetbrains.kotlin.plugin.allopen")
    pluginManager.apply("org.jetbrains.kotlin.plugin.serialization")
    
    // Only apply Quarkus to service modules, not platform modules
    if (project.path.startsWith(":modules:contexts:")) {
        pluginManager.apply("io.quarkus")
    }
    
    pluginManager.apply("org.jlleitschuh.gradle.ktlint")
    
    afterEvaluate {
        dependencies {
            val quarkusPlatformGroupId: String by project
            val quarkusPlatformArtifactId: String by project
            val quarkusPlatformVersion: String by project
            
            // Kotlin standard library
            add("implementation", "org.jetbrains.kotlin:kotlin-stdlib-jdk8")
            add("implementation", "org.jetbrains.kotlin:kotlin-reflect")
            
            // Quarkus BOM - only for service modules
            if (project.path.startsWith(":modules:contexts:")) {
                add("implementation", enforcedPlatform("$quarkusPlatformGroupId:$quarkusPlatformArtifactId:$quarkusPlatformVersion"))
                add("implementation", "io.quarkus:quarkus-kotlin")
                add("implementation", "io.quarkus:quarkus-arc")
                
                // Common Quarkus dependencies for all services
                add("implementation", "io.quarkus:quarkus-rest")
                add("implementation", "io.quarkus:quarkus-rest-jackson")
                add("implementation", "io.quarkus:quarkus-smallrye-health")
                add("implementation", "io.quarkus:quarkus-hibernate-orm-panache-kotlin")
                add("implementation", "io.quarkus:quarkus-jdbc-postgresql")
                
                // Kafka Messaging
                add("implementation", "io.quarkus:quarkus-messaging-kafka")
                add("implementation", "io.quarkus:quarkus-confluent-registry-avro")
                
                // Testing
                add("testImplementation", "io.quarkus:quarkus-junit5")
                add("testImplementation", "io.rest-assured:rest-assured")
                add("testImplementation", "org.testcontainers:postgresql:1.19.3")
                add("testImplementation", "org.testcontainers:kafka:1.19.3")
            }
            
            // Common testing dependencies
            add("testImplementation", "org.jetbrains.kotlin:kotlin-test-junit5")
            add("testImplementation", "org.junit.jupiter:junit-jupiter:5.10.1")
            add("testImplementation", "io.mockk:mockk:1.13.8")
            add("testImplementation", "org.assertj:assertj-core:3.24.2")
        }
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
