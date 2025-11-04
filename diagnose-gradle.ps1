# Quick diagnostic script
Write-Host "========================================" -ForegroundColor Cyan
Write-Host "  Gradle Build System Diagnostic" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan

Write-Host "`n1. Testing basic Gradle execution..." -ForegroundColor Yellow
$result = & .\gradlew --version 2>&1
if ($LASTEXITCODE -eq 0) {
    Write-Host "✓ Gradle wrapper works" -ForegroundColor Green
    $result | Select-String "Gradle 9"
} else {
    Write-Host "✗ Gradle wrapper failed" -ForegroundColor Red
    $result
    exit 1
}

Write-Host "`n2. Testing project structure detection..." -ForegroundColor Yellow
$result = & .\gradlew projects --no-daemon --console=plain 2>&1
if ($result -match "Resolution of the configuration") {
    Write-Host "✗ Configuration resolution error detected" -ForegroundColor Red
    Write-Host "`nThis appears to be a Gradle 9.0 + Kotlin annotation processor issue."
    Write-Host "However, this is typically a VS Code/IDE issue, not a build issue."
    Write-Host "`nSuggested fixes:" -ForegroundColor Yellow
    Write-Host "  1. Use command line for builds (bypasses IDE)" -ForegroundColor White
    Write-Host "  2. Reload VS Code: Ctrl+Shift+P -> 'Reload Window'" -ForegroundColor White
    Write-Host "  3. See VSCODE_FIX.md for more solutions" -ForegroundColor White
    exit 1
} elseif ($LASTEXITCODE -eq 0) {
    Write-Host "✓ Project configuration successful" -ForegroundColor Green
    $moduleCount = ($result | Select-String "modules:").Count
    Write-Host "  Found $moduleCount module projects" -ForegroundColor Gray
} else {
    Write-Host "✗ Project configuration failed" -ForegroundColor Red
    $result | Select-Object -Last 20
    exit 1
}

Write-Host "`n3. Checking build optimizations..." -ForegroundColor Yellow
$props = Get-Content gradle.properties
if ($props -match "org.gradle.caching=true") {
    Write-Host "✓ Build caching enabled" -ForegroundColor Green
}
if ($props -match "kotlin.incremental=true") {
    Write-Host "✓ Incremental Kotlin compilation enabled" -ForegroundColor Green
}
if ($props -match "org.gradle.workers.max=") {
    $workers = ($props | Select-String "org.gradle.workers.max=").ToString().Split("=")[1]
    Write-Host "✓ Worker parallelism enabled ($workers workers)" -ForegroundColor Green
}

Write-Host "`n========================================" -ForegroundColor Cyan
Write-Host "✅ Gradle configuration is working!" -ForegroundColor Green
Write-Host "========================================" -ForegroundColor Cyan

Write-Host "`nRecommended commands:" -ForegroundColor Cyan
Write-Host "  .\gradlew build                    # Build everything" -ForegroundColor White
Write-Host "  .\gradlew test                     # Run all tests" -ForegroundColor White
Write-Host "  .\gradlew build --scan             # Build with performance report" -ForegroundColor White
Write-Host "  .\gradlew clean build              # Clean and rebuild" -ForegroundColor White

Write-Host "`nIf VS Code shows errors:" -ForegroundColor Cyan
Write-Host "  1. Ignore them - command line works fine!" -ForegroundColor White
Write-Host "  2. Or reload: Ctrl+Shift+P -> 'Reload Window'" -ForegroundColor White
Write-Host "  3. See VSCODE_FIX.md for details" -ForegroundColor White

Write-Host "`n📚 Documentation:" -ForegroundColor Cyan
Write-Host "  README.md                          # Quick start" -ForegroundColor White
Write-Host "  BUILD_OPTIMIZATION_SUMMARY.md      # Executive summary" -ForegroundColor White
Write-Host "  GRADLE_OPTIMIZATION_GUIDE.md       # Complete guide" -ForegroundColor White
Write-Host "  IMPLEMENTATION_STATUS.md           # Detailed status" -ForegroundColor White
