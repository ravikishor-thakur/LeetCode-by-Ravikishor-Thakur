class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int left = 0, ones = 0;
        int minLen = Integer.MAX_VALUE;
        String ans = "";

        for (int right = 0; right < n; right++) {
            if (s.charAt(right) == '1') ones++;

            while (ones > k) {
                if (s.charAt(left) == '1') ones--;
                left++;
            }

            while (ones == k && s.charAt(left) == '0') {
                left++;
            }

            if (ones == k) {
                String cur = s.substring(left, right + 1);
                if (cur.length() < minLen) {
                    minLen = cur.length();
                    ans = cur;
                } else if (cur.length() == minLen &&
                           (ans.isEmpty() || cur.compareTo(ans) < 0)) {
                    ans = cur;
                }
            }
        }

        return ans;
    }
}