class Solution {
public:
    int m, n;
    vector<vector<int>> dir{{1,0},{-1,0},{0,1},{0,-1}};

    int bfs(vector<vector<int>>& forest, int sx, int sy, int tx, int ty) {
        if (sx == tx && sy == ty) return 0;

        queue<pair<int,int>> q;
        vector<vector<int>> vis(m, vector<int>(n, 0));

        q.push({sx, sy});
        vis[sx][sy] = 1;

        int steps = 0;

        while (!q.empty()) {
            int sz = q.size();
            while (sz--) {
                auto [x, y] = q.front();
                q.pop();

                if (x == tx && y == ty)
                    return steps;

                for (auto &d : dir) {
                    int nx = x + d[0];
                    int ny = y + d[1];

                    if (nx >= 0 && nx < m && ny >= 0 && ny < n &&
                        !vis[nx][ny] && forest[nx][ny] != 0) {
                        vis[nx][ny] = 1;
                        q.push({nx, ny});
                    }
                }
            }
            steps++;
        }

        return -1;
    }

    int cutOffTree(vector<vector<int>>& forest) {
        m = forest.size();
        n = forest[0].size();

        vector<tuple<int,int,int>> trees;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (forest[i][j] > 1)
                    trees.push_back({forest[i][j], i, j});
            }
        }

        sort(trees.begin(), trees.end());

        int x = 0, y = 0;
        int ans = 0;

        for (auto &[h, tx, ty] : trees) {
            int d = bfs(forest, x, y, tx, ty);
            if (d == -1)
                return -1;
            ans += d;
            forest[tx][ty] = 1;
            x = tx;
            y = ty;
        }

        return ans;
    }
};