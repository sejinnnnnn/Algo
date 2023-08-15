import sys
import heapq

class Path:
    def __init__(self, to, weight):
        self.to = to
        self.weight = weight

    def __lt__(self, other):
        return self.weight < other.weight
    
    def __le__(self, other):
        return self.weight <= other.weight
    
    def __gt__(self, other):
        return self.weight > other.weight
    
    def __ge__(self, other):
        return self.weight >= other.weight
    
    def __eq__(self, other):
        return self.weight == other.weight
    
    def __str__(self):
        return "to : {}, weight : {}".format(self.to + 1, self.weight)

class Node:
    def __init__(self, v):
        self.v = v
        self.path = []
        self.visited = False

v_count, e_count = map(int, sys.stdin.readline().split())
start = int(sys.stdin.readline()) - 1
nodes = [Node(i) for i in range(v_count)]

for i in range(e_count):
    u, v, w = map(int, sys.stdin.readline().split())
    nodes[u - 1].path.append(Path(v - 1, w))

table = [10000000 for _ in range(v_count)]
pq = []

table[start] = 0
nodes[start].visited = True

for i in range(len(nodes[start].path)):
    if table[nodes[start].path[i].to] > nodes[start].path[i].weight:
        table[nodes[start].path[i].to] = nodes[start].path[i].weight
        heapq.heappush(pq, nodes[start].path[i])

# print(table)
# for i in range(len(pq)):
#     print(pq[i])

while pq:
    selected = heapq.heappop(pq)

    if table[selected.to] < selected.weight or nodes[selected.to].visited:
        continue

    nodes[selected.to].visited = True

    for i in range(len(nodes[selected.to].path)):
        new_weight = selected.weight + nodes[selected.to].path[i].weight
        if new_weight < table[nodes[selected.to].path[i].to]:
            table[nodes[selected.to].path[i].to] = new_weight
            # heapq.heappush(pq, nodes[selected.to].path[i])
            heapq.heappush(pq, Path(nodes[selected.to].path[i].to, new_weight))

    # print(table)
    # for i in range(len(pq)):
    #     print(pq[i])

for i in range(v_count):
    if table[i] == 10000000:
        print("INF")
    else:
        print(table[i])