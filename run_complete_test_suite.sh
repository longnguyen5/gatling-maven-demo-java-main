#!/bin/bash

echo "========================================"
echo "  COMPLETE PERFORMANCE TEST SUITE"
echo "========================================"
echo ""
echo "🎯 Full Test Suite: VinhUni.edu.vn + Viblo.asia"
echo "📊 Total Tests: 30 test cases (15 VinhUni + 15 Viblo)"
echo "⏱️ Estimated Duration: ~8-10 hours total"
echo ""

# Check if Maven wrapper exists
if [ ! -f "mvnw" ]; then
    echo "❌ Maven wrapper not found! Make sure you're in the correct directory."
    read -p "Press any key to exit..."
    exit 1
fi

# Set Java memory options
export MAVEN_OPTS="-Xmx3g -XX:+UseG1GC"

echo "📋 Available Test Options:"
echo ""
echo "  1. Run VinhUni Tests Only (TC_VU_006-010)"
echo "  2. Run Viblo Tests Only (TC_VIBLO_001-010)"
echo "  3. Run ALL Tests (VinhUni + Viblo)"
echo "  4. Generate Reports from Existing Results"
echo "  5. Clean All Test Results"
echo "  6. Exit"
echo ""

read -p "Select option (1-6): " choice

case $choice in
    1)
        echo ""
        echo "🏫 Running VinhUni Performance Tests..."
        echo "⚠️  This will take approximately 4-5 hours."
        echo ""
        read -p "Continue with VinhUni tests? (Y/N): " confirm
        if [[ ! "$confirm" =~ ^[Yy]$ ]]; then
            echo "Test cancelled by user."
            exit 0
        fi
        
        echo ""
        if [ -f "run_all_gatling_tests.sh" ]; then
            chmod +x run_all_gatling_tests.sh
            ./run_all_gatling_tests.sh
        else
            echo "❌ VinhUni test script not found!"
            exit 1
        fi
        ;;
    
    2)
        echo ""
        echo "📝 Running Viblo Performance Tests..."
        echo "⚠️  This will take approximately 3-4 hours."
        echo ""
        read -p "Continue with Viblo tests? (Y/N): " confirm
        if [[ ! "$confirm" =~ ^[Yy]$ ]]; then
            echo "Test cancelled by user."
            exit 0
        fi
        
        echo ""
        chmod +x run_all_viblo_tests.sh
        ./run_all_viblo_tests.sh
        ;;
    
    3)
        echo ""
        echo "🚀 Running COMPLETE Performance Test Suite..."
        echo "⚠️  This will take approximately 8-10 hours!"
        echo ""
        echo "📋 Test Sequence:"
        echo "  Phase 1: VinhUni.edu.vn Tests (4-5 hours)"
        echo "  Phase 2: Viblo.asia Tests (3-4 hours)"
        echo "  Phase 3: Report Generation (15 minutes)"
        echo ""
        read -p "Are you sure you want to run ALL tests? (Y/N): " confirm
        if [[ ! "$confirm" =~ ^[Yy]$ ]]; then
            echo "Test cancelled by user."
            exit 0
        fi
        
        echo ""
        echo "========================================"
        echo "Phase 1/3: VINHUNI.EDU.VN TESTS"
        echo "========================================"
        if [ -f "run_all_gatling_tests.sh" ]; then
            chmod +x run_all_gatling_tests.sh
            ./run_all_gatling_tests.sh
        else
            echo "❌ VinhUni test script not found!"
        fi
        
        echo ""
        echo "========================================"
        echo "Phase 2/3: VIBLO.ASIA TESTS"
        echo "========================================"
        chmod +x run_all_viblo_tests.sh
        ./run_all_viblo_tests.sh
        
        echo ""
        echo "========================================"
        echo "Phase 3/3: REPORT GENERATION"
        echo "========================================"
        ;;
    
    4)
        echo ""
        echo "📊 Generating Performance Reports..."
        echo "========================================"
        ;;
    
    5)
        echo ""
        echo "🧹 Cleaning Test Results..."
        echo "========================================"
        echo ""
        echo "⚠️  This will delete ALL existing test results!"
        echo "  • target/gatling/* (all Gatling reports)"
        echo "  • *.csv (all CSV summaries)"
        echo "  • test_charts/* (all VinhUni charts)"
        echo "  • viblo_charts/* (all Viblo charts)"
        echo ""
        read -p "Are you sure you want to clean all results? (Y/N): " confirm
        if [[ ! "$confirm" =~ ^[Yy]$ ]]; then
            echo "Cleanup cancelled by user."
            exit 0
        fi
        
        echo ""
        echo "🗑️ Removing Gatling reports..."
        rm -rf target/gatling 2>/dev/null
        
        echo "🗑️ Removing CSV files..."
        rm -f *.csv 2>/dev/null
        
        echo "🗑️ Removing chart directories..."
        rm -rf test_charts 2>/dev/null
        rm -rf viblo_charts 2>/dev/null
        
        echo "✅ Cleanup completed!"
        ;;
    
    6)
        echo "👋 Thank you for using the Complete Performance Test Suite!"
        echo "🎯 Happy performance testing!"
        exit 0
        ;;
    
    *)
        echo ""
        echo "❌ Invalid selection! Please choose 1-6."
        exit 1
        ;;
