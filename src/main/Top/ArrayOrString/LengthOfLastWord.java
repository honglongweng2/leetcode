package Top.ArrayOrString;

public class LengthOfLastWord {
    /**
     * No.58
     */
    public int lengthOfLastWord(String s) {
        int result = 0, n = s.length();
        while (n > 0 && s.charAt(n - 1) == ' ') n--;
        while (n > 0 && s.charAt(n - 1) != ' ') {
            result++;
            n--;
        }
        return result;
    }
}
