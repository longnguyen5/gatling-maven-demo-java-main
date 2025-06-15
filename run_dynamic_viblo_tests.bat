@echo off
echo ========================================
echo     DYNAMIC VIBLO PERFORMANCE TESTS
echo ========================================
echo.
echo 🎯 Test Suite: Dynamic User Load Testing
echo 📊 All test cases will run with your specified user count
echo ⚡ Adaptive load patterns based on user count
echo.

:: Check if Maven wrapper exists
if not exist "mvnw.cmd" (
    echo ❌ Maven wrapper not found! Make sure you're in the correct directory.
    pause
    exit /b 1
)

:: Set Java memory options
set MAVEN_OPTS=-Xmx3g -XX:+UseG1GC

:INPUT_USERS
echo 👥 Enter the number of users for testing:
echo.
echo 💡 Recommended ranges:
echo   • Light Load: 1-50 users (fast execution)
echo   • Medium Load: 51-200 users (balanced testing)
echo   • Heavy Load: 201-1000 users (stress testing)
echo   • Extreme Load: 1000+ users (maximum stress)
echo.
set /p USER_COUNT="Number of users (1-2000): "

:: Validate input
if not defined USER_COUNT (
    echo ❌ No input provided!
    goto INPUT_USERS
)

:: Check if input is numeric
echo %USER_COUNT%| findstr /r "^[1-9][0-9]*$" >nul
if errorlevel 1 (
    echo ❌ Invalid input! Please enter a valid number.
    goto INPUT_USERS
)

:: Check reasonable range
if %USER_COUNT% lss 1 (
    echo ❌ User count must be at least 1!
    goto INPUT_USERS
)

if %USER_COUNT% gtr 2000 (
    echo ⚠️ Warning: Very high user count (%USER_COUNT%)!
    echo This may take a very long time and consume significant resources.
    set /p confirm="Continue? (Y/N): "
    if /i "!confirm!" neq "Y" goto INPUT_USERS
)

echo.
echo 🎯 Test Configuration:
echo   • User Count: %USER_COUNT%
echo   • Load Pattern: Adaptive (based on user count)
echo   • Test Duration: Variable (10-30 minutes per test)
echo   • Total Tests: 10 test cases
echo.

:: Estimate total time
if %USER_COUNT% leq 50 (
    set "LOAD_TYPE=Light"
    set "EST_TIME=2-3 hours"
) else if %USER_COUNT% leq 200 (
    set "LOAD_TYPE=Medium"
    set "EST_TIME=3-4 hours"
) else (
    set "LOAD_TYPE=Heavy/Stress"
    set "EST_TIME=4-6 hours"
)

echo 📊 Load Classification: %LOAD_TYPE%
echo ⏱️ Estimated Total Time: %EST_TIME%
echo.

set /p confirm="Start dynamic testing with %USER_COUNT% users? (Y/N): "
if /i "%confirm%" neq "Y" goto EXIT

echo.
echo 🚀 Starting Dynamic Viblo Performance Tests...
echo User Count: %USER_COUNT%
echo ========================================

:: Initialize counters
set TOTAL_TESTS=10
set PASSED_TESTS=0
set FAILED_TESTS=0

:: Test 1: Dynamic Homepage Test
echo.
echo [1/10] 🏠 Running Dynamic Homepage Test...
echo ----------------------------------------
call mvnw.cmd clean gatling:test -Dgatling.simulationClass=viblo_asia.TC_VIBLO_DYNAMIC.Dynamic_Homepage_Test -DuserCount=%USER_COUNT%
if %errorlevel% equ 0 (
    echo ✅ Dynamic Homepage Test PASSED
    set /a PASSED_TESTS+=1
) else (
    echo ❌ Dynamic Homepage Test FAILED
    set /a FAILED_TESTS+=1
)

:: Test 2: Dynamic Article Search Test
echo.
echo [2/10] 🔍 Running Dynamic Article Search Test...
echo ----------------------------------------
call mvnw.cmd clean gatling:test -Dgatling.simulationClass=viblo_asia.TC_VIBLO_DYNAMIC.Dynamic_Article_Search_Test -DuserCount=%USER_COUNT%
if %errorlevel% equ 0 (
    echo ✅ Dynamic Article Search Test PASSED
    set /a PASSED_TESTS+=1
) else (
    echo ❌ Dynamic Article Search Test FAILED
    set /a FAILED_TESTS+=1
)

:: Test 3: Dynamic Multi-Page Navigation Test
echo.
echo [3/10] 🧭 Running Dynamic Multi-Page Navigation Test...
echo ----------------------------------------
call mvnw.cmd clean gatling:test -Dgatling.simulationClass=viblo_asia.TC_VIBLO_DYNAMIC.Dynamic_Multi_Page_Test -DuserCount=%USER_COUNT%
if %errorlevel% equ 0 (
    echo ✅ Dynamic Multi-Page Navigation Test PASSED
    set /a PASSED_TESTS+=1
) else (
    echo ❌ Dynamic Multi-Page Navigation Test FAILED
    set /a FAILED_TESTS+=1
)

:: Test 4: Dynamic Post Reading Test
echo.
echo [4/10] � Running Dynamic Post Reading Test...
echo ----------------------------------------
call mvnw.cmd clean gatling:test -Dgatling.simulationClass=viblo_asia.TC_VIBLO_DYNAMIC.Dynamic_Post_Reading_Test -DuserCount=%USER_COUNT%
if %errorlevel% equ 0 (
    echo ✅ Dynamic Post Reading Test PASSED
    set /a PASSED_TESTS+=1
) else (
    echo ❌ Dynamic Post Reading Test FAILED
    set /a FAILED_TESTS+=1
)

