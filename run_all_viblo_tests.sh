#!/bin/bash

echo "========================================"
echo "   ALL VIBLO.ASIA PERFORMANCE TESTS"
echo "========================================"
echo ""
echo "🎯 Test Suite: Complete Viblo.asia Performance Testing"
echo "📊 Tests: TC_VIBLO_001 to TC_VIBLO_010 (100 users each)"
echo "⏱️ Estimated Duration: ~3-4 hours total"
echo ""

# Check if Maven wrapper exists
if [ ! -f "mvnw" ]; then
    echo "❌ Maven wrapper not found! Make sure you're in the correct directory."
    read -p "Press any key to exit..."
    exit 1
fi

# Set Java memory options
export MAVEN_OPTS="-Xmx3g -XX:+UseG1GC"

echo "⚠️  This will run ALL 10 Viblo test cases sequentially."
echo "   Each test takes approximately 15-20 minutes."
echo ""
read -p "Continue with full Viblo test suite? (Y/N): " confirm
if [[ ! "$confirm" =~ ^[Yy]$ ]]; then
    echo "Test cancelled by user."
    exit 0
fi

echo ""
echo "🚀 Starting Complete Viblo Performance Test Suite..."
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
    local test_icon=$4
    
    echo ""
    echo "[$test_num/10] $test_icon Running $test_name..."
    echo "----------------------------------------"
    
    if ./mvnw clean gatling:test -Dgatling.simulationClass="$test_class"; then
        echo "✅ $test_name PASSED"
        ((PASSED_TESTS++))
    else
        echo "❌ $test_name FAILED"
        ((FAILED_TESTS++))
    fi
}

# Test 1: Homepage Load Test
run_test 1 "TC_VIBLO_001: Homepage Load Test" "viblo_asia.TC_VIBLO_001_Homepage._100_Users" "🏠"

# Test 2: Article Search Test
run_test 2 "TC_VIBLO_002: Article Search Test" "viblo_asia.TC_VIBLO_002_Article_Search._100_Users" "🔍"

# Test 3: Post Reading Test
run_test 3 "TC_VIBLO_003: Post Reading Test" "viblo_asia.TC_VIBLO_003_Post_Reading._100_Users" "📖"

# Test 4: User Profile Test
run_test 4 "TC_VIBLO_004: User Profile Test" "viblo_asia.TC_VIBLO_004_User_Profile._100_Users" "👤"

# Test 5: Tag Browse Test
run_test 5 "TC_VIBLO_005: Tag Browse Test" "viblo_asia.TC_VIBLO_005_Tag_Browse._100_Users" "🏷️"

# Test 6: Category Filter Test
run_test 6 "TC_VIBLO_006: Category Filter Test" "viblo_asia.TC_VIBLO_006_Category_Filter._100_Users" "📂"

# Test 7: Multi Page Navigation Test
run_test 7 "TC_VIBLO_007: Multi Page Navigation Test" "viblo_asia.TC_VIBLO_007_Multi_Page_Navigation._100_Users" "🧭"

# Test 8: API Performance Test
run_test 8 "TC_VIBLO_008: API Performance Test" "viblo_asia.TC_VIBLO_008_API_Performance._100_Users" "⚡"

# Test 9: Long Session Test
run_test 9 "TC_VIBLO_009: Long Session Test" "viblo_asia.TC_VIBLO_009_Long_Session._100_Users" "⏱️"

# Test 10: Peak Load Stress Test
run_test 10 "TC_VIBLO_010: Peak Load Stress Test" "viblo_asia.TC_VIBLO_010_Peak_Load_Stress._100_Users" "🔥"

# Display final summary
echo ""
echo "========================================"
echo "🎉 VIBLO TEST SUITE COMPLETED!"
echo "========================================"
echo ""
echo "📊 FINAL RESULTS SUMMARY:"
echo "  • Total Tests Run: $TOTAL_TESTS"
echo "  • Tests Passed: $PASSED_TESTS"
echo "  • Tests Failed: $FAILED_TESTS"
echo ""

if [ $FAILED_TESTS -eq 0 ]; then
    echo "✅ ALL TESTS PASSED SUCCESSFULLY!"
    echo "🎯 Viblo.asia performance is excellent!"
else
    echo "⚠️  Some tests failed. Please check the detailed reports."
    echo "🔍 Failed tests may indicate performance issues."
fi

echo ""
echo "📁 TEST RESULTS LOCATION:"
echo "  • Gatling Reports: target/gatling/"
echo "  • Individual Reports: target/gatling/tc-viblo-*/index.html"
echo ""
echo "💡 NEXT STEPS:"
echo "  1. Generate CSV summary:"
echo "     python generate_viblo_summary.py"
echo ""
echo "  2. Create performance charts:"
echo "     python generate_viblo_charts.py"
echo ""
echo "  3. Compare with VinhUni results:"
echo "     python compare_performance.py"
echo ""
echo "📊 Time completed: $(date)"
echo ""

echo "👋 Thank you for using Viblo.asia Performance Test Suite!"
read -p "Press any key to exit..."
