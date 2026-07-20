int countLessEqual(int m, int n, int x) {
    int count = 0;
    for (int i = 1; i <= m; i++) {
        count += (x / i < n) ? (x / i) : n;
    }
    return count;
}

int findKthNumber(int m, int n, int k) {
    int left = 1, right = m * n;

    while (left < right) {
        int mid = left + (right - left) / 2;

        if (countLessEqual(m, n, mid) >= k)
            right = mid;
        else
            left = mid + 1;
    }

    return left;
}