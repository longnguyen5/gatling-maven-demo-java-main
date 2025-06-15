@echo off
echo ========================================
echo     TEST SUITE VALIDATION CHECKER
echo ========================================
echo.
echo 🔍 Checking readiness of all test cases...
echo.

:: Initialize counters
set TOTAL_CHECKS=0
set PASSED_CHECKS=0
set FAILED_CHECKS=0

:: Function to check if file exists
set CHECK_FILE=goto :check_file
goto :start_checks

:check_file
set file_path=%~1
set test_name=%~2
set /a TOTAL_CHECKS+=1

if exist "%file_path%" (
    echo ✅ %test_name%
    set /a PASSED_CHECKS+=1
) else (
    echo ❌ %test_name% - FILE MISSING
    set /a FAILED_CHECKS+=1
)
goto :eof

:start_checks

echo 📋 CHECKING VINHUNI TEST CASES:
echo ----------------------------------------
call :check_file "src\test\java\vinhuni_edu_vn\TC_VU_006_PDF_Download\_100_Users.java" "TC_VU_006 - 100 Users"
call :check_file "src\test\java\vinhuni_edu_vn\TC_VU_006_PDF_Download\_200_Users.java" "TC_VU_006 - 200 Users"
call :check_file "src\test\java\vinhuni_edu_vn\TC_VU_006_PDF_Download\_400_Users.java" "TC_VU_006 - 400 Users"
call :check_file "src\test\java\vinhuni_edu_vn\TC_VU_006_PDF_Download\_800_Users.java" "TC_VU_006 - 800 Users"
call :check_file "src\test\java\vinhuni_edu_vn\TC_VU_006_PDF_Download\_1000_Users.java" "TC_VU_006 - 1000 Users"

call :check_file "src\test\java\vinhuni_edu_vn\TC_VU_007_Multi_Page_Navigation\_100_Users.java" "TC_VU_007 - 100 Users"
call :check_file "src\test\java\vinhuni_edu_vn\TC_VU_007_Multi_Page_Navigation\_200_Users.java" "TC_VU_007 - 200 Users"
call :check_file "src\test\java\vinhuni_edu_vn\TC_VU_007_Multi_Page_Navigation\_400_Users.java" "TC_VU_007 - 400 Users"
call :check_file "src\test\java\vinhuni_edu_vn\TC_VU_007_Multi_Page_Navigation\_800_Users.java" "TC_VU_007 - 800 Users"
call :check_file "src\test\java\vinhuni_edu_vn\TC_VU_007_Multi_Page_Navigation\_1000_Users.java" "TC_VU_007 - 1000 Users"

call :check_file "src\test\java\vinhuni_edu_vn\TC_VU_008_Long_Session\_100_Users.java" "TC_VU_008 - 100 Users"
call :check_file "src\test\java\vinhuni_edu_vn\TC_VU_008_Long_Session\_200_Users.java" "TC_VU_008 - 200 Users"
call :check_file "src\test\java\vinhuni_edu_vn\TC_VU_008_Long_Session\_400_Users.java" "TC_VU_008 - 400 Users"
call :check_file "src\test\java\vinhuni_edu_vn\TC_VU_008_Long_Session\_800_Users.java" "TC_VU_008 - 800 Users"
call :check_file "src\test\java\vinhuni_edu_vn\TC_VU_008_Long_Session\_1000_Users.java" "TC_VU_008 - 1000 Users"

call :check_file "src\test\java\vinhuni_edu_vn\TC_VU_009_Search_Download_Combo\_100_Users.java" "TC_VU_009 - 100 Users"
call :check_file "src\test\java\vinhuni_edu_vn\TC_VU_009_Search_Download_Combo\_200_Users.java" "TC_VU_009 - 200 Users"
call :check_file "src\test\java\vinhuni_edu_vn\TC_VU_009_Search_Download_Combo\_400_Users.java" "TC_VU_009 - 400 Users"
call :check_file "src\test\java\vinhuni_edu_vn\TC_VU_009_Search_Download_Combo\_800_Users.java" "TC_VU_009 - 800 Users"
call :check_file "src\test\java\vinhuni_edu_vn\TC_VU_009_Search_Download_Combo\_1000_Users.java" "TC_VU_009 - 1000 Users"

call :check_file "src\test\java\vinhuni_edu_vn\TC_VU_010_Peak_Load_Stress\_100_Users.java" "TC_VU_010 - 100 Users"
call :check_file "src\test\java\vinhuni_edu_vn\TC_VU_010_Peak_Load_Stress\_200_Users.java" "TC_VU_010 - 200 Users"
call :check_file "src\test\java\vinhuni_edu_vn\TC_VU_010_Peak_Load_Stress\_400_Users.java" "TC_VU_010 - 400 Users"
call :check_file "src\test\java\vinhuni_edu_vn\TC_VU_010_Peak_Load_Stress\_800_Users.java" "TC_VU_010 - 800 Users"
call :check_file "src\test\java\vinhuni_edu_vn\TC_VU_010_Peak_Load_Stress\_1000_Users.java" "TC_VU_010 - 1000 Users"

