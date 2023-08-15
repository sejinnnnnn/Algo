import sys

fast_input = sys.stdin.readline

T = int(fast_input())
for i in range(T):
    a, b = map(int, fast_input().split())
    print(a + b)