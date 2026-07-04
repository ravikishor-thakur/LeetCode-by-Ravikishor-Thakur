from collections import deque

class Solution:
    def minScore(self, n, roads):
        graph = [[] for _ in range(n + 1)]

        for u, v, d in roads:
            graph[u].append((v, d))
            graph[v].append((u, d))

        visited = [False] * (n + 1)
        q = deque([1])
        visited[1] = True

        ans = float("inf")

        while q:
            u = q.popleft()

            for v, d in graph[u]:
                ans = min(ans, d)

                if not visited[v]:
                    visited[v] = True
                    q.append(v)

        return ans