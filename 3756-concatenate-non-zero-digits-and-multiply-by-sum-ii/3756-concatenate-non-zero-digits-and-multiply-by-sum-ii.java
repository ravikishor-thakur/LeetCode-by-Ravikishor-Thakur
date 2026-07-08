import java.util.*;

class Solution {
    static final int MOD = 1_000_000_007;

    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();

        ArrayList<Integer> pos = new ArrayList<>();
        ArrayList<Integer> digit = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != '0') {
                pos.add(i);
                digit.add(s.charAt(i) - '0');
            }
        }

        int m = digit.size();

        long[] pow10 = new long[m + 1];
        long[] hash = new long[m + 1];
        int[] prefixSum = new int[m + 1];

        pow10[0] = 1;
        for (int i = 1; i <= m; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }

        for (int i = 0; i < m; i++) {
            hash[i + 1] = (hash[i] * 10 + digit.get(i)) % MOD;
            prefixSum[i + 1] = prefixSum[i] + digit.get(i);
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            int left = lowerBound(pos, l);
            int right = upperBound(pos, r);

            if (left == right) {
                ans[i] = 0;
                continue;
            }

            int len = right - left;

            long x = (hash[right] - hash[left] * pow10[len] % MOD + MOD) % MOD;
            long sum = prefixSum[right] - prefixSum[left];

            ans[i] = (int) (x * sum % MOD);
        }

        return ans;
    }

    private int lowerBound(ArrayList<Integer> list, int target) {
        int l = 0, r = list.size();
        while (l < r) {
            int mid = (l + r) / 2;
            if (list.get(mid) >= target)
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }

    private int upperBound(ArrayList<Integer> list, int target) {
        int l = 0, r = list.size();
        while (l < r) {
            int mid = (l + r) / 2;
            if (list.get(mid) > target)
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }
}