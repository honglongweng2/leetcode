package Top.ArrayOrString;

/**
 * No.151 反转字符串
 *
 * 思路：从末尾遍历到第一个非\s的
 */
public class ReverseWord {
    public static void main(String[] args) {
        ReverseWord main = new ReverseWord();
        System.out.println(main.reverseWords(" i  the  sky  is  blue "));
    }
    public String reverseWords(String s) {
        s = s.trim();
        StringBuilder sb = new StringBuilder();
        int index = s.length(), lastIndex = s.length();
        while (index > 0) {
            while (index > 0 && s.charAt(index-1) != ' ') index--;
            sb.append(s, index, lastIndex).append(" ");
            while (index > 0 && s.charAt(index-1) == ' ') index--;
            lastIndex = index;
        }
        return sb.toString().trim();
    }
}
