class Solution:
    def exclusiveTime(self, n: int, logs: List[str]) -> List[int]:
        ans = [0] * n
        stack = []
        prev = 0

        for log in logs:
            id, typ, time = log.split(":")
            id = int(id)
            time = int(time)

            if typ == "start":
                if stack:
                    ans[stack[-1]] += time - prev
                stack.append(id)
                prev = time
            else:
                ans[stack.pop()] += time - prev + 1
                prev = time + 1

        return ans