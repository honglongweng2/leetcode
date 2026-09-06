package Top.DP;

// No. 5
public class LongestPalindrome {
    private int start = 0;
    private int maxLen = 0;
    // 思路 双指针，以left,right向两侧扩回文串
    public String longestPalindrome(String s) {
        int n = s.length();
        if (n < 2) {
            return s;
        }
        for (int i = 0; i < n; i++) {
            // 奇数长度的回文串
            expand(s, i, i);
            // 偶数长度的回文串
            expand(s, i, i+1);
        }
        return s.substring(start, start + maxLen);
    }

    private void expand(String s, int left, int right) {
        while (left >=0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            int curLen = right - left + 1;
            if (curLen > maxLen) {
                maxLen = curLen;
                start = left;
            }
            left--;
            right++;
        }
    }

    public static void main(String[] args) {
        LongestPalindrome so = new LongestPalindrome();
        System.out.println(so.longestPalindrome("babad"));
    }
}
