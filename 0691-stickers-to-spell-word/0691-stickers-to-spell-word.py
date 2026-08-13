from typing import List

class Solution:
    def minStickers(self, stickers: List[str], target: str) -> int:
        m = len(target)
        full = (1 << m) - 1

        INF = float('inf')
        dp = [INF] * (1 << m)
        dp[0] = 0

        for mask in range(1 << m):
            if dp[mask] == INF:
                continue

            for sticker in stickers:
                cnt = [0] * 26
                for ch in sticker:
                    cnt[ord(ch) - ord('a')] += 1

                new_mask = mask

                for i in range(m):
                    if (new_mask >> i) & 1:
                        continue
                    idx = ord(target[i]) - ord('a')
                    if cnt[idx] > 0:
                        cnt[idx] -= 1
                        new_mask |= (1 << i)

                dp[new_mask] = min(dp[new_mask], dp[mask] + 1)

        return -1 if dp[full] == INF else dp[full]