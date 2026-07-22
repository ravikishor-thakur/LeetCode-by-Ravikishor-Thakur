int maximumSwap(int num) {
    char s[12];
    sprintf(s, "%d", num);

    int last[10];
    for (int i = 0; i < 10; i++)
        last[i] = -1;

    for (int i = 0; s[i]; i++)
        last[s[i] - '0'] = i;

    for (int i = 0; s[i]; i++) {
        int cur = s[i] - '0';
        for (int d = 9; d > cur; d--) {
            if (last[d] > i) {
                char t = s[i];
                s[i] = s[last[d]];
                s[last[d]] = t;
                return atoi(s);
            }
        }
    }

    return num;
}