:: Test 5: Dynamic User Profile Test
echo.
echo [5/10] 👤 Running Dynamic User Profile Test...
echo ----------------------------------------
call mvnw.cmd clean gatling:test -Dgatling.simulationClass=viblo_asia.TC_VIBLO_DYNAMIC.Dynamic_User_Profile_Test -DuserCount=%USER_COUNT%
if %errorlevel% equ 0 (
    echo ✅ Dynamic User Profile Test PASSED
    set /a PASSED_TESTS+=1
) else (
    echo ❌ Dynamic User Profile Test FAILED
    set /a FAILED_TESTS+=1
)

:: Test 6: Dynamic Tag Browse Test
echo.
echo [6/10] 🏷️ Running Dynamic Tag Browse Test...
echo ----------------------------------------
call mvnw.cmd clean gatling:test -Dgatling.simulationClass=viblo_asia.TC_VIBLO_DYNAMIC.Dynamic_Tag_Browse_Test -DuserCount=%USER_COUNT%
if %errorlevel% equ 0 (
    echo ✅ Dynamic Tag Browse Test PASSED
    set /a PASSED_TESTS+=1
) else (
    echo ❌ Dynamic Tag Browse Test FAILED
    set /a FAILED_TESTS+=1
)

:: Test 7: Dynamic Category Filter Test
echo.
echo [7/10] 📂 Running Dynamic Category Filter Test...
echo ----------------------------------------
call mvnw.cmd clean gatling:test -Dgatling.simulationClass=viblo_asia.TC_VIBLO_DYNAMIC.Dynamic_Category_Filter_Test -DuserCount=%USER_COUNT%
if %errorlevel% equ 0 (
    echo ✅ Dynamic Category Filter Test PASSED
    set /a PASSED_TESTS+=1
) else (
    echo ❌ Dynamic Category Filter Test FAILED
    set /a FAILED_TESTS+=1
)

:: Test 8: Dynamic API Performance Test
echo.
echo [8/10] ⚡ Running Dynamic API Performance Test...
echo ----------------------------------------
call mvnw.cmd clean gatling:test -Dgatling.simulationClass=viblo_asia.TC_VIBLO_DYNAMIC.Dynamic_API_Performance_Test -DuserCount=%USER_COUNT%
if %errorlevel% equ 0 (
    echo ✅ Dynamic API Performance Test PASSED
    set /a PASSED_TESTS+=1
) else (
    echo ❌ Dynamic API Performance Test FAILED
    set /a FAILED_TESTS+=1
)

:: Test 9: Dynamic Long Session Test
echo.
echo [9/10] ⏱️ Running Dynamic Long Session Test...
echo ----------------------------------------
call mvnw.cmd clean gatling:test -Dgatling.simulationClass=viblo_asia.TC_VIBLO_DYNAMIC.Dynamic_Long_Session_Test -DuserCount=%USER_COUNT%
if %errorlevel% equ 0 (
    echo ✅ Dynamic Long Session Test PASSED
    set /a PASSED_TESTS+=1
) else (
    echo ❌ Dynamic Long Session Test FAILED
    set /a FAILED_TESTS+=1
)

:: Test 10: Dynamic Stress Test
echo.
echo [10/10] 🔥 Running Dynamic Stress Test...
echo ----------------------------------------
call mvnw.cmd clean gatling:test -Dgatling.simulationClass=viblo_asia.TC_VIBLO_DYNAMIC.Dynamic_Stress_Test -DuserCount=%USER_COUNT%
if %errorlevel% equ 0 (
    echo ✅ Dynamic Stress Test PASSED
    set /a PASSED_TESTS+=1
) else (
    echo ❌ Dynamic Stress Test FAILED
    set /a FAILED_TESTS+=1
)

:: Display final summary
echo.
echo ========================================
echo 🎉 DYNAMIC TEST SUITE COMPLETED!
echo ========================================
echo.
echo 📊 FINAL RESULTS SUMMARY:
echo   • User Count Tested: %USER_COUNT%
echo   • Load Type: %LOAD_TYPE%
echo   • Total Tests Run: %TOTAL_TESTS%
echo   • Tests Passed: %PASSED_TESTS%
echo   • Tests Failed: %FAILED_TESTS%
echo.

if %FAILED_TESTS% equ 0 (
    echo ✅ ALL TESTS PASSED SUCCESSFULLY!
    echo 🎯 Viblo.asia handles %USER_COUNT% users excellently!
) else (
    echo ⚠️  %FAILED_TESTS% test(s) failed with %USER_COUNT% users.
    echo 🔍 Performance bottlenecks detected at this load level.
)

echo.
echo 📁 TEST RESULTS LOCATION:
echo   • Gatling Reports: target\gatling\
echo   • Dynamic Test Reports: target\gatling\dynamic-*\index.html
echo.
echo 💡 ANALYSIS RECOMMENDATIONS:
if %USER_COUNT% leq 50 (
    echo   • ✅ Light load testing completed
    echo   • 📈 Try testing with 100-200 users for medium load
    echo   • 🎯 System appears to handle light loads well
) else if %USER_COUNT% leq 200 (
    echo   • ✅ Medium load testing completed  
    echo   • 📈 Consider testing with 500+ users for stress testing
    echo   • 🎯 System performance under normal production load
) else (
    echo   • ✅ Heavy/Stress load testing completed
    echo   • 📈 This represents peak or extreme load conditions
    echo   • 🎯 Critical for capacity planning and scaling decisions
)

echo.
echo 📊 Time completed: %date% %time%
echo.

goto EXIT

:EXIT
echo 👋 Thank you for using Dynamic Viblo Performance Testing!
echo 🎯 Happy performance testing!
pause > nul
