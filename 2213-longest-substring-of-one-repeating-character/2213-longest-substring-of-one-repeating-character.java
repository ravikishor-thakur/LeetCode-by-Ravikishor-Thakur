class Solution {
    class Node {
        int l, r;
        char leftChar, rightChar;
        int prefix, suffix, maxLen;
    }

    Node[] tree;
    char[] arr;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        arr = s.toCharArray();
        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int m = queryIndices.length;
        int[] ans = new int[m];

        for (int i = 0; i < m; i++) {
            update(1, queryIndices[i], queryCharacters.charAt(i));
            ans[i] = tree[1].maxLen;
        }

        return ans;
    }

    private void build(int idx, int l, int r) {
        tree[idx] = new Node();
        tree[idx].l = l;
        tree[idx].r = r;

        if (l == r) {
            tree[idx].leftChar = arr[l];
            tree[idx].rightChar = arr[l];
            tree[idx].prefix = 1;
            tree[idx].suffix = 1;
            tree[idx].maxLen = 1;
            return;
        }

        int mid = (l + r) / 2;
        build(idx * 2, l, mid);
        build(idx * 2 + 1, mid + 1, r);
        merge(idx);
    }

    private void update(int idx, int pos, char ch) {
        if (tree[idx].l == tree[idx].r) {
            arr[pos] = ch;
            tree[idx].leftChar = ch;
            tree[idx].rightChar = ch;
            tree[idx].prefix = 1;
            tree[idx].suffix = 1;
            tree[idx].maxLen = 1;
            return;
        }

        int mid = (tree[idx].l + tree[idx].r) / 2;

        if (pos <= mid)
            update(idx * 2, pos, ch);
        else
            update(idx * 2 + 1, pos, ch);

        merge(idx);
    }

    private void merge(int idx) {
        Node left = tree[idx * 2];
        Node right = tree[idx * 2 + 1];
        Node cur = tree[idx];

        cur.leftChar = left.leftChar;
        cur.rightChar = right.rightChar;

        cur.prefix = left.prefix;
        if (left.prefix == left.r - left.l + 1 && left.rightChar == right.leftChar)
            cur.prefix += right.prefix;

        cur.suffix = right.suffix;
        if (right.suffix == right.r - right.l + 1 && left.rightChar == right.leftChar)
            cur.suffix += left.suffix;

        cur.maxLen = Math.max(left.maxLen, right.maxLen);

        if (left.rightChar == right.leftChar) {
            cur.maxLen = Math.max(cur.maxLen, left.suffix + right.prefix);
        }
    }
}