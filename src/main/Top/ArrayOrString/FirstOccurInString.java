package Top.ArrayOrString;

/**
 * No.28 needle在haystack中第一次出现的位置，若没有返回-1
 *
 * 思路：窗口法，在haystack上留一个len2的窗口来看是否匹配
 */
public class FirstOccurInString {
    public int strStr(String haystack, String needle) {
        final int len1 = haystack.length(), len2 = needle.length();
        for (int i = 0; i <= len1 - len2; i++) {
            if (haystack.substring(i, i+len2).equals(needle)) return i;
        }
        return -1;
    }
}
