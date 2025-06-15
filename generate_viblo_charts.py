import os
import csv
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns

# Read the Viblo test summary CSV
csv_file = "d:\\Study\\Test\\gatling-maven-plugin-demo-java-main\\viblo_test_summary.csv"

# Check if CSV exists
if not os.path.exists(csv_file):
    print(f"CSV file not found: {csv_file}")
    print("Please run generate_viblo_summary.py first to create the summary.")
    exit(1)

df = pd.read_csv(csv_file)

# Convert columns to numeric
df['User Count'] = pd.to_numeric(df['User Count'])
df['Mean Response Time (ms)'] = pd.to_numeric(df['Mean Response Time (ms)'], errors='coerce')
df['Max Response Time (ms)'] = pd.to_numeric(df['Max Response Time (ms)'], errors='coerce')
df['Success Rate (%)'] = pd.to_numeric(df['Success Rate (%)'], errors='coerce')

# Create output directory for Viblo charts
output_dir = "d:\\Study\\Test\\gatling-maven-plugin-demo-java-main\\viblo_charts"
os.makedirs(output_dir, exist_ok=True)

# Set style for all plots
plt.style.use('seaborn')
sns.set_palette("husl")

# 1. Viblo Response Time Comparison by Test Case
plt.figure(figsize=(14, 8))
for test_case in df['Test Case'].unique():
    test_data = df[df['Test Case'] == test_case]
    plt.plot(test_data['User Count'], test_data['Mean Response Time (ms)'], 
             marker='o', label=test_case, linewidth=2)

plt.xlabel('Number of Users', fontsize=12)
plt.ylabel('Mean Response Time (ms)', fontsize=12)
plt.title('Viblo.asia - Mean Response Time vs Number of Users', fontsize=14, fontweight='bold')
plt.legend(bbox_to_anchor=(1.05, 1), loc='upper left')
plt.grid(True, alpha=0.3)
plt.tight_layout()
plt.savefig(os.path.join(output_dir, 'viblo_response_time_comparison.png'), dpi=300)
plt.close()

# 2. Viblo Success Rate Analysis
plt.figure(figsize=(14, 8))
for test_case in df['Test Case'].unique():
    test_data = df[df['Test Case'] == test_case]
    plt.plot(test_data['User Count'], test_data['Success Rate (%)'], 
             marker='s', label=test_case, linewidth=2)

plt.xlabel('Number of Users', fontsize=12)
plt.ylabel('Success Rate (%)', fontsize=12)
plt.title('Viblo.asia - Success Rate vs Number of Users', fontsize=14, fontweight='bold')
plt.legend(bbox_to_anchor=(1.05, 1), loc='upper left')
plt.grid(True, alpha=0.3)
plt.ylim(0, 105)
plt.tight_layout()
plt.savefig(os.path.join(output_dir, 'viblo_success_rate_analysis.png'), dpi=300)
plt.close()

# 3. Viblo Performance Heatmap
if len(df['Test Case'].unique()) > 1 and len(df['User Count'].unique()) > 1:
    pivot_response = df.pivot(index='Test Case', 
                             columns='User Count', 
                             values='Mean Response Time (ms)')
    plt.figure(figsize=(12, 8))
    sns.heatmap(pivot_response, annot=True, cmap='YlOrRd', fmt='.0f', cbar_kws={'label': 'Response Time (ms)'})
    plt.title('Viblo.asia - Response Time Heatmap', fontsize=14, fontweight='bold')
    plt.tight_layout()
    plt.savefig(os.path.join(output_dir, 'viblo_response_heatmap.png'), dpi=300)
    plt.close()

# 4. Viblo Test Case Performance Summary
plt.figure(figsize=(12, 8))
test_summary = df.groupby('Test Case').agg({
    'Mean Response Time (ms)': 'mean',
    'Success Rate (%)': 'mean'
}).reset_index()

x_pos = range(len(test_summary))
fig, ax1 = plt.subplots(figsize=(14, 8))

# Response time bars
color = 'tab:red'
ax1.set_xlabel('Test Cases', fontsize=12)
ax1.set_ylabel('Average Response Time (ms)', color=color, fontsize=12)
bars1 = ax1.bar([x - 0.2 for x in x_pos], test_summary['Mean Response Time (ms)'], 
                0.4, label='Avg Response Time', color=color, alpha=0.7)
ax1.tick_params(axis='y', labelcolor=color)
ax1.set_xticks(x_pos)
ax1.set_xticklabels(test_summary['Test Case'], rotation=45, ha='right')