esac

# Generate reports function
generate_reports() {
    # Check if Python scripts exist
    if [ ! -f "generate_test_summary_v2.py" ]; then
        echo "❌ VinhUni summary script not found!"
        return 1
    fi
    
    if [ ! -f "generate_viblo_summary.py" ]; then
        echo "❌ Viblo summary script not found!"
        return 1
    fi
    
    # Generate VinhUni CSV summary
    echo ""
    echo "📈 Generating VinhUni test summary..."
    if python generate_test_summary_v2.py; then
        echo "✅ VinhUni CSV summary generated"
    else
        echo "⚠️  Warning: VinhUni summary generation failed"
    fi
    
    # Generate Viblo CSV summary
    echo ""
    echo "📈 Generating Viblo test summary..."
    if python generate_viblo_summary.py; then
        echo "✅ Viblo CSV summary generated"
    else
        echo "⚠️  Warning: Viblo summary generation failed"
    fi
    
    # Generate VinhUni charts
    if [ -f "generate_test_charts.py" ]; then
        echo ""
        echo "📊 Generating VinhUni performance charts..."
        if python generate_test_charts.py; then
            echo "✅ VinhUni charts generated"
        else
            echo "⚠️  Warning: VinhUni charts generation failed"
        fi
    fi
    
    # Generate Viblo charts
    if [ -f "generate_viblo_charts.py" ]; then
        echo ""
        echo "📊 Generating Viblo performance charts..."
        if python generate_viblo_charts.py; then
            echo "✅ Viblo charts generated"
        else
            echo "⚠️  Warning: Viblo charts generation failed"
        fi
    fi
    
    return 0
}

# Call generate_reports if option 3 or 4 was selected
if [ "$choice" == "3" ] || [ "$choice" == "4" ]; then
    generate_reports
fi

# Final summary
echo ""
echo "========================================"
echo "📊 PERFORMANCE TEST SUITE SUMMARY"
echo "========================================"
echo ""
echo "📁 RESULT LOCATIONS:"
echo "  • Gatling Reports: target/gatling/"
echo "  • VinhUni CSV: gatling_test_summary.csv"
echo "  • Viblo CSV: viblo_test_summary.csv"
echo "  • VinhUni Charts: test_charts/"
echo "  • Viblo Charts: viblo_charts/"
echo ""
echo "💡 KEY FILES TO REVIEW:"
echo "  1. Overall Performance: Both CSV files"
echo "  2. Detailed Analysis: HTML reports in target/gatling/"
echo "  3. Visual Analysis: Chart images in respective folders"
echo ""
echo "🔍 PERFORMANCE COMPARISON:"
echo "  • Compare response times between VinhUni vs Viblo"
echo "  • Analyze throughput patterns"
echo "  • Identify bottlenecks and optimization opportunities"
echo ""
echo "📈 NEXT STEPS:"
echo "  1. Review CSV summaries for quick overview"
echo "  2. Open HTML reports for detailed metrics"
echo "  3. Use charts for presentation and analysis"
echo "  4. Document findings and recommendations"
echo ""
echo "📊 Completed: $(date)"
echo ""

echo "👋 Thank you for using the Complete Performance Test Suite!"
echo "🎯 Happy performance testing!"
read -p "Press any key to exit..."
