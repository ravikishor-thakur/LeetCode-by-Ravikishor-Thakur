class Solution:
    def findRedundantConnection(self, edges: List[List[int]]) -> List[int]:
        n = len(edges)
        parent = list(range(n + 1))

        def find(x):
            if parent[x] != x:
                parent[x] = find(parent[x])
            return parent[x]

        for u, v in edges:
            pu, pv = find(u), find(v)
            if pu == pv:
                return [u, v]
            parent[pu] = pv