from collections import Counter
from typing import List

class Solution:
    def topKFrequent(self, words: List[str], k: int) -> List[str]:
        count = Counter(words)
        return sorted(count.keys(), key=lambda x: (-count[x], x))[:k]