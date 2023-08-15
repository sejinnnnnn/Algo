from collections import deque

def bfs(n, goal, visited_arr):
    queue = deque([n])
    answer = 0
    while queue:
        current = queue.popleft()

        if current == goal:
            print(visited_arr[current])
            break

        for i in (current - 1, current + 1, current * 2):
            if 0 <= i <= 100000 and not visited_arr[i]:
                visited_arr[i] = visited_arr[current] + 1
                queue.append(i)

        # print(answer, queue, current, visited_arr[17])
        

a, b = map(int, input().split())
if b < a:
    print(a - b)
else:
    visited = [0 for _ in range(100001)]
    bfs(a, b, visited)