import matplotlib.pyplot as plt

user_loads = [100, 200, 400, 800, 1000]

tc_7_throughput = [5.98, 6.34, 8.49, 12.75, 12.73]
tc_8_throughput = [6.02, 6.39, 8.60, 12.92, 12.90]
tc_9_throughput = [5.96, 6.32, 8.45, 12.71, 12.68]
tc_10_throughput = [6.00, 6.37, 8.54, 12.87, 12.84]

plt.figure(figsize=(10,6))
plt.plot(user_loads, tc_7_throughput, marker='o', label='TC_VU_007')
plt.plot(user_loads, tc_8_throughput, marker='o', label='TC_VU_008')
plt.plot(user_loads, tc_9_throughput, marker='o', label='TC_VU_009')
plt.plot(user_loads, tc_10_throughput, marker='o', label='TC_VU_010')

plt.xlabel('User Load')
plt.ylabel('Throughput (req/s)')
plt.title('Throughput (req/s) vs User Load by Test Case')
plt.legend()
plt.grid(True)
plt.tight_layout()
plt.show()