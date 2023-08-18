from collections import deque

def dfs(n):
    visited[n] = True
    print(n, end=" ")

    for r in road[n]:
        if not visited[r]:
            dfs(r)
            
def bfs(n):
    queue = deque([n])
    visited[n] = True
    while len(queue) != 0:
        current = queue.popleft()
        print(current, end=" ")
        for r in road[current]:
            if not visited[r]:
                visited[r] = True
                queue.append(r)


node_count, road_count, start = map(int, input().split())

road = [[] for _ in range(node_count + 1)]
visited = [False for _ in range(node_count + 1)]

for _ in range(road_count):
    f, t = map(int, input().split())
    road[f].append(t)
    road[t].append(f)

for row in road:
    row.sort()

dfs(start)
visited = [False for _ in range(node_count + 1)]
print()
bfs(start)