#!/bin/bash

echo "========================================"
echo "    DYNAMIC VIBLO PERFORMANCE TESTS"
echo "========================================"
echo ""
echo "🎯 Test Suite: Dynamic User Load Testing"
echo "📊 All test cases will run with your specified user count"
echo "⚡ Adaptive load patterns based on user count"
echo ""

# Check if Maven wrapper exists
if [ ! -f "mvnw" ]; then
    echo "❌ Maven wrapper not found! Make sure you're in the correct directory."
    read -p "Press any key to exit..."
    exit 1
fi

# Set Java memory options
export MAVEN_OPTS="-Xmx3g -XX:+UseG1GC"

# Function to get user input
get_user_count() {
    while true; do
        echo "👥 Enter the number of users for testing:"
        echo ""
        echo "💡 Recommended ranges:"
        echo "  • Light Load: 1-50 users (fast execution)"
        echo "  • Medium Load: 51-200 users (balanced testing)"
        echo "  • Heavy Load: 201-1000 users (stress testing)"
        echo "  • Extreme Load: 1000+ users (maximum stress)"
        echo ""
        read -p "Number of users (1-2000): " USER_COUNT
        
        # Validate input
        if ! [[ "$USER_COUNT" =~ ^[0-9]+$ ]] || [ "$USER_COUNT" -lt 1 ]; then
            echo "❌ Invalid input! Please enter a valid number (1 or greater)."
            continue
        fi
        
        if [ "$USER_COUNT" -gt 2000 ]; then
            echo "⚠️ Warning: Very high user count ($USER_COUNT)!"
            echo "This may take a very long time and consume significant resources."
            read -p "Continue? (Y/N): " confirm
            if [[ ! "$confirm" =~ ^[Yy]$ ]]; then
                continue
            fi
        fi
        
        break
    done
}

# Get user count
get_user_count

echo ""
echo "🎯 Test Configuration:"
echo "  • User Count: $USER_COUNT"
echo "  • Load Pattern: Adaptive (based on user count)"
echo "  • Test Duration: Variable (10-30 minutes per test)"
echo "  • Total Tests: 10 test cases"
echo ""

# Estimate total time and load type
if [ "$USER_COUNT" -le 50 ]; then
    LOAD_TYPE="Light"
    EST_TIME="2-3 hours"
elif [ "$USER_COUNT" -le 200 ]; then
    LOAD_TYPE="Medium"
    EST_TIME="3-4 hours"
else
    LOAD_TYPE="Heavy/Stress"
    EST_TIME="4-6 hours"
fi

echo "📊 Load Classification: $LOAD_TYPE"
echo "⏱️ Estimated Total Time: $EST_TIME"
echo ""

read -p "Start dynamic testing with $USER_COUNT users? (Y/N): " confirm
if [[ ! "$confirm" =~ ^[Yy]$ ]]; then
    echo "Test cancelled by user."
    exit 0
fi

echo ""
echo "🚀 Starting Dynamic Viblo Performance Tests..."
echo "User Count: $USER_COUNT"
echo "========================================"

# Initialize counters
TOTAL_TESTS=10
PASSED_TESTS=0
FAILED_TESTS=0

# Function to run test and update counters
run_test() {
    local test_num=$1
    local test_name=$2
    local test_class=$3
    local user_count_param=$4
    
    echo ""
    echo "[$test_num/10] Running $test_name..."
    echo "----------------------------------------"
    
    if [ "$user_count_param" = "dynamic" ]; then
        if ./mvnw clean gatling:test -Dgatling.simulationClass="$test_class" -DuserCount="$USER_COUNT"; then
            echo "✅ $test_name PASSED"
            ((PASSED_TESTS++))
        else
            echo "❌ $test_name FAILED"
            ((FAILED_TESTS++))
        fi
    else
        if ./mvnw clean gatling:test -Dgatling.simulationClass="$test_class"; then
            echo "✅ $test_name PASSED"
            ((PASSED_TESTS++))
        else
            echo "❌ $test_name FAILED"
            ((FAILED_TESTS++))
        fi
    fi
}

