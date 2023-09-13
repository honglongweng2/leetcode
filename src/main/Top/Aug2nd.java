package Top;

public class Aug2nd {
    public static void main(String[] args) {
        Aug2nd main = new Aug2nd();
        System.out.println(main.numberToAdd("aabc"));
    }

    // 题目：保证字符串为abc的最小步数，如aabc->abcabc：2步
    public int numberToAdd(String str) {
        if (str.length() == 0) return 3;
        if (str.length() == 1) return 2;
        int result = 0;
        int left = 0, right = 1;
        while (right < str.length()) {
            if (str.charAt(right - 1) >= str.charAt(right)) {
                // 新abc
                result += 3 + left - right;
                left = right;
            }
            right++;
        }
        return result + 3 + left - right;
    }
}
