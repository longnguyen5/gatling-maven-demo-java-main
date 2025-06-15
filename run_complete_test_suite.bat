@echo off
echo ========================================
echo   COMPLETE PERFORMANCE TEST SUITE
echo ========================================
echo.
echo 🎯 Full Test Suite: VinhUni.edu.vn + Viblo.asia
echo 📊 Total Tests: 30 test cases (15 VinhUni + 15 Viblo)
echo ⏱️ Estimated Duration: ~8-10 hours total
echo.

:: Check if Maven wrapper exists
if not exist "mvnw.cmd" (
    echo ❌ Maven wrapper not found! Make sure you're in the correct directory.
    pause
    exit /b 1
)

:: Set Java memory options
set MAVEN_OPTS=-Xmx3g -XX:+UseG1GC

echo 📋 Available Test Options:
echo.
echo   1. Run VinhUni Tests Only (TC_VU_006-010)
echo   2. Run Viblo Tests Only (TC_VIBLO_001-010)
echo   3. Run ALL Tests (VinhUni + Viblo)
echo   4. Generate Reports from Existing Results
echo   5. Clean All Test Results
echo   6. Exit
echo.

set /p choice="Select option (1-6): "

if "%choice%"=="1" goto VINHUNI_ONLY
if "%choice%"=="2" goto VIBLO_ONLY
if "%choice%"=="3" goto ALL_TESTS
if "%choice%"=="4" goto GENERATE_REPORTS
if "%choice%"=="5" goto CLEAN_RESULTS
if "%choice%"=="6" goto EXIT
goto INVALID

:VINHUNI_ONLY
echo.
echo 🏫 Running VinhUni Performance Tests...
echo ⚠️  This will take approximately 4-5 hours.
echo.
set /p confirm="Continue with VinhUni tests? (Y/N): "
if /i "%confirm%" neq "Y" goto EXIT

echo.
call run_all_gatling_tests.bat
goto GENERATE_REPORTS

:VIBLO_ONLY
echo.
echo 📝 Running Viblo Performance Tests...
echo ⚠️  This will take approximately 3-4 hours.
echo.
set /p confirm="Continue with Viblo tests? (Y/N): "
if /i "%confirm%" neq "Y" goto EXIT

echo.
call run_all_viblo_tests.bat
goto GENERATE_REPORTS

:ALL_TESTS
echo.
echo 🚀 Running COMPLETE Performance Test Suite...
echo ⚠️  This will take approximately 8-10 hours!
echo.
echo 📋 Test Sequence:
echo   Phase 1: VinhUni.edu.vn Tests (4-5 hours)
echo   Phase 2: Viblo.asia Tests (3-4 hours)
echo   Phase 3: Report Generation (15 minutes)
echo.
set /p confirm="Are you sure you want to run ALL tests? (Y/N): "
if /i "%confirm%" neq "Y" goto EXIT

echo.
echo ========================================
echo Phase 1/3: VINHUNI.EDU.VN TESTS
echo ========================================
call run_all_gatling_tests.bat

echo.
echo ========================================
echo Phase 2/3: VIBLO.ASIA TESTS
echo ========================================
call run_all_viblo_tests.bat

echo.
echo ========================================
echo Phase 3/3: REPORT GENERATION
echo ========================================
goto GENERATE_REPORTS

:GENERATE_REPORTS
echo.
echo 📊 Generating Performance Reports...
echo ========================================

:: Check if Python scripts exist
if not exist "generate_test_summary_v2.py" (
    echo ❌ VinhUni summary script not found!
    goto REPORT_ERROR
)

if not exist "generate_viblo_summary.py" (
    echo ❌ Viblo summary script not found!
    goto REPORT_ERROR
)

:: Generate VinhUni CSV summary
echo.
echo 📈 Generating VinhUni test summary...
python generate_test_summary_v2.py
if %errorlevel% neq 0 (
    echo ⚠️  Warning: VinhUni summary generation failed
) else (
    echo ✅ VinhUni CSV summary generated
)

:: Generate Viblo CSV summary
echo.
echo 📈 Generating Viblo test summary...
python generate_viblo_summary.py
if %errorlevel% neq 0 (
    echo ⚠️  Warning: Viblo summary generation failed
) else (
    echo ✅ Viblo CSV summary generated
)

:: Generate VinhUni charts
if exist "generate_test_charts.py" (
    echo.
    echo 📊 Generating VinhUni performance charts...
    python generate_test_charts.py
    if %errorlevel% neq 0 (
        echo ⚠️  Warning: VinhUni charts generation failed
    ) else (
        echo ✅ VinhUni charts generated
    )
)

:: Generate Viblo charts
if exist "generate_viblo_charts.py" (
    echo.
    echo 📊 Generating Viblo performance charts...
    python generate_viblo_charts.py
    if %errorlevel% neq 0 (
        echo ⚠️  Warning: Viblo charts generation failed
    ) else (
        echo ✅ Viblo charts generated
    )
)

goto FINAL_SUMMARY

:REPORT_ERROR
echo ❌ Cannot generate reports - required scripts missing!
echo 💡 Please ensure Python scripts are in the current directory.
goto FINAL_SUMMARY

:CLEAN_RESULTS
echo.
echo 🧹 Cleaning Test Results...
echo ========================================
echo.
echo ⚠️  This will delete ALL existing test results!
echo   • target\gatling\* (all Gatling reports)
echo   • *.csv (all CSV summaries)
echo   • test_charts\* (all VinhUni charts)
echo   • viblo_charts\* (all Viblo charts)
echo.
set /p confirm="Are you sure you want to clean all results? (Y/N): "
if /i "%confirm%" neq "Y" goto EXIT

echo.
echo 🗑️ Removing Gatling reports...
if exist "target\gatling" rmdir /s /q "target\gatling"

echo 🗑️ Removing CSV files...
del /q *.csv 2>nul

echo 🗑️ Removing chart directories...
if exist "test_charts" rmdir /s /q "test_charts"
if exist "viblo_charts" rmdir /s /q "viblo_charts"

echo ✅ Cleanup completed!
goto FINAL_SUMMARY

:FINAL_SUMMARY
echo.
echo ========================================
echo 📊 PERFORMANCE TEST SUITE SUMMARY
echo ========================================
echo.
echo 📁 RESULT LOCATIONS:
echo   • Gatling Reports: target\gatling\
echo   • VinhUni CSV: gatling_test_summary.csv
echo   • Viblo CSV: viblo_test_summary.csv
echo   • VinhUni Charts: test_charts\
echo   • Viblo Charts: viblo_charts\
echo.
echo 💡 KEY FILES TO REVIEW:
echo   1. Overall Performance: Both CSV files
echo   2. Detailed Analysis: HTML reports in target\gatling\
echo   3. Visual Analysis: Chart images in respective folders
echo.
echo 🔍 PERFORMANCE COMPARISON:
echo   • Compare response times between VinhUni vs Viblo
echo   • Analyze throughput patterns
echo   • Identify bottlenecks and optimization opportunities
echo.
echo 📈 NEXT STEPS:
echo   1. Review CSV summaries for quick overview
echo   2. Open HTML reports for detailed metrics
echo   3. Use charts for presentation and analysis
echo   4. Document findings and recommendations
echo.
echo 📊 Completed: %date% %time%
echo.
goto EXIT

:INVALID
echo.
echo ❌ Invalid selection! Please choose 1-6.
echo.
goto :eof

:EXIT
echo 👋 Thank you for using the Complete Performance Test Suite!
echo 🎯 Happy performance testing!
pause