echo.
echo 📋 CHECKING VIBLO TEST CASES:
echo ----------------------------------------
call :check_file "src\test\java\viblo_asia\TC_VIBLO_001_Homepage\_100_Users.java" "TC_VIBLO_001 - Homepage"
call :check_file "src\test\java\viblo_asia\TC_VIBLO_002_Article_Search\_100_Users.java" "TC_VIBLO_002 - Article Search"
call :check_file "src\test\java\viblo_asia\TC_VIBLO_003_Post_Reading\_100_Users.java" "TC_VIBLO_003 - Post Reading"
call :check_file "src\test\java\viblo_asia\TC_VIBLO_004_User_Profile\_100_Users.java" "TC_VIBLO_004 - User Profile"
call :check_file "src\test\java\viblo_asia\TC_VIBLO_005_Tag_Browse\_100_Users.java" "TC_VIBLO_005 - Tag Browse"
call :check_file "src\test\java\viblo_asia\TC_VIBLO_006_Category_Filter\_100_Users.java" "TC_VIBLO_006 - Category Filter"
call :check_file "src\test\java\viblo_asia\TC_VIBLO_007_Multi_Page_Navigation\_100_Users.java" "TC_VIBLO_007 - Multi Page Nav"
call :check_file "src\test\java\viblo_asia\TC_VIBLO_008_API_Performance\_100_Users.java" "TC_VIBLO_008 - API Performance"
call :check_file "src\test\java\viblo_asia\TC_VIBLO_009_Long_Session\_100_Users.java" "TC_VIBLO_009 - Long Session"
call :check_file "src\test\java\viblo_asia\TC_VIBLO_010_Peak_Load_Stress\_100_Users.java" "TC_VIBLO_010 - Peak Load Stress"

echo.
echo 📋 CHECKING EXECUTION SCRIPTS:
echo ----------------------------------------
call :check_file "run_all_gatling_tests.bat" "VinhUni Test Runner (Windows)"
call :check_file "run_all_viblo_tests.bat" "Viblo Test Runner (Windows)"
call :check_file "run_all_viblo_tests.sh" "Viblo Test Runner (Unix/Linux)"
call :check_file "run_complete_test_suite.bat" "Complete Suite Runner (Windows)"
call :check_file "run_complete_test_suite.sh" "Complete Suite Runner (Unix/Linux)"

echo.
echo 📋 CHECKING ANALYSIS SCRIPTS:
echo ----------------------------------------
call :check_file "generate_test_summary_v2.py" "VinhUni Summary Generator"
call :check_file "generate_viblo_summary.py" "Viblo Summary Generator"
call :check_file "generate_test_charts.py" "VinhUni Chart Generator"
call :check_file "generate_viblo_charts.py" "Viblo Chart Generator"
call :check_file "setup_venv.bat" "Python Environment Setup"

echo.
echo 📋 CHECKING SYSTEM REQUIREMENTS:
echo ----------------------------------------

:: Check Java
java -version >nul 2>&1
if %errorlevel% equ 0 (
    echo ✅ Java Runtime Environment
    set /a PASSED_CHECKS+=1
) else (
    echo ❌ Java Runtime Environment - NOT FOUND
    set /a FAILED_CHECKS+=1
)
set /a TOTAL_CHECKS+=1

:: Check Maven wrapper
if exist "mvnw.cmd" (
    echo ✅ Maven Wrapper (Windows)
    set /a PASSED_CHECKS+=1
) else (
    echo ❌ Maven Wrapper (Windows) - NOT FOUND
    set /a FAILED_CHECKS+=1
)
set /a TOTAL_CHECKS+=1

:: Check Python
python --version >nul 2>&1
if %errorlevel% equ 0 (
    echo ✅ Python Runtime
    set /a PASSED_CHECKS+=1
) else (
    echo ❌ Python Runtime - NOT FOUND
    set /a FAILED_CHECKS+=1
)
set /a TOTAL_CHECKS+=1

:: Check Python packages
python -c "import pandas, matplotlib, seaborn, numpy" >nul 2>&1
if %errorlevel% equ 0 (
    echo ✅ Python Dependencies (pandas, matplotlib, seaborn, numpy)
    set /a PASSED_CHECKS+=1
) else (
    echo ❌ Python Dependencies - MISSING (run setup_venv.bat)
    set /a FAILED_CHECKS+=1
)
set /a TOTAL_CHECKS+=1

echo.
echo ========================================
echo 📊 VALIDATION SUMMARY
echo ========================================
echo.
echo 📈 Total Checks: %TOTAL_CHECKS%
echo ✅ Passed: %PASSED_CHECKS%
echo ❌ Failed: %FAILED_CHECKS%
echo.

if %FAILED_CHECKS% equ 0 (
    echo 🎉 ALL CHECKS PASSED!
    echo ✅ Test suite is ready to run
    echo.
    echo 🚀 You can now execute:
    echo   • run_complete_test_suite.bat ^(complete suite^)
    echo   • run_all_gatling_tests.bat ^(VinhUni only^)
    echo   • run_all_viblo_tests.bat ^(Viblo only^)
) else (
    echo ⚠️  VALIDATION FAILED!
    echo 🔧 Please fix the missing components before running tests
    echo.
    echo 💡 Common fixes:
    echo   • Install Java 11+ if missing
    echo   • Install Python 3.7+ if missing
    echo   • Run setup_venv.bat for Python dependencies
    echo   • Ensure all test files are present
)

echo.
echo 📋 For detailed setup instructions, see: EXECUTION_GUIDE.md
echo.
pause
