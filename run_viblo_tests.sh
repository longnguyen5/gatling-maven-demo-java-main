#!/bin/bash
# Linux/macOS script: Run all Viblo.asia Gatling test cases in sequence

echo "========================================"
echo "Viblo.asia Performance Test Suite"
echo "========================================"
echo

# Array of test classes
VIBLO_TESTS=(
    "viblo_asia.TC_VIBLO_001_Homepage._100_Users"
    "viblo_asia.TC_VIBLO_002_Article_Search._100_Users"
    "viblo_asia.TC_VIBLO_003_Post_Reading._100_Users"
    "viblo_asia.TC_VIBLO_004_User_Profile._100_Users"
    "viblo_asia.TC_VIBLO_005_Tag_Browse._100_Users"
    "viblo_asia.TC_VIBLO_006_Category_Filter._100_Users"
    "viblo_asia.TC_VIBLO_007_Multi_Page_Navigation._100_Users"
    "viblo_asia.TC_VIBLO_008_API_Performance._100_Users"
    "viblo_asia.TC_VIBLO_009_Long_Session._100_Users"
    "viblo_asia.TC_VIBLO_010_Peak_Load_Stress._100_Users"
)

# Initialize counters
TOTAL_TESTS=${#VIBLO_TESTS[@]}
PASSED_TESTS=0
FAILED_TESTS=0
CURRENT_TEST=0

echo "Starting Viblo.asia test execution..."
echo

# Loop through each test
for test_class in "${VIBLO_TESTS[@]}"; do
    ((CURRENT_TEST++))
    echo "[$CURRENT_TEST/$TOTAL_TESTS] Running test: $test_class"
    echo "----------------------------------------"
    
    # Run the test
    mvn gatling:test -Dgatling.simulationClass="$test_class"
    
    # Check result
    if [ $? -eq 0 ]; then
        ((PASSED_TESTS++))
        echo "[SUCCESS] Test $test_class completed successfully."
    else
        ((FAILED_TESTS++))
        echo "[ERROR] Test $test_class failed!"
        read -p "Press Enter to continue or Ctrl+C to abort..."
    fi
    
    echo
    echo "Waiting 30 seconds before next test..."
    echo "----------------------------------------"
    sleep 30
    echo
done

# Display final summary
echo "========================================"
echo "Test Execution Summary"
echo "========================================"
echo "Total Tests Run: $TOTAL_TESTS"
echo "Passed: $PASSED_TESTS"
echo "Failed: $FAILED_TESTS"
echo

if [ $FAILED_TESTS -eq 0 ]; then
    echo "[SUCCESS] All Viblo.asia tests completed successfully!"
    echo "Test results can be found in: target/gatling/"
else
    echo "[WARNING] $FAILED_TESTS test(s) failed. Please check the reports."
    echo "Failed tests should be investigated before production deployment."
fi

echo
echo "Test reports location: target/gatling/"
echo "You can open index.html files to view detailed reports."
echo

read -p "Press Enter to exit..."
