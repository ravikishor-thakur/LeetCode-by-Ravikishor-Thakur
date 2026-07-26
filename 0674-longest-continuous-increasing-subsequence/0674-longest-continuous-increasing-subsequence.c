int findLengthOfLCIS(int* nums, int numsSize) {
    int ans = 1, cur = 1;

    for (int i = 1; i < numsSize; i++) {
        if (nums[i] > nums[i - 1])
            cur++;
        else
            cur = 1;

        if (cur > ans)
            ans = cur;
    }

    return ans;
}