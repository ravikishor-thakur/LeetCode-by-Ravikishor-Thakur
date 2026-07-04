class Solution:
    def findErrorNums(self, nums):
        n = len(nums)
        seen = set()
        duplicate = -1

        for num in nums:
            if num in seen:
                duplicate = num
            seen.add(num)

        for i in range(1, n + 1):
            if i not in seen:
                return [duplicate, i]