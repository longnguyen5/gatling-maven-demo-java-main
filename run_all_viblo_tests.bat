@echo off
echo ========================================
echo    ALL VIBLO.ASIA PERFORMANCE TESTS
echo ========================================
echo.
echo 🎯 Test Suite: Complete Viblo.asia Performance Testing
echo 📊 Tests: TC_VIBLO_001 to TC_VIBLO_010 (100 users each)
echo ⏱️ Estimated Duration: ~3-4 hours total
echo.

:: Check if Maven wrapper exists
if not exist "mvnw.cmd" (
    echo ❌ Maven wrapper not found! Make sure you're in the correct directory.
    pause
    exit /b 1
)

:: Set Java memory options
set MAVEN_OPTS=-Xmx3g -XX:+UseG1GC

echo ⚠️  This will run ALL 10 Viblo test cases sequentially.
echo    Each test takes approximately 15-20 minutes.
echo.
set /p confirm="Continue with full Viblo test suite? (Y/N): "
if /i "%confirm%" neq "Y" goto EXIT

echo.
echo 🚀 Starting Complete Viblo Performance Test Suite...
echo ========================================

:: Initialize counters
set TOTAL_TESTS=10
set PASSED_TESTS=0
set FAILED_TESTS=0

:: Test 1: Homepage Load Test
echo.
echo [1/10] 🏠 Running TC_VIBLO_001: Homepage Load Test...
echo ----------------------------------------
call mvnw.cmd clean gatling:test -Dgatling.simulationClass=viblo_asia.TC_VIBLO_001_Homepage._100_Users
if %errorlevel% equ 0 (
    echo ✅ TC_VIBLO_001 PASSED
    set /a PASSED_TESTS+=1
) else (
    echo ❌ TC_VIBLO_001 FAILED
    set /a FAILED_TESTS+=1
)

:: Test 2: Article Search Test
echo.
echo [2/10] 🔍 Running TC_VIBLO_002: Article Search Test...
echo ----------------------------------------
call mvnw.cmd clean gatling:test -Dgatling.simulationClass=viblo_asia.TC_VIBLO_002_Article_Search._100_Users
if %errorlevel% equ 0 (
    echo ✅ TC_VIBLO_002 PASSED
    set /a PASSED_TESTS+=1
) else (
    echo ❌ TC_VIBLO_002 FAILED
    set /a FAILED_TESTS+=1
)

:: Test 3: Post Reading Test
echo.
echo [3/10] 📖 Running TC_VIBLO_003: Post Reading Test...
echo ----------------------------------------
call mvnw.cmd clean gatling:test -Dgatling.simulationClass=viblo_asia.TC_VIBLO_003_Post_Reading._100_Users
if %errorlevel% equ 0 (
    echo ✅ TC_VIBLO_003 PASSED
    set /a PASSED_TESTS+=1
) else (
    echo ❌ TC_VIBLO_003 FAILED
    set /a FAILED_TESTS+=1
)

:: Test 4: User Profile Test
echo.
echo [4/10] 👤 Running TC_VIBLO_004: User Profile Test...
echo ----------------------------------------
call mvnw.cmd clean gatling:test -Dgatling.simulationClass=viblo_asia.TC_VIBLO_004_User_Profile._100_Users
if %errorlevel% equ 0 (
    echo ✅ TC_VIBLO_004 PASSED
    set /a PASSED_TESTS+=1
) else (
    echo ❌ TC_VIBLO_004 FAILED
    set /a FAILED_TESTS+=1
)

:: Test 5: Tag Browse Test
echo.
echo [5/10] 🏷️ Running TC_VIBLO_005: Tag Browse Test...
echo ----------------------------------------
call mvnw.cmd clean gatling:test -Dgatling.simulationClass=viblo_asia.TC_VIBLO_005_Tag_Browse._100_Users
if %errorlevel% equ 0 (
    echo ✅ TC_VIBLO_005 PASSED
    set /a PASSED_TESTS+=1
) else (
    echo ❌ TC_VIBLO_005 FAILED
    set /a FAILED_TESTS+=1
)

