# Dynamic Viblo Performance Testing Guide

## 🎯 Overview

Dynamic Performance Testing cho phép bạn chạy tất cả test cases với số lượng user tùy chỉnh. Thay vì chỉ có thể test với 100, 200, 400, 800, 1000 users cố định, bạn có thể nhập bất kỳ số lượng user nào từ 1 đến 2000+.

## 🚀 Quick Start

### Windows
```bat
run_dynamic_viblo_tests.bat
```

### Unix/Linux
```bash
chmod +x run_dynamic_viblo_tests.sh
./run_dynamic_viblo_tests.sh
```

## 📊 Test Cases

### Dynamic Test Cases (Nhận tham số user count)
1. **Dynamic Homepage Test** - Kiểm tra trang chủ với load pattern thích ứng
2. **Dynamic Article Search Test** - Test tìm kiếm bài viết với từ khóa ngẫu nhiên
3. **Dynamic Multi-Page Navigation Test** - Test điều hướng nhiều trang
4. **Dynamic Post Reading Test** - Test đọc bài viết với nhiều category
5. **Dynamic User Profile Test** - Test xem profile user với different user types
6. **Dynamic Tag Browse Test** - Test duyệt tags với popular tags
7. **Dynamic Category Filter Test** - Test lọc theo category với sorting
8. **Dynamic API Performance Test** - Test hiệu năng API với multiple endpoints
9. **Dynamic Long Session Test** - Test phiên làm việc dài mô phỏng real user behavior
10. **Dynamic Stress Test** - Test áp lực với pattern khác nhau theo user count

## 🎛️ Load Patterns

### Light Load (1-50 users)
- **Pattern**: Tất cả users load cùng lúc
- **Duration**: 8-12 phút mỗi test
- **Assertions**: Response time < 3-4s, Success rate > 95%

### Medium Load (51-200 users)
- **Pattern**: Ramp up trong 2-3 phút
- **Duration**: 15-18 phút mỗi test  
- **Assertions**: Response time < 4-5s, Success rate > 90%

### Heavy Load (201+ users)
- **Pattern**: Ramp up trong 5 phút + sustained load
- **Duration**: 20-30 phút mỗi test
- **Assertions**: Response time < 6-10s, Success rate > 75-85%

## 💡 Input Examples

### Lightweight Testing
```
Number of users: 25
✓ Fast execution (2-3 hours total)
✓ Good for development testing
✓ Basic performance validation
```

### Production Simulation
```
Number of users: 150
✓ Realistic production load
✓ Balanced testing time (3-4 hours)
✓ Performance benchmarking
```

### Stress Testing
```
Number of users: 500
✓ High stress conditions
✓ Extended testing time (4-6 hours)  
✓ Capacity planning
```

### Extreme Load Testing
```
Number of users: 1000+
✓ Maximum stress testing
✓ Very long testing time (6+ hours)
✓ Breaking point analysis
```

## 📈 Adaptive Features

### Smart Load Patterns
- **Low user count**: Simple at-once injection
- **Medium user count**: Ramp-up patterns
- **High user count**: Complex spike + sustained patterns

### Dynamic Assertions
- **Response time thresholds** adjust based on load
- **Success rate expectations** scale with user count
- **Test duration** adapts to user load

### Resource Optimization
- **Connection sharing** for high user counts
- **Memory management** scales with load
- **Timeout settings** adjust based on expected response times

## 🔧 Technical Details

### System Properties
Các dynamic test cases nhận tham số qua system property:
```bash
-DuserCount=150
```

### Test Classes
```
viblo_asia.TC_VIBLO_DYNAMIC.Dynamic_Homepage_Test
viblo_asia.TC_VIBLO_DYNAMIC.Dynamic_Article_Search_Test  
viblo_asia.TC_VIBLO_DYNAMIC.Dynamic_Multi_Page_Test
viblo_asia.TC_VIBLO_DYNAMIC.Dynamic_Post_Reading_Test
viblo_asia.TC_VIBLO_DYNAMIC.Dynamic_User_Profile_Test
viblo_asia.TC_VIBLO_DYNAMIC.Dynamic_Tag_Browse_Test
viblo_asia.TC_VIBLO_DYNAMIC.Dynamic_Category_Filter_Test
viblo_asia.TC_VIBLO_DYNAMIC.Dynamic_API_Performance_Test
viblo_asia.TC_VIBLO_DYNAMIC.Dynamic_Long_Session_Test
viblo_asia.TC_VIBLO_DYNAMIC.Dynamic_Stress_Test
```

### Example Manual Execution
```bash
# Run individual dynamic test
./mvnw clean gatling:test \
  -Dgatling.simulationClass=viblo_asia.TC_VIBLO_DYNAMIC.Dynamic_Homepage_Test \
  -DuserCount=75

# Run stress test with 300 users
./mvnw clean gatling:test \
  -Dgatling.simulationClass=viblo_asia.TC_VIBLO_DYNAMIC.Dynamic_Stress_Test \
  -DuserCount=300
```

## 📊 Results Analysis

### Output Files
```
target/gatling/
├── dynamic-homepage-test-[timestamp]/
├── dynamic-article-search-test-[timestamp]/
├── dynamic-multi-page-test-[timestamp]/
└── dynamic-stress-test-[timestamp]/
```

### Key Metrics to Review
1. **Response Time Percentiles** (50th, 95th, 99th)
2. **Throughput** (requests/second)
3. **Success Rate** (% successful requests)
4. **Error Analysis** (failed request patterns)

### Performance Comparison
- Compare results across different user counts
- Identify performance degradation points
- Find optimal load capacity
- Plan scaling requirements

## ⚠️ Best Practices

### Resource Planning
- **RAM**: Minimum 4GB, recommend 8GB+ for high loads
- **CPU**: Multi-core processor recommended
- **Network**: Stable internet connection required
- **Disk**: Ensure sufficient space for reports

### Load Testing Strategy
1. **Start Small**: Begin with 10-25 users
2. **Increment Gradually**: Double user count each iteration
3. **Find Breaking Point**: Continue until failure threshold
4. **Document Results**: Record findings at each load level

### Time Management
- **Light loads**: 2-3 hours total
- **Medium loads**: 3-4 hours total  
- **Heavy loads**: 4-6 hours total
- **Extreme loads**: 6+ hours total

## 🔍 Troubleshooting

### Common Issues

**OutOfMemoryError:**
```bash
export MAVEN_OPTS="-Xmx4g -XX:+UseG1GC"
```

**Connection Timeouts:**
- Reduce user count
- Check network stability
- Review target system capacity

**Test Failures:**
- Review assertion thresholds
- Check if target system is available
- Validate test scenarios

### Performance Optimization
- Close unnecessary applications
- Use SSD storage
- Ensure stable network
- Monitor system resources during testing

## 📝 Example Session

```
👥 Enter the number of users for testing: 100

🎯 Test Configuration:
  • User Count: 100
  • Load Pattern: Adaptive (Medium Load)
  • Test Duration: Variable (15-18 minutes per test)
  • Total Tests: 10 test cases

📊 Load Classification: Medium
⏱️ Estimated Total Time: 3-4 hours

Start dynamic testing with 100 users? (Y/N): Y

🚀 Starting Dynamic Viblo Performance Tests...

[1/10] 🏠 Running Dynamic Homepage Test...
✅ Dynamic Homepage Test PASSED

[2/10] 🔍 Running Dynamic Article Search Test...
✅ Dynamic Article Search Test PASSED

... (continues for all 10 tests) ...

📊 FINAL RESULTS SUMMARY:
  • User Count Tested: 100
  • Load Type: Medium
  • Total Tests Run: 10
  • Tests Passed: 10
  • Tests Failed: 0

✅ ALL TESTS PASSED SUCCESSFULLY!
🎯 Viblo.asia handles 100 users excellently!
```

## 🎯 Use Cases

### Development Testing
- **User Count**: 10-25
- **Purpose**: Basic functionality validation
- **Frequency**: Daily/weekly

### Pre-Production Testing  
- **User Count**: 50-150
- **Purpose**: Performance benchmarking
- **Frequency**: Before releases

### Capacity Planning
- **User Count**: 200-500
- **Purpose**: Infrastructure planning
- **Frequency**: Quarterly assessments

### Disaster Recovery
- **User Count**: 500-1000+
- **Purpose**: Breaking point analysis
- **Frequency**: Annual stress testing

---

**Happy Dynamic Performance Testing! 🚀**
