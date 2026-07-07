class TrieNode:
    def __init__(self):
        self.children = {}
        self.word = None


class Solution:
    def replaceWords(self, dictionary, sentence):
        root = TrieNode()

        for word in dictionary:
            node = root
            for ch in word:
                if ch not in node.children:
                    node.children[ch] = TrieNode()
                node = node.children[ch]
            node.word = word

        def replace(word):
            node = root
            for ch in word:
                if ch not in node.children:
                    break
                node = node.children[ch]
                if node.word:
                    return node.word
            return word

        return " ".join(replace(word) for word in sentence.split())