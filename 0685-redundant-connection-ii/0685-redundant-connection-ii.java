class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        int[] parent = new int[n + 1];

        int[] first = null;
        int[] second = null;

        for (int[] e : edges) {
            int u = e[0], v = e[1];
            if (parent[v] == 0) {
                parent[v] = u;
            } else {
                first = new int[]{parent[v], v};
                second = new int[]{u, v};
                e[1] = 0;
            }
        }

        for (int i = 0; i <= n; i++)
            parent[i] = i;

        for (int[] e : edges) {
            if (e[1] == 0)
                continue;

            int u = e[0], v = e[1];
            int pu = find(parent, u);

            if (pu == v) {
                if (first == null)
                    return e;
                return first;
            }

            parent[v] = pu;
        }

        return second;
    }

    private int find(int[] parent, int x) {
        if (parent[x] != x)
            parent[x] = find(parent, parent[x]);
        return parent[x];
    }
}