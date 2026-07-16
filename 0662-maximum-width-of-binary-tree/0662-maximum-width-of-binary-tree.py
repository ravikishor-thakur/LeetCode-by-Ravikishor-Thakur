from collections import deque

class Solution:
    def widthOfBinaryTree(self, root):
        if not root:
            return 0

        ans = 0
        q = deque([(root, 0)])

        while q:
            size = len(q)
            _, first = q[0]

            for i in range(size):
                node, idx = q.popleft()
                idx -= first

                if node.left:
                    q.append((node.left, 2 * idx))
                if node.right:
                    q.append((node.right, 2 * idx + 1))

            ans = max(ans, idx + 1)

        return ans