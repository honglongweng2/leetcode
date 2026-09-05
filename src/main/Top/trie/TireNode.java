package Top.trie;

public class TireNode {
    boolean isEnd;
    TireNode[] children;

    public TireNode() {
        isEnd = false;
        // 26个字母
        children = new TireNode[26];
    }
}