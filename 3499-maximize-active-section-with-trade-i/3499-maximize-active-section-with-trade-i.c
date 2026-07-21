int maxActiveSectionsAfterTrade(char* s) {
    int n = strlen(s);
    int ones = 0;
    for (int i = 0; i < n; i++) {
        if (s[i] == '1') ones++;
    }

    int m = n + 2;
    char *t = (char *)malloc((m + 1) * sizeof(char));
    t[0] = '1';
    for (int i = 0; i < n; i++) t[i + 1] = s[i];
    t[m - 1] = '1';
    t[m] = '\0';

    int len[100005];
    char type[100005];
    int runs = 0;

    for (int i = 0; i < m;) {
        int j = i;
        while (j < m && t[j] == t[i]) j++;
        type[runs] = t[i];
        len[runs] = j - i;
        runs++;
        i = j;
    }

    int bestGain = 0;
    for (int i = 1; i + 1 < runs; i++) {
        if (type[i] == '1' && type[i - 1] == '0' && type[i + 1] == '0') {
            int gain = len[i - 1] + len[i + 1];
            if (gain > bestGain)
                bestGain = gain;
        }
    }

    free(t);
    return ones + bestGain;
}