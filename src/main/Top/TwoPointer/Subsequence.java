package Top.TwoPointer;

public class Subsequence {

    public static void main(String[] args) {
        Subsequence main = new Subsequence();
        System.out.println(main.isSubsequence("abc", "abdd"));
    }
    /**
     * No.392 s是t的子序列
     */
    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0) return true;
        int index_s = 0, index_t = 0;
        while (index_t < t.length()) {
            if (t.charAt(index_t) == s.charAt(index_s)) {
                index_s++;
                if (index_s == s.length()) return true;
            }
            index_t++;
        }
        return false;
    }
}
