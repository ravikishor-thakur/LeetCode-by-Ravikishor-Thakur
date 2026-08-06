class Solution {
    private int ans = 0;

    public int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode node) {
        if (node == null)
            return 0;

        int left = dfs(node.left);
        int right = dfs(node.right);

        int l = 0, r = 0;

        if (node.left != null && node.left.val == node.val)
            l = left + 1;

        if (node.right != null && node.right.val == node.val)
            r = right + 1;

        ans = Math.max(ans, l + r);

        return Math.max(l, r);
    }
}