# Performance Test Suite - Execution Guide

## 📋 Overview

This directory contains comprehensive performance testing scripts for two major systems:
- **VinhUni.edu.vn** (Educational institution website)
- **Viblo.asia** (Technical blogging platform)

## 🗂️ Script Files

### Main Execution Scripts

| Script | Platform | Description |
|--------|----------|-------------|
| `run_complete_test_suite.bat` | Windows | Master script to run all tests |
| `run_complete_test_suite.sh` | Unix/Linux | Master script to run all tests |
| `run_all_gatling_tests.bat` | Windows | VinhUni tests only |
| `run_all_viblo_tests.bat` | Windows | Viblo tests only |
| `run_all_viblo_tests.sh` | Unix/Linux | Viblo tests only |

### Analysis Scripts

| Script | Purpose |
|--------|---------|
| `generate_test_summary_v2.py` | Generate VinhUni CSV reports |
| `generate_viblo_summary.py` | Generate Viblo CSV reports |
| `generate_test_charts.py` | Create VinhUni performance charts |
| `generate_viblo_charts.py` | Create Viblo performance charts |
| `setup_venv.bat` | Setup Python virtual environment |

## 🚀 Quick Start

### Option 1: Run Complete Test Suite (Recommended)

**Windows:**
```bat
run_complete_test_suite.bat
```

**Unix/Linux:**
```bash
chmod +x run_complete_test_suite.sh
./run_complete_test_suite.sh
```

### Option 2: Run Individual System Tests

**VinhUni Tests Only:**
```bat
run_all_gatling_tests.bat
```

**Viblo Tests Only:**
```bat
run_all_viblo_tests.bat
```

## 📊 Test Cases Overview

### VinhUni.edu.vn Tests (5 test cases)
- **TC_VU_006**: PDF Download Performance (100-1000 users)
- **TC_VU_007**: Multi-Page Navigation (100-1000 users)
- **TC_VU_008**: Long Session Behavior (100-1000 users)
- **TC_VU_009**: Search + Download Combo (100-1000 users)
- **TC_VU_010**: Peak Load Stress Test (100-1000 users)

### Viblo.asia Tests (10 test cases)
- **TC_VIBLO_001**: Homepage Load Test (100 users)
- **TC_VIBLO_002**: Article Search Test (100 users)
- **TC_VIBLO_003**: Post Reading Test (100 users)
- **TC_VIBLO_004**: User Profile Test (100 users)
- **TC_VIBLO_005**: Tag Browse Test (100 users)
- **TC_VIBLO_006**: Category Filter Test (100 users)
- **TC_VIBLO_007**: Multi-Page Navigation Test (100 users)
- **TC_VIBLO_008**: API Performance Test (100 users)
- **TC_VIBLO_009**: Long Session Test (100 users)
- **TC_VIBLO_010**: Peak Load Stress Test (100 users)

## ⏱️ Execution Time Estimates

| Test Suite | Duration | Total Tests |
|------------|----------|-------------|
| VinhUni Only | 4-5 hours | 15 tests |
| Viblo Only | 3-4 hours | 10 tests |
| Complete Suite | 8-10 hours | 25 tests |

## 📁 Output Locations

### Test Results
```
target/gatling/
├── tc-vu-006-100-[timestamp]/     # VinhUni test results
├── tc-vu-007-200-[timestamp]/
├── tc-viblo-001-[timestamp]/      # Viblo test results
├── tc-viblo-002-[timestamp]/
└── ...
```

### Generated Reports
```
./
├── gatling_test_summary.csv       # VinhUni summary
├── viblo_test_summary.csv         # Viblo summary
├── test_charts/                   # VinhUni charts
│   ├── response_time_comparison.png
│   ├── throughput_analysis.png
│   └── performance_heatmap.png
└── viblo_charts/                  # Viblo charts
    ├── viblo_response_time_comparison.png
    ├── viblo_throughput_analysis.png
    └── viblo_performance_heatmap.png
```

## 🔧 Prerequisites

### System Requirements
- **Java 11+** (for Gatling)
- **Maven** (wrapper included)
- **Python 3.7+** (for report generation)
- **Minimum 4GB RAM** (8GB recommended)

### Python Dependencies
Setup virtual environment first:
```bat
setup_venv.bat
```

Or manually install:
```bash
pip install pandas matplotlib seaborn numpy beautifulsoup4
```

## 📋 Menu Options

### Complete Test Suite Menu
1. **Run VinhUni Tests Only** - Execute all VinhUni performance tests
2. **Run Viblo Tests Only** - Execute all Viblo performance tests  
3. **Run ALL Tests** - Execute complete test suite (both systems)
4. **Generate Reports** - Create CSV summaries and charts from existing results
5. **Clean All Results** - Remove all test results and generated files
6. **Exit** - Close the script

## 🔍 Analyzing Results

### CSV Reports
- Open `gatling_test_summary.csv` and `viblo_test_summary.csv` in Excel or any spreadsheet application
- Key metrics: Response Time, Throughput, Success Rate, Error Rate

### HTML Reports
- Navigate to `target/gatling/[test-name]/index.html`
- Interactive dashboards with detailed performance metrics
- Response time distributions, throughput graphs, error analysis

### Performance Charts
- Visual comparisons in `test_charts/` and `viblo_charts/`
- Response time trends, throughput analysis, performance heatmaps
- Suitable for presentations and documentation

## ⚠️ Important Notes

### Resource Usage
- Tests consume significant system resources
- Close unnecessary applications before running
- Monitor disk space (reports can be large)

### Network Considerations
- Tests generate real traffic to target websites
- Ensure stable internet connection
- Consider running during off-peak hours

### Error Handling
- Scripts continue on individual test failures
- Final summary shows passed/failed test counts
- Check detailed HTML reports for error analysis

## 🛠️ Troubleshooting

### Common Issues

**Java Memory Errors:**
```
set MAVEN_OPTS=-Xmx4g -XX:+UseG1GC
```

**Python Module Not Found:**
```
pip install --upgrade pandas matplotlib seaborn
```

**Maven Build Failures:**
```
./mvnw clean compile
```

**Permission Denied (Unix):**
```
chmod +x *.sh
```

### Performance Optimization
- Increase JVM heap size for large user loads
- Use SSD storage for faster report generation
- Close browser and unnecessary applications

## 📞 Support

For issues or questions:
1. Check the detailed HTML reports for specific errors
2. Review the CSV summaries for performance trends
3. Ensure all prerequisites are properly installed
4. Verify network connectivity to target systems

## 📈 Best Practices

1. **Before Testing:**
   - Ensure system resources are available
   - Verify network stability
   - Clean previous results if needed

2. **During Testing:**
   - Monitor system performance
   - Avoid interfering with running tests
   - Keep logs for troubleshooting

3. **After Testing:**
   - Generate reports immediately
   - Backup important results
   - Document findings and recommendations

---

**Happy Performance Testing! 🎯**