# Success rate line
ax2 = ax1.twinx()
color = 'tab:blue'
ax2.set_ylabel('Average Success Rate (%)', color=color, fontsize=12)
line1 = ax2.plot(x_pos, test_summary['Success Rate (%)'], 
                 color=color, marker='o', linewidth=2, markersize=8, label='Avg Success Rate')
ax2.tick_params(axis='y', labelcolor=color)
ax2.set_ylim(0, 105)

plt.title('Viblo.asia - Test Case Performance Summary', fontsize=14, fontweight='bold')
plt.tight_layout()
plt.savefig(os.path.join(output_dir, 'viblo_performance_summary.png'), dpi=300)
plt.close()

# 5. Viblo Load Testing Insights
plt.figure(figsize=(12, 6))
plt.scatter(df['Mean Response Time (ms)'], df['Success Rate (%)'], 
           c=df['User Count'], cmap='viridis', alpha=0.6, s=100)
plt.colorbar(label='User Count')
plt.xlabel('Mean Response Time (ms)', fontsize=12)
plt.ylabel('Success Rate (%)', fontsize=12)
plt.title('Viblo.asia - Response Time vs Success Rate (colored by User Count)', fontsize=14, fontweight='bold')
plt.grid(True, alpha=0.3)
plt.tight_layout()
plt.savefig(os.path.join(output_dir, 'viblo_load_insights.png'), dpi=300)
plt.close()

print(f"Viblo.asia charts have been generated in {output_dir}")

# Generate Viblo-specific summary report
with open(os.path.join(output_dir, 'viblo_test_analysis_report.txt'), 'w', encoding='utf-8') as f:
    f.write("Viblo.asia Performance Test Analysis Report\n")
    f.write("=" * 50 + "\n\n")
    
    # Overall statistics
    f.write(f"Total Test Executions: {len(df)}\n")
    f.write(f"Test Cases Covered: {df['Test Case'].nunique()}\n")
    f.write(f"User Load Levels: {sorted(df['User Count'].unique())}\n\n")
    
    # Performance metrics
    f.write("Overall Performance Metrics:\n")
    f.write("-" * 30 + "\n")
    f.write(f"Average Response Time: {df['Mean Response Time (ms)'].mean():.0f}ms\n")
    f.write(f"Best Response Time: {df['Mean Response Time (ms)'].min():.0f}ms\n")
    f.write(f"Worst Response Time: {df['Mean Response Time (ms)'].max():.0f}ms\n")
    f.write(f"Average Success Rate: {df['Success Rate (%)'].mean():.1f}%\n")
    f.write(f"Lowest Success Rate: {df['Success Rate (%)'].min():.1f}%\n\n")
    
    # Test case analysis
    for test_case in sorted(df['Test Case'].unique()):
        test_data = df[df['Test Case'] == test_case]
        f.write(f"\n{test_case}:\n")
        f.write("-" * len(test_case) + "\n")
        f.write(f"User loads tested: {sorted(test_data['User Count'].unique())}\n")
        f.write(f"Average response time: {test_data['Mean Response Time (ms)'].mean():.0f}ms\n")
        f.write(f"Best response time: {test_data['Mean Response Time (ms)'].min():.0f}ms\n")
        f.write(f"Worst response time: {test_data['Mean Response Time (ms)'].max():.0f}ms\n")
        f.write(f"Average success rate: {test_data['Success Rate (%)'].mean():.1f}%\n")
        f.write(f"Lowest success rate: {test_data['Success Rate (%)'].min():.1f}%\n")
        
        # Performance assessment
        avg_response = test_data['Mean Response Time (ms)'].mean()
        avg_success = test_data['Success Rate (%)'].mean()
        
        if avg_response < 2000 and avg_success > 95:
            f.write("Assessment: EXCELLENT performance\n")
        elif avg_response < 3000 and avg_success > 90:
            f.write("Assessment: GOOD performance\n")
        elif avg_response < 5000 and avg_success > 80:
            f.write("Assessment: ACCEPTABLE performance\n")
        else:
            f.write("Assessment: NEEDS IMPROVEMENT\n")

print("Viblo.asia analysis report generated")
print("\nFiles created:")
print("- viblo_response_time_comparison.png")
print("- viblo_success_rate_analysis.png")
print("- viblo_response_heatmap.png")
print("- viblo_performance_summary.png")
print("- viblo_load_insights.png")
print("- viblo_test_analysis_report.txt")