:: Test 6: Category Filter Test
echo.
echo [6/10] 📂 Running TC_VIBLO_006: Category Filter Test...
echo ----------------------------------------
call mvnw.cmd clean gatling:test -Dgatling.simulationClass=viblo_asia.TC_VIBLO_006_Category_Filter._100_Users
if %errorlevel% equ 0 (
    echo ✅ TC_VIBLO_006 PASSED
    set /a PASSED_TESTS+=1
) else (
    echo ❌ TC_VIBLO_006 FAILED
    set /a FAILED_TESTS+=1
)

:: Test 7: Multi Page Navigation Test
echo.
echo [7/10] 🧭 Running TC_VIBLO_007: Multi Page Navigation Test...
echo ----------------------------------------
call mvnw.cmd clean gatling:test -Dgatling.simulationClass=viblo_asia.TC_VIBLO_007_Multi_Page_Navigation._100_Users
if %errorlevel% equ 0 (
    echo ✅ TC_VIBLO_007 PASSED
    set /a PASSED_TESTS+=1
) else (
    echo ❌ TC_VIBLO_007 FAILED
    set /a FAILED_TESTS+=1
)

:: Test 8: API Performance Test
echo.
echo [8/10] ⚡ Running TC_VIBLO_008: API Performance Test...
echo ----------------------------------------
call mvnw.cmd clean gatling:test -Dgatling.simulationClass=viblo_asia.TC_VIBLO_008_API_Performance._100_Users
if %errorlevel% equ 0 (
    echo ✅ TC_VIBLO_008 PASSED
    set /a PASSED_TESTS+=1
) else (
    echo ❌ TC_VIBLO_008 FAILED
    set /a FAILED_TESTS+=1
)

:: Test 9: Long Session Test
echo.
echo [9/10] ⏱️ Running TC_VIBLO_009: Long Session Test...
echo ----------------------------------------
call mvnw.cmd clean gatling:test -Dgatling.simulationClass=viblo_asia.TC_VIBLO_009_Long_Session._100_Users
if %errorlevel% equ 0 (
    echo ✅ TC_VIBLO_009 PASSED
    set /a PASSED_TESTS+=1
) else (
    echo ❌ TC_VIBLO_009 FAILED
    set /a FAILED_TESTS+=1
)

:: Test 10: Peak Load Stress Test
echo.
echo [10/10] 🔥 Running TC_VIBLO_010: Peak Load Stress Test...
echo ----------------------------------------
call mvnw.cmd clean gatling:test -Dgatling.simulationClass=viblo_asia.TC_VIBLO_010_Peak_Load_Stress._100_Users
if %errorlevel% equ 0 (
    echo ✅ TC_VIBLO_010 PASSED
    set /a PASSED_TESTS+=1
) else (
    echo ❌ TC_VIBLO_010 FAILED
    set /a FAILED_TESTS+=1
)

:: Display final summary
echo.
echo ========================================
echo 🎉 VIBLO TEST SUITE COMPLETED!
echo ========================================
echo.
echo 📊 FINAL RESULTS SUMMARY:
echo   • Total Tests Run: %TOTAL_TESTS%
echo   • Tests Passed: %PASSED_TESTS%
echo   • Tests Failed: %FAILED_TESTS%
echo.

if %FAILED_TESTS% equ 0 (
    echo ✅ ALL TESTS PASSED SUCCESSFULLY!
    echo 🎯 Viblo.asia performance is excellent!
) else (
    echo ⚠️  Some tests failed. Please check the detailed reports.
    echo 🔍 Failed tests may indicate performance issues.
)

echo.
echo 📁 TEST RESULTS LOCATION:
echo   • Gatling Reports: target\gatling\
echo   • Individual Reports: target\gatling\tc-viblo-*\index.html
echo.
echo 💡 NEXT STEPS:
echo   1. Generate CSV summary:
echo      python generate_viblo_summary.py
echo.
echo   2. Create performance charts:
echo      python generate_viblo_charts.py
echo.
echo   3. Compare with VinhUni results:
echo      python compare_performance.py
echo.
echo 📊 Time completed: %date% %time%
echo.

goto EXIT

:EXIT
echo 👋 Thank you for using Viblo.asia Performance Test Suite!
echo Press any key to exit...
pause > nul