# Run all 10 dynamic tests
run_test 1 "🏠 Dynamic Homepage Test" "viblo_asia.TC_VIBLO_DYNAMIC.Dynamic_Homepage_Test" "dynamic"
run_test 2 "🔍 Dynamic Article Search Test" "viblo_asia.TC_VIBLO_DYNAMIC.Dynamic_Article_Search_Test" "dynamic"
run_test 3 "🧭 Dynamic Multi-Page Navigation Test" "viblo_asia.TC_VIBLO_DYNAMIC.Dynamic_Multi_Page_Test" "dynamic"
run_test 4 "� Dynamic Post Reading Test" "viblo_asia.TC_VIBLO_DYNAMIC.Dynamic_Post_Reading_Test" "dynamic"
run_test 5 "👤 Dynamic User Profile Test" "viblo_asia.TC_VIBLO_DYNAMIC.Dynamic_User_Profile_Test" "dynamic"
run_test 6 "🏷️ Dynamic Tag Browse Test" "viblo_asia.TC_VIBLO_DYNAMIC.Dynamic_Tag_Browse_Test" "dynamic"
run_test 7 "📂 Dynamic Category Filter Test" "viblo_asia.TC_VIBLO_DYNAMIC.Dynamic_Category_Filter_Test" "dynamic"
run_test 8 "⚡ Dynamic API Performance Test" "viblo_asia.TC_VIBLO_DYNAMIC.Dynamic_API_Performance_Test" "dynamic"
run_test 9 "⏱️ Dynamic Long Session Test" "viblo_asia.TC_VIBLO_DYNAMIC.Dynamic_Long_Session_Test" "dynamic"
run_test 10 "🔥 Dynamic Stress Test" "viblo_asia.TC_VIBLO_DYNAMIC.Dynamic_Stress_Test" "dynamic"

# Display final summary
echo ""
echo "========================================"
echo "🎉 DYNAMIC TEST SUITE COMPLETED!"
echo "========================================"
echo ""
echo "📊 FINAL RESULTS SUMMARY:"
echo "  • User Count Tested: $USER_COUNT"
echo "  • Load Type: $LOAD_TYPE"
echo "  • Total Tests Run: $TOTAL_TESTS"
echo "  • Tests Passed: $PASSED_TESTS"
echo "  • Tests Failed: $FAILED_TESTS"
echo ""

if [ "$FAILED_TESTS" -eq 0 ]; then
    echo "✅ ALL TESTS PASSED SUCCESSFULLY!"
    echo "🎯 Viblo.asia handles $USER_COUNT users excellently!"
else
    echo "⚠️  $FAILED_TESTS test(s) failed with $USER_COUNT users."
    echo "🔍 Performance bottlenecks detected at this load level."
fi

echo ""
echo "📁 TEST RESULTS LOCATION:"
echo "  • Gatling Reports: target/gatling/"
echo "  • Dynamic Test Reports: target/gatling/dynamic-*/index.html"
echo ""
echo "💡 ANALYSIS RECOMMENDATIONS:"
if [ "$USER_COUNT" -le 50 ]; then
    echo "  • ✅ Light load testing completed"
    echo "  • 📈 Try testing with 100-200 users for medium load"
    echo "  • 🎯 System appears to handle light loads well"
elif [ "$USER_COUNT" -le 200 ]; then
    echo "  • ✅ Medium load testing completed"
    echo "  • 📈 Consider testing with 500+ users for stress testing"
    echo "  • 🎯 System performance under normal production load"
else
    echo "  • ✅ Heavy/Stress load testing completed"
    echo "  • 📈 This represents peak or extreme load conditions"
    echo "  • 🎯 Critical for capacity planning and scaling decisions"
fi

echo ""
echo "📊 Time completed: $(date)"
echo ""

echo "👋 Thank you for using Dynamic Viblo Performance Testing!"
echo "🎯 Happy performance testing!"
read -p "Press any key to exit..."
