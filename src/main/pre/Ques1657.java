package pre;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ques1657 {
    public static boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) return false;
        int[] visited = new int[26];
        int[] visited2 = new int[26];
        for (int i = 0; i < word1.length(); i ++) {
            visited[word1.charAt(i) - 'a']++;
            visited2[word2.charAt(i) - 'a']++;
        }
        List<Integer> visitedArray1 = new ArrayList<>();
        List<Integer> visitedArray2 = new ArrayList<>();
        for (int i = 0; i < 26; i ++) {
            if (visited[i] == 0 && visited2[i] == 0) {
                continue;
            } else if (visited[i] > 0 && visited2[i] > 0) {
                visitedArray1.add(visited[i]);
                visitedArray2.add(visited2[i]);
            } else {
                return false;
            }
        }
        if (visitedArray1.size() != visitedArray2.size()) return false;
        Collections.sort(visitedArray1);
        Collections.sort(visitedArray2);
        for (int i = 0; i < visitedArray1.size(); i++) {
            if (!visitedArray1.get(i).equals(visitedArray2.get(i))) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(closeStrings("abc", "bca"));
    }
}
