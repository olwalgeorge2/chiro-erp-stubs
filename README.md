# chiro-erp (stubs)
DDD + Hex + pure EDA. Each folder has a meaningful placeholder.

## 🚀 Quick Start

### Build the Project
```bash
# Build everything (optimized with caching)
.\gradlew build

# Build specific service
.\gradlew :modules:contexts:commerce-service:build

# Run tests
.\gradlew test

# Clean and rebuild
.\gradlew clean build
```

### Development Workflow
1. Edit code in VS Code
2. Build from command line: `.\gradlew build`
3. Run tests: `.\gradlew test`
4. Commit when green ✅

**Note**: Use command line for builds - it's faster and more reliable than IDE.

## 📚 Documentation

- **[GRADLE_OPTIMIZATION_GUIDE.md](GRADLE_OPTIMIZATION_GUIDE.md)** - Complete build optimization guide
- **[IMPLEMENTATION_STATUS.md](IMPLEMENTATION_STATUS.md)** - Current status and performance benchmarks
- **[VSCODE_FIX.md](VSCODE_FIX.md)** - Fix VS Code configuration errors

## 🏗️ Project Structure

```
chiro-erp/
├── modules/
│   ├── platform/          # Shared infrastructure modules
│   │   ├── shared-kernel  # Domain primitives
│   │   ├── messaging      # Kafka/event infrastructure
│   │   ├── security       # Auth/authorization
│   │   ├── observability  # Monitoring/metrics
│   │   └── contracts      # Event schemas (Avro)
│   │
│   └── contexts/          # Microservices (bounded contexts)
│       ├── commerce-service
│       ├── inventory-service
│       ├── customer-relation-service
│       ├── financial-acl-service
│       ├── procurement-service
│       ├── operations-service
│       ├── mfg-execution-service
│       ├── tenancy-identity-service
│       ├── comms-hub-service
│       └── bi-ingestion-service
│
└── gradle/
    └── libs.versions.toml # Centralized dependency management
```

## ⚙️ Build System

- **Gradle 9.0** with Kotlin DSL
- **Version Catalog** for dependency management
- **Build caching** enabled (50-70% faster incremental builds)
- **Incremental Kotlin compilation**
- **Worker parallelism** (4 workers)

### Performance
- Full build: ~6-8 minutes
- Incremental build: ~1-2 minutes
- No-change rebuild: ~30-60 seconds

## 🧪 Testing

```bash
# Run all tests
.\gradlew test

# Run tests for specific service
.\gradlew :modules:contexts:commerce-service:test

# Run with coverage
.\gradlew test jacocoTestReport
```

## 📦 Technology Stack

- **Kotlin 2.2.20**
- **Quarkus 3.29.0** (Fast startup, low memory)
- **PostgreSQL** (Persistence)
- **Kafka + Avro** (Event-driven messaging)
- **Micrometer + Prometheus** (Observability)

## 🔧 Troubleshooting

### VS Code shows configuration errors
This is normal - VS Code's Gradle extension has some compatibility issues. The build works perfectly from command line.

**Fix**: Press `Ctrl+Shift+P` → "Reload Window"

### Build is slow
Make sure build caching is enabled and Gradle daemon is running:
```bash
.\gradlew --status
.\gradlew build --scan  # Get performance report
```

### More help
Run diagnostic: `.\diagnose-gradle.ps1`

## 📖 Architecture Principles

- **Domain-Driven Design (DDD)** - Bounded contexts, ubiquitous language
- **Hexagonal Architecture** - Ports & adapters, clean separation
- **Event-Driven Architecture (EDA)** - Pure event sourcing, eventual consistency
- **Microservices** - Independently deployable services
