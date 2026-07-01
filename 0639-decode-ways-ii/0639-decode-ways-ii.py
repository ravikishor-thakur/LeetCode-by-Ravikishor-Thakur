class Solution:
    def numDecodings(self, s: str) -> int:
        MOD = 10 ** 9 + 7

        def one(c):
            if c == '*':
                return 9
            if c == '0':
                return 0
            return 1

        def two(a, b):
            if a == '*' and b == '*':
                return 15
            if a == '*':
                if '0' <= b <= '6':
                    return 2
                return 1
            if b == '*':
                if a == '1':
                    return 9
                if a == '2':
                    return 6
                return 0
            num = int(a + b)
            return 1 if 10 <= num <= 26 else 0

        prev2 = 1
        prev1 = one(s[0])

        for i in range(1, len(s)):
            curr = (one(s[i]) * prev1 + two(s[i - 1], s[i]) * prev2) % MOD
            prev2 = prev1
            prev1 = curr

        return prev1