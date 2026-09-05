package Top.trie;

// No.211
class WordDictionary {

    private TireNode root;

    public WordDictionary() {
        this.root = new TireNode();
    }

    public void addWord(String word) {
        TireNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            int ch = word.charAt(i) - 'a';
            if (cur.children[ch] == null) {
                cur.children[ch] = new TireNode();
            }
            cur = cur.children[ch];
        }
        // 遍历到单词末
        cur.isEnd = true;
    }

    public boolean search(String word) {
        return dfs(word, 0, root);
    }

    private boolean dfs(String word, int index, TireNode cur) {
        if (index == word.length()) {
            return cur.isEnd;
        }
        if (word.charAt(index) == '.') {
            for (int i = 0; i < 26; i++) {
                if (cur.children[i] != null && dfs(word, index+1, cur.children[i])) {
                    return true;
                }
            }
            return false;
        }
        int ch = word.charAt(index) - 'a';
        return cur.children[ch] != null && dfs(word, index+1, cur.children[ch]);
    }

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        System.out.println(wordDictionary.search("pad")); // 返回 False
        System.out.println(wordDictionary.search("bad")); // 返回 True
        System.out.println(wordDictionary.search(".ad")); // 返回 True
        System.out.println(wordDictionary.search("b..")); // 返回 True
    }
}