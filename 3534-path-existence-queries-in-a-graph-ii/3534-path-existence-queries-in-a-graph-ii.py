class Solution:
    def pathExistenceQueries(self, n, nums, maxDiff, queries):
        order = sorted(range(n), key=lambda i: nums[i])
        vals = [nums[i] for i in order]

        pos = [0] * n
        for i, idx in enumerate(order):
            pos[idx] = i

        nxt = [0] * n
        j = 0
        for i in range(n):
            while j + 1 < n and vals[j + 1] - vals[i] <= maxDiff:
                j += 1
            nxt[i] = j

        LOG = n.bit_length()
        up = [nxt[:]]

        for _ in range(1, LOG):
            prev = up[-1]
            cur = [0] * n
            for i in range(n):
                cur[i] = prev[prev[i]]
            up.append(cur)

        ans = []

        for u, v in queries:
            if u == v:
                ans.append(0)
                continue

            l = pos[u]
            r = pos[v]
            if l > r:
                l, r = r, l

            if nxt[l] == l:
                ans.append(-1)
                continue

            cur = l
            steps = 0

            for k in range(LOG - 1, -1, -1):
                if up[k][cur] < r:
                    cur = up[k][cur]
                    steps += 1 << k

            if nxt[cur] < r:
                ans.append(-1)
            else:
                ans.append(steps + 1)

        return ans