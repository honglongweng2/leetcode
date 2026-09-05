package Top.trie;

import java.util.ArrayList;
import java.util.List;

class WordTireNode {
    String value;
    WordTireNode[] children;

    public WordTireNode() {
        this.value = null;
        this.children = new WordTireNode[26];
    }
}
//No. 212
public class WordSearch {
    private WordTireNode root;

    public List<String> findWords(char[][] board, String[] words) {
        // 构建字典树
        root = new WordTireNode();
        for (String word : words) {
            insertWord(word);
        }
        List<String> result = new ArrayList<>();
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, visited, i, j, root, result);
            }
        }
        return result;
    }

    private void dfs(char[][] board, boolean[][] visited, int i, int j, WordTireNode cur, List<String> result) {
        int m = board.length;
        int n = board[0].length;
        if (i < 0 || j < 0 || i >= m || j >= n || visited[i][j]) {
            return;
        }
        // 判断字母是否在字典树中
        cur = cur.children[board[i][j] - 'a'];
        if (cur == null) {
            return;
        }
        visited[i][j] = true;
        // 找到单词
        if (cur.value != null) {
            result.add(cur.value);
            // 重点，把找到的单词删掉，防止输出重复单词
            cur.value = null;
        }
        // 继续遍历
        dfs(board, visited, i+1, j, cur, result);
        dfs(board, visited, i-1, j, cur, result);
        dfs(board, visited, i, j+1, cur, result);
        dfs(board, visited, i, j-1, cur, result);
        // 重置visited，其他单词可能也会用到这个字母
        visited[i][j] = false;
    }

    private void insertWord(String word) {
        WordTireNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            int ch = word.charAt(i) - 'a';
            if (cur.children[ch] == null) {
                cur.children[ch] = new WordTireNode();
            }
            cur = cur.children[ch];
        }
        // 遍历到单词末
        cur.value = word;
    }

    public static void main(String[] args) {
        char[][] board = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        String[] words = {"oath","pea","eat","rain"};
        WordSearch so = new WordSearch();
        System.out.println(so.findWords(board, words));
    }
}