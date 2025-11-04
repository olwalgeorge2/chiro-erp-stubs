# Gradle Build Test Script
# Run this to test the build configuration

Write-Host "🔧 Testing Gradle Configuration..." -ForegroundColor Cyan

# Step 1: Check Gradle version
Write-Host "`n📦 Gradle Version:" -ForegroundColor Yellow
.\gradlew --version

# Step 2: List all projects
Write-Host "`n📋 Listing all projects..." -ForegroundColor Yellow
.\gradlew projects --no-daemon

# Step 3: Try a simple build task
Write-Host "`n🏗️  Testing build configuration..." -ForegroundColor Yellow
.\gradlew help --no-daemon

Write-Host "`n✅ Basic configuration test complete!" -ForegroundColor Green
Write-Host "`nNext steps:" -ForegroundColor Cyan
Write-Host "  1. Run: .\gradlew clean build --scan" -ForegroundColor White
Write-Host "  2. Check build scan URL for performance details" -ForegroundColor White
Write-Host "  3. Review IMPLEMENTATION_STATUS.md for current status" -ForegroundColor White
