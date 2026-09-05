package Top.trie;

// No.208
public class Trie {
    private TireNode root;

    public Trie() {
        this.root = new TireNode();
    }

    public void insert(String word) {
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
        TireNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            int ch = word.charAt(i) - 'a';
            if (cur.children[ch] == null) {
                return false;
            }
            cur = cur.children[ch];
        }
        return cur.isEnd;
    }

    public boolean startsWith(String prefix) {
        TireNode cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            int ch = prefix.charAt(i) - 'a';
            if (cur.children[ch] == null) {
                return false;
            }
            cur = cur.children[ch];
        }
        return true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));   // 返回 True
        System.out.println(trie.search("app"));     // 返回 False
        System.out.println(trie.startsWith("app")); // 返回 True
        trie.insert("app");
        trie.search("app");
    }
}
