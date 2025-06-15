import os
import csv
import re
import json

# Define paths for Viblo test results
gatling_results_dir = "d:\\Study\\Test\\gatling-maven-plugin-demo-java-main\\target\\gatling"
output_csv = "d:\\Study\\Test\\gatling-maven-plugin-demo-java-main\\viblo_test_summary.csv"

def extract_metrics_from_file(filepath):
    """Extract metrics directly from the stats.json file"""
    try:
        if not os.path.exists(filepath):
            return None
            
        with open(filepath, "r", encoding="utf-8") as f:
            content = f.read()
            
        # First try to find the stats declaration
        match = re.search(r'var stats = ({[\s\S]*?});', content)
        if not match:
            return None
            
        # Get the JSON-like string and clean it
        json_str = match.group(1)
        # Fix JavaScript JSON to be valid
        json_str = re.sub(r'(\w+):', r'"\1":', json_str)
        json_str = re.sub(r',(\s*})', r'\1', json_str)
        
        data = json.loads(json_str)
        
        # Find the global statistics
        global_stats = None
        if 'stats' in data:
            for stat in data['stats']:
                if stat.get('name') == 'Global':
                    global_stats = stat
                    break
        
        if not global_stats:
            return None
            
        return {
            'mean': global_stats.get('meanResponseTime', {}).get('total'),
            'max': global_stats.get('maxResponseTime', {}).get('total'),
            'success': (global_stats.get('successfulRequests', {}).get('total', 0) / 
                       global_stats.get('numberOfRequests', {}).get('total', 1)) * 100
                       if global_stats.get('numberOfRequests', {}).get('total', 0) > 0 else 0
        }
    except Exception as e:
        print(f"Error processing {filepath}: {e}")
        return None

def get_viblo_test_info(dirname):
    """Get Viblo test case number and user count from directory name"""
    # Try tc-viblo-XXX format
    viblo_match = re.search(r'tc-viblo-(\d{3})', dirname.lower())
    if viblo_match:
        test_num = viblo_match.group(1)
    else:
        # Try direct user count format for Viblo tests
        user_match = re.search(r'viblo.*?(\d+)-users', dirname.lower())
        if user_match:
            test_num = user_match.group(1)
        else:
            return None, None
            
    # Get user count
    user_count = re.search(r'(\d+)-users', dirname)
    if not user_count:
        return None, None
        
    return f"TC_VIBLO_{test_num}", int(user_count.group(1))

# Collect results
results = []
for root, dirs, _ in os.walk(gatling_results_dir):
    for dirname in dirs:
        # Only process Viblo test directories
        if 'viblo' not in dirname.lower():
            continue
            
        stats_path = os.path.join(root, dirname, "js", "stats.js")
        if not os.path.exists(stats_path):
            continue
            
        test_case, user_count = get_viblo_test_info(dirname)
        if not test_case or not user_count:
            continue
            
        metrics = extract_metrics_from_file(stats_path)
        if not metrics:
            continue
            
        results.append({
            'Test Case': test_case,
            'User Count': user_count,
            'Directory': dirname,
            'Mean Response Time (ms)': metrics['mean'] if metrics['mean'] is not None else 'N/A',
            'Max Response Time (ms)': metrics['max'] if metrics['max'] is not None else 'N/A',
            'Success Rate (%)': round(metrics['success'], 2) if metrics['success'] is not None else 'N/A'
        })

# Sort results
results.sort(key=lambda x: (x['Test Case'], x['User Count']))

# Write CSV
headers = ['Test Case', 'User Count', 'Directory', 'Mean Response Time (ms)', 'Max Response Time (ms)', 'Success Rate (%)']
with open(output_csv, 'w', newline='', encoding='utf-8') as f:
    writer = csv.DictWriter(f, fieldnames=headers)
    writer.writeheader()
    writer.writerows(results)

print(f"\nProcessed {len(results)} Viblo test results")
print(f"Output written to: {output_csv}\n")

# Print summary
if results:
    test_cases = sorted(list(set(r['Test Case'] for r in results)))
    print("Summary by Viblo Test Case:")
    for tc in test_cases:
        tc_results = [r for r in results if r['Test Case'] == tc]
        user_counts = sorted(list(set(r['User Count'] for r in tc_results)))
        print(f"\n{tc}:")
        print(f"  User counts tested: {user_counts}")
        print(f"  Number of runs: {len(tc_results)}")
        
        # Show performance summary
        mean_times = [r['Mean Response Time (ms)'] for r in tc_results if r['Mean Response Time (ms)'] != 'N/A']
        success_rates = [r['Success Rate (%)'] for r in tc_results if r['Success Rate (%)'] != 'N/A']
        
        if mean_times:
            print(f"  Avg Response Time: {sum(mean_times)/len(mean_times):.0f}ms")
        if success_rates:
            print(f"  Avg Success Rate: {sum(success_rates)/len(success_rates):.1f}%")
else:
    print("No Viblo test results found. Make sure to run the tests first using run_viblo_tests.bat")
