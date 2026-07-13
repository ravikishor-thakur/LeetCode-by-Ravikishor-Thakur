import java.util.*;

class Solution {
    public List<List<String>> printTree(TreeNode root) {
        int height = getHeight(root);
        int rows = height + 1;
        int cols = (1 << (height + 1)) - 1;

        List<List<String>> res = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            List<String> row = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                row.add("");
            }
            res.add(row);
        }

        fill(root, res, 0, 0, cols - 1);
        return res;
    }

    private int getHeight(TreeNode root) {
        if (root == null) {
            return -1;
        }

        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }

    private void fill(TreeNode node, List<List<String>> res, int row, int left, int right) {
        if (node == null) {
            return;
        }

        int mid = (left + right) / 2;
        res.get(row).set(mid, String.valueOf(node.val));

        fill(node.left, res, row + 1, left, mid - 1);
        fill(node.right, res, row + 1, mid + 1, right);
    }
}