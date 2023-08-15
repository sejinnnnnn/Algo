from collections import deque

M, N = map(int, input().split())
tomato = []

for i in range(N):
    tomato.append(list(map(int, input().split())))

queue = deque([])

for i in range(N):
    for j in range(M):
        if tomato[i][j] == 1:
            queue.append([i, j])

dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]

while queue:
    x_cur, y_cur = queue.popleft()
    for i in range(4):
        x_new = x_cur + dx[i]
        y_new = y_cur + dy[i]
        if 0 <= x_new < N and 0 <= y_new < M and tomato[x_new][y_new] == 0:
            tomato[x_new][y_new] = tomato[x_cur][y_cur] + 1
            queue.append([x_new, y_new])

answer = 0
for i in range(N):
    for j in range(M):
        if tomato[i][j] == 0:
            print(-1)
            exit()
    answer = max(answer, max(tomato[i]))

print(answer - 1)