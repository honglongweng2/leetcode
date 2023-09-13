package Top.HashMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {
    /**
     * No.49 异位构词（如tan、nat）
     *
     * 思路：取26个质数（分解质数因），每个word可以转化成一个乘积，用map
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<Long, List<String>> map = new java.util.HashMap<>();
        // 前26个质数
        int[] arr= {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103};
        for (String str : strs) {
            long sum = 1;
            for (int i = 0; i < str.length(); i++) {
                sum *= arr[str.charAt(i) - 'a'];
            }
            if (map.containsKey(sum)) {
                List<String> oneAnagram = map.get(sum);
                oneAnagram.add(str);
                map.put(sum, oneAnagram);
            } else {
                List<String> oneAnagram = new ArrayList<>();
                oneAnagram.add(str);
                map.put(sum, oneAnagram);
            }
        }
        return new ArrayList<>(map.values());
    }
}
