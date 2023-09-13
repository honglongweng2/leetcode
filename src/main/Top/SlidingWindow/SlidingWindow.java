package Top.SlidingWindow;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SlidingWindow {
    public static void main(String[] args) {
        SlidingWindow main = new SlidingWindow();
//        System.out.println(main.minSubArrayLen(4, new int[]{1,4,4}));
        System.out.println(main.lengthOfLongestSubstring(" "));
    }
    /**
     * No.209 最小子数组和大于等于target
     *
     * 思路：窗口法，一直向右直到sum>=target，然后向右移动窗口
     */
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0, right = 0;
        int sum = 0, size = Integer.MAX_VALUE;
        for (; right < nums.length; right++) {
            sum += nums[right];
            if (sum >= target) {
                size = Math.min(size, right - left + 1);
                while (sum - nums[left] >= target) {
                    sum -= nums[left];
                    left++;
                    size = Math.min(size, right - left + 1);
                }
            }
        }
        return size > nums.length ? 0 : size;
    }

    /**
     * No.3 Longest substring without repeating characters
     *
     * 思路：用一个数组alpha存字符出现的上一个位置，也就是下次出现某个字符时，需要选择从start和alpha[c]中较大的一方开始新的substring
     * 为了方便，alpha存的是出现的上一个位置+1（因为初始值为0）
     */
    public int lengthOfLongestSubstring(String s) {
        int start = 0, max = 0;
        int[] alpha = new int[128];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            start = Math.max(start, alpha[c]);
            max = Math.max(max, i - start + 1);
            alpha[c] = i+1;
        }
        return max;
    }

    /**
     * No.30 Substring with concatenation of All words
     *
     * 思路：维持窗口，用map存word和出现次数。每个left向右一个word一个word的录入。
     * 碰到有个substring组不成任一word时，可以直接跳到right+word_len
     * 若一直可以组成word时，有两个条件做限制：1.匹配的word数count，任何时候count=words.length时代表匹配完全，产生一个结果
     * 2.匹配到word的次数window.get(word)，如果次数大于map[word]表示匹配多了，从left开始向右移动窗口直到把word的次数-1，重新开始移动窗口右侧
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new LinkedList<>();
        if (words.length == 0) return result;
        int word_len = words[0].length();
        int n = s.length();
        Map<String, Integer> map = new HashMap<>();
        for (String value : words) {
            if (!map.containsKey(value)) {
                map.put(value, 1);
            } else {
                map.put(value, map.get(value) + 1);// assume that words can duplicate.
            }
        }
        for (int i = 0; i < word_len; i++) {
            Map<String, Integer> window = new HashMap<>();
            int left = i, count = 0;
            for (int right = i; right <= n - word_len; right+=word_len) {
                String word = s.substring(right, right + word_len);
                if (map.containsKey(word)) {
                    if (!window.containsKey(word)) {
                        window.put(word, 1);
                    }else {
                        window.put(word, window.get(word) + 1);
                    }
                    if (window.get(word) <= map.get(word)) {
                        count++;
                    }else {
                        // word is already in the window and count of word is over.
                        // now remove the word and put left after the point of word.
                        for (int j = left;; j+=word_len) {
                            String temp = s.substring(j, j + word_len);
                            window.put(temp, window.get(temp) - 1);
                            if (temp.equals(word)) {
                                left = j + word_len;
                                break;
                            }
                            count --;
                        }
                    }
                    if (count == words.length) result.add(left);
                } else {
                    left = right + word_len;
                    window.clear();
                    count = 0;
                }
            }
        }
        return result;
    }

    /**
     * No.76 minimum window substring
     *
     * 同上，统计字符的出现次数letters。
     */
    public String minWindow(String s, String t) {
        int[] letters = new int[58];
        int left = 0, count = 0, minLeft = -1, minLen = Integer.MAX_VALUE;
        for (int i = 0; i < t.length(); i++) {
            letters[t.charAt(i)-'A']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (--letters[s.charAt(i) - 'A'] >= 0) {
                // 大于0即为t中包含的字母
                count++;
            }
            while (count == t.length()) {
                if (minLen > i - left + 1) {
                    // 即新的包含了所有t字母substring的长度比之前的minLen小
                    minLen = i - left + 1;
                    minLeft = left;
                }
                if (++letters[s.charAt(left) - 'A'] > 0) {
                    // 大于0代表left排出substring的字母是在t中存在的
                    // count--跳出循环继续向右，因为i右侧可能还有更优解
                    count--;
                }
                left++;
            }
        }
        return minLeft == -1 ? "" : s.substring(minLeft, minLen+minLeft);
    }
}
