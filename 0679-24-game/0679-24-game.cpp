class Solution {
public:
    bool solve(vector<double> nums) {
        const double EPS = 1e-6;

        if (nums.size() == 1)
            return fabs(nums[0] - 24.0) < EPS;

        int n = nums.size();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                vector<double> next;

                for (int k = 0; k < n; k++) {
                    if (k != i && k != j)
                        next.push_back(nums[k]);
                }

                vector<double> val;
                val.push_back(nums[i] + nums[j]);
                val.push_back(nums[i] - nums[j]);
                val.push_back(nums[j] - nums[i]);
                val.push_back(nums[i] * nums[j]);

                if (fabs(nums[j]) > EPS)
                    val.push_back(nums[i] / nums[j]);

                if (fabs(nums[i]) > EPS)
                    val.push_back(nums[j] / nums[i]);

                for (double x : val) {
                    next.push_back(x);
                    if (solve(next))
                        return true;
                    next.pop_back();
                }
            }
        }

        return false;
    }

    bool judgePoint24(vector<int>& cards) {
        vector<double> nums;
        for (int x : cards)
            nums.push_back(x);

        return solve(nums);
    }
};