class MapSum {
    unordered_map<string, int> mp;
    unordered_map<string, int> pre;

public:
    MapSum() {
    }

    void insert(string key, int val) {
        int diff = val;
        if (mp.count(key))
            diff -= mp[key];

        mp[key] = val;

        string cur;
        for (char c : key) {
            cur += c;
            pre[cur] += diff;
        }
    }

    int sum(string prefix) {
        return pre[prefix];
    }
};