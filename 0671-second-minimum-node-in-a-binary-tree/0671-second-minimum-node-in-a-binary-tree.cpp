class Solution {
public:
    long long ans;
    int mn;

    void dfs(TreeNode* root) {
        if (!root) return;

        if (root->val > mn && root->val < ans)
            ans = root->val;

        dfs(root->left);
        dfs(root->right);
    }

    int findSecondMinimumValue(TreeNode* root) {
        mn = root->val;
        ans = LLONG_MAX;

        dfs(root);

        return ans == LLONG_MAX ? -1 : (int)ans;
    }
};