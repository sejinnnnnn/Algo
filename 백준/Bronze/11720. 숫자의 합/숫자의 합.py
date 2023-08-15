N = int(input())
nums = list(input())

answer = 0
for i in range(N):
    answer += int(nums[i])

print(answer)