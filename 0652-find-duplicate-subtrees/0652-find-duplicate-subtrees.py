class Solution:
    def findDuplicateSubtrees(self, root):
        count = {}
        ans = []

        def dfs(node):
            if not node:
                return "#"

            serial = (
                str(node.val)
                + ","
                + dfs(node.left)
                + ","
                + dfs(node.right)
            )

            count[serial] = count.get(serial, 0) + 1
            if count[serial] == 2:
                ans.append(node)

            return serial

        dfs(root)
        return ans