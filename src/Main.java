import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] matrix = new int[][]{
                {4, 5}
        };
        Main main = new Main();
//        System.out.println((main.isValidSudoku(new char[][]{
//                {'8','3','.','.','7','.','.','.','.'},
//                {'6','.','.','1','9','5','.','.','.'},
//                {'.','9','8','.','.','.','.','6','.'},
//                {'8','.','.','.','6','.','.','.','3'},
//                {'4','.','.','8','.','3','.','.','1'},
//                {'7','.','.','.','2','.','.','.','6'},
//                {'.','6','.','.','.','.','2','8','.'},
//                {'.','.','.','4','1','9','.','.','5'},
//                {'.','.','.','.','8','.','.','7','9'}
//        })));
//        System.out.println(
//        main.rotate(new int[][] {
//                {1, 2, 2, 3, 5},
//                {3, 2, 3, 4, 4},
//                {2, 4, 5, 3, 1},
//                {6, 7, 1, 4, 5},
//                {5, 1, 1, 2, 4}
//        });
//            );
//        System.out.println(main.leastBricks(new ArrayList<List<Integer>>(){{
//            add(new ArrayList<Integer>() {{
//                add(1);
//                add(2);
//                add(2);
//                add(1);
//            }});
//            add(new ArrayList<Integer>() {{
//                add(3);
//                add(1);
//                add(2);
//            }});
//            add(new ArrayList<Integer>() {{
//                add(1);
//                add(3);
//                add(2);
//            }});
//            add(new ArrayList<Integer>() {{
//                add(2);
//                add(4);
//            }});
//            add(new ArrayList<Integer>() {{
//                add(3);
//                add(1);
//                add(2);
//            }});
//            add(new ArrayList<Integer>() {{
//                add(1);
//                add(3);
//                add(1);
//                add(1);
//            }});
//        }}));
        System.out.println(main.maximumUniqueSubarray(new int[]{187,470,25,436,538,809,441,167,477,110,275,133,666,345,411,459,490,266,987,965,429,166,809,340,467,318,125,165,809,610,31,585,970,306,42,189,169,743,78,810,70,382,367,490,787,670,476,278,775,673,299,19,893,817,971,458,409,886,434}));
    }

    public String findLongestWordOld(String s, List<String> dictionary) {
        List<String> rList = new ArrayList<>();
        Map<Character, List<Pair<Integer, String>>> dmap = new HashMap<>(26);
        for (String item : dictionary) {
            dmap.computeIfAbsent(item.charAt(0), k -> new ArrayList<>()).add(new Pair<>(0, item));
        }
        for (char c : s.toCharArray()) {
            if (!dmap.containsKey(c)) {
                continue;
            }
            List<Pair<Integer, String>> wList = dmap.get(c);
            dmap.remove(c);
            for (Pair<Integer, String> pair : wList) {
                if (pair.getKey() + 1 == pair.getValue().length()) {
                    rList.add(pair.getValue());
                }else {
                    dmap.computeIfAbsent(pair.getValue().charAt(pair.getKey() + 1), k -> new ArrayList<>()).add(new Pair<>(pair.getKey() + 1, pair.getValue()));
                }
            }
        }

        if (rList.isEmpty()) {
            return "";
        } else {
            Collections.sort(rList);
            return Collections.max(rList, Comparator.comparing(String::length));
        }
    }

    public String findLongestWord(String s, List<String> dictionary) {
        int len = s.length();
        int [][] indexes = new int[len+1][26];
        Arrays.fill(indexes[len], Integer.MAX_VALUE);
        for (int i = len - 1; i >= 0; i--){
            System.arraycopy(indexes[i + 1], 0, indexes[i], 0, 26);
            indexes[i][s.charAt(i) - 'a'] = i+1;
        }
        String result = "";
        for (String word : dictionary) {
            if (word.length() > s.length() || word.length() < result.length()) continue;
            int i = 0;
            int j = 0;
            while (j < word.length()) {
                if (indexes[i][word.charAt(j) - 'a'] == Integer.MAX_VALUE) break;
                i = indexes[i][word.charAt(j) - 'a'];
                j ++;
            }
            if (j == word.length()){
                if (word.length() > result.length() || (word.length() == result.length() && word.compareTo(result) < 0)) {
                    result = word;
                }
            }
        }
        return result;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        // 可以看成倾斜45度的搜索二叉树
        int i = 0;
        int j = matrix[0].length - 1;
        if (target < matrix[0][0]) return false;
        while (i < matrix.length && j >= 0){
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] < target) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }

    public int scoreOfParentheses(String S) {
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        for (char c : S.toCharArray()) {
            if (c == '(') {
                stack.push(result);
                result = 0;
            } else {
                result = stack.pop() + Math.max(result * 2, 1);
            }
        }
        return result;
    }

    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int start = -1;
        int end = -2;
        int max = nums[0];
        int min = nums[n-1];
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[n-i-1]);
            // 逆序或小于unsorted array最大值时一直更新end
            if (max > nums[i]) end = i;
            // 同理逆向顺序或大于unsorted array最小值时一直更新start
            if (min < nums[n-i-1]) start = n-i-1;
        }
        return end - start + 1;
    }

    public int distributeCandies(int[] candyType) {
        Set<Integer> candySet = new HashSet<>();
        for (int i : candyType) {
            candySet.add(i);
        }
        return Math.min(candySet.size(), candyType.length/2);
    }

    public int[] findErrorNums(int[] nums) {
        int sum = 0;
        int n = nums.length;
        boolean[] allNum = new boolean[n];
        int errorNum = -1;
        for (int i = 0; i < n; i++) {
            if (allNum[nums[i]-1]){
                errorNum = nums[i];
            }else {
                allNum[nums[i]-1] = true;
            }
            sum += nums[i];
        }
        return new int[]{errorNum, errorNum - sum + n*(n+1)/2};
    }

    public void nextPermutation(int[] nums) {
        int n = nums.length;
        if (n == 1) return;
        int max = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < max) {
                for (int j = i+1; j < n; j++) {
                    if (nums[j] > nums[i]) {
                        exchange(nums, i , j);
                        return;
                    }
                }
            }else {
                max = nums[i];
                for (int j = i+1; j < n; j++){
                    if (max > nums[j]) {
                        exchange(nums, j, j-1);
                    }
                }
            }
        }
    }

    private void exchange(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public int longestValidParentheses(String s) {
        int left = 0;
        int right = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            }
            if (s.charAt(i) == ')') {
                right++;
            }
            if (left == right) {
                max = Math.max(max, 2 * right);
            } else if (left < right){
                left = right = 0;
            }
        }
        // 重置并反向
        left = right = 0;
        for (int i = s.length() - 1; i >= 0 ; i--) {
            if (s.charAt(i) == ')') {
                right++;
            }
            if (s.charAt(i) == '(') {
                left++;
            }
            if (left == right) {
                max = Math.max(max, 2 * left);
            } else if (left > right){
                left = right = 0;
            }
        }
        return max;
    }
    public int longestValidParentheses2(String s) {
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            }
            if (s.charAt(i) == ')') {
                stack.pop();
                if (stack.isEmpty()) stack.push(i);
                else {
                    max = Math.max(max, i - stack.peek());
                }
            }
        }
        return max;
    }

    public int missingNumber(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        return nums.length * (nums.length + 1) / 2 - sum;
    }

    // [LeetCode] 81. Search in Rotated Sorted Array
    public int searchI(int[] nums, int target) {
        if (target > nums[0]) {
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == target) return i;
                if (nums[i] < nums[0]) break;
            }
        } else {
            for (int i = nums.length - 1; i >= 0; i--) {
                if (nums[i] == target) return i;
                if (nums[i] > nums[0]) break;
            }
        }
        return -1;
    }

    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[] {-1, -1};
        if (nums.length == 0) return res;
        int start = 0, end = nums.length;
        while (start < end) {
            if (target > nums[(start + end) / 2]) {
                start = (start + end) / 2 + 1;
            } else {
                end = (start + end) / 2;
            }
        }
        if (end == nums.length || nums[end] != target) return res;
        res[0] = end;
        end = nums.length;
        while (start < end) {
            if (target < nums[(start + end) / 2]) {
                end = (start + end) / 2 ;
            } else {
                start = (start + end) /2 + 1;
            }
        }
        res[1] = start - 1;
        return res;
    }

    public int searchInsert(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = (start + end) / 2;
            if (target == nums[mid]) {
                return mid;
            } else if (target > nums[mid]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return nums[start] < target ? start + 1 : start;
    }

    public boolean isValidSudoku(char[][] board) {
        boolean[][] row = new boolean[9][9];
        boolean[][] col = new boolean[9][9];
        boolean[][] cell = new boolean[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') continue;
                int c = board[i][j] - '1';
                if (row[i][c] || col[c][j] || cell[i/3*3+j/3][c]) return false;
                row[i][c] = col[c][j] = cell[i/3*3+j/3][c] = true;
            }
        }
        return true;
    }

    public String[] spellchecker(String[] wordlist, String[] queries) {
        String[] result = new String[queries.length];
        Set<String> set = new HashSet<>();
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        for (int i = 0; i < wordlist.length; i++) {
            set.add(wordlist[i]);
            map1.putIfAbsent(wordlist[i].toLowerCase(), i);
            map2.putIfAbsent(wordlist[i].toLowerCase().replaceAll("[aeiou]", "#"), i);
        }
        for (int i = 0; i < queries.length; i++) {
            if (set.contains(queries[i])) {
                result[i] = queries[i];
            } else if (map1.containsKey(queries[i].toLowerCase())) {
                result[i] = wordlist[map1.get(queries[i].toLowerCase())];
            } else if (map2.containsKey(queries[i].toLowerCase().replaceAll("[aeiou]", "#"))) {
                result[i] = wordlist[map2.get(queries[i].toLowerCase().replaceAll("[aeiou]", "#"))];
            } else {
                result[i] = "";
            }
        }
        return result;
    }

    public int threeSumMultiOld(int[] arr, int target) {
        long result = 0, M = (long) (1e9+7);
        Map<Integer, Integer> count = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (count.containsKey(target - arr[i] - arr[j])) {
                    result += count.get(target - arr[i] - arr[j]);
                }
            }
            count.put(arr[i], count.getOrDefault(arr[i], 0) + 1);
        }
        return (int) (result % M);
    }

    public int threeSumMulti(int[] arr, int target) {
        long result = 0;
        long[] count = new long[101];
        for (int i : arr) {
            ++count[i];
        }
        for (int i = 0; i < count.length; i++) {
            if (count[i] == 0) continue;
            if (i > target) break;
            for (int j = i; j < count.length; j++) {
                if (count[j] == 0) continue;
                int third = target - i - j;
                if (third > 100 || third < 0) continue;
                if (count[third] == 0) continue;
                if (i == j && j == third) {
                    // 同个数字中选3，组合
                    result += count[i] * (count[i] - 1) * (count[i] - 2) / 6;
                }else if (i == j) {
                    // 同个数字中选2，组合
                    result += count[i] * (count[i] - 1) / 2 * count[third];
                }else if (j < third) {
                    result += count[i] * count[j] * count[third];
                }
            }
        }
        return (int) (result % (1e9 + 7));
    }

    public int maxProfit(int[] prices, int fee) {
        int result = 0;
        int buy = 0, sell = 0;
        int step = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < prices[buy]) {
                // 出现更低买入价
                buy = sell = i;
            }
            if (prices[i] > prices[sell]) {
                sell = i;
                int trade = prices[sell] - prices[buy] - fee;
                if (trade > 0) {
                    // 一次有效的交易
                    result += Math.max(trade, step > 0 ? prices[sell] - prices[step] : 0);
                    buy = step = i;
                }
            }
            if (prices[i] > prices[step] && step > 0) {
                // 重置前一次交易
                result += prices[sell] - prices[step];
                step = i;
            }
        }
        return result;
    }

    public int greed(int[] prices, int fee) {
        int profit=0;
        int curProfit=0;
        int minP=prices[0];
        int maxP=prices[0];
        for(int i=1; i<prices.length; i++) {
            minP = Math.min(minP, prices[i]);
            maxP = Math.max(maxP, prices[i]);
            curProfit = Math.max(curProfit, prices[i] - minP - fee);
            if ((maxP - prices[i]) >= fee) {
                profit += curProfit;
                curProfit = 0;
                minP = prices[i];
                maxP = prices[i];
            }
        }
        return profit + curProfit;
    }

    public int[] advantageCount(int[] A, int[] B) {
        int[] result = new int[A.length];
        TreeMap<Integer, Integer> aTree = new TreeMap<>();
        for (int i : A) {
            Integer count = aTree.getOrDefault(i, 0);
            aTree.put(i, count+1);
        }
        for (int i = 0; i < B.length; i++) {
            Integer higher = aTree.higherKey(B[i]);
            if (higher == null) {
                higher = aTree.firstKey();
            }
            result[i] = higher;
            int count = aTree.get(higher);
            if (count == 1) {
                aTree.remove(higher);
            } else {
                aTree.put(higher, count - 1);
            }
        }
        return result;
    }

    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> result = new ArrayList<>();
        int m = matrix.length;
        if (m == 0) return result;
        int n = matrix[0].length;
        if (n == 0) return result;

        boolean[][] pVisit = new boolean[m][n];
        boolean[][] aVisit = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            dfs(i, 0, m, n, pVisit, matrix, -1);
            dfs(i, n - 1, m, n, aVisit, matrix, -1);
        }
        for (int i = 0; i < n; i++) {
            dfs(0, i, m, n, pVisit, matrix, -1);
            dfs(m - 1, i, m, n, aVisit, matrix, -1);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pVisit[i][j] && aVisit[i][j]) {
                    result.add(new ArrayList<>(Arrays.asList(i, j)));
                }
            }
        }
        return result;
    }

    private int[][] directs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    private void dfs (int x, int y, int m, int n, boolean[][] visit, int[][] matrix, int height) {
        if (x < 0 || x >= m || y < 0 || y >= n || visit[x][y] || matrix[x][y] < height) return;
        visit[x][y] = true;
        for (int[] dir : directs) {
            dfs(x + dir[0], y + dir[1], m, n, visit, matrix, matrix[x][y]);
        }
    }

    public int minimumLengthEncoding(String[] words) {
        Set<String> wordSet = new HashSet<>(Arrays.asList(words));
        for (String s : words) {
            for (int i = 1; i <s.length(); i++) {
                wordSet.remove(s.substring(i));
            }
        }
        return wordSet.stream().mapToInt(word -> word.length() + 1).sum();
    }

    public List<String> wordSubsets(String[] A, String[] B) {
        List<String> result = new ArrayList<>();
        int[] bCount = new int[26];
        for (String s : B) {
            int[] sCount = countChar(s);
            for (int i = 0; i < bCount.length; i++) {
                bCount[i] = Math.max(sCount[i], bCount[i]);
            }
        }
        for (String s : A) {
            int[] sCount = countChar(s);
            for (int i = 0; i < bCount.length; i++) {
                if (sCount[i] < bCount[i]) break;
                if (i == 25) result.add(s);
            }
        }
        return result;
    }

    private int[] countChar(String word) {
        int[] result = new int[26];
        for (int i = 0; i < word.length(); i++) {
            result[word.charAt(i) - 'a']++;
        }
        return result;
    }

    public String originalDigits(String s) {
        StringBuilder result = new StringBuilder();
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        int[] nums = new int[10];
        nums[0] = count['z' - 'a'];
        nums[2] = count['w' - 'a'];
        nums[4] = count['u' - 'a'];
        nums[6] = count['x' - 'a'];
        nums[8] = count['g' - 'a'];
        nums[1] = count['o' - 'a'] - nums[0] - nums[2] - nums[4];
        nums[3] = count['h' - 'a'] - nums[8];
        nums[5] = count['f' - 'a'] - nums[4];
        nums[7] = count['s' - 'a'] - nums[6];
        nums[9] = count['i' - 'a'] - nums[5] - nums[6] - nums[8];
        for (int i = 0; i < nums.length; i++) {
            for (int i1 = 0; i1 < nums[i]; i1++) {
                result.append(i);
            }
        }
        return result.toString();
    }

    public int countSubstringsOld(String s) {
        // dp
        boolean[][] dp = new boolean[s.length()][s.length()];
        int result = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                dp[i][j] = (s.charAt(i) == s.charAt(j)) && (j - i <= 2 || dp[i+1][j-1]);
                if (dp[i][j]) ++result;
            }
        }
        return result;
    }

    public int countSubstrings(String s) {
        // expand
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            // odd
            result += expandPalindromic(s, i, i);
            // even
            result += expandPalindromic(s, i , i+1);
        }
        return result;
    }

    private int expandPalindromic(String s, int i, int j) {
        int result = 0;
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            --i;
            ++j;
            ++result;
        }
        return result;
    }

    public int removePalindromeSub(String s) {
        if (s.isEmpty()) return 0;
        StringBuilder sb = new StringBuilder(s);
        if (sb.reverse().toString().equals(s)) return 1;
        return 2;
    }

    public String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] strings = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                sb.append(strings[i]);
                num -= values[i];
            }
        }
        return sb.toString();
    }

    public int coinChangeOld(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        for (int i = 1; i <= amount; i++) {
            // 初始化一个不可能的大值
            dp[i] = amount + 1;
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public int coinChange(int[] coins, int amount) {
        result = amount + 1;
        Arrays.sort(coins);
        dfsCoinChange(coins, coins.length - 1, amount, 0);
        return result > amount ? -1 : result;
    }

    private void dfsCoinChange(int[] coins, int start, int target, int current) {
        if (start < 0) return;
        if (target % coins[start] == 0) {
            // 整除
            result = Math.min(result, current + target / coins[start]);
            return;
        }
        for (int i = target / coins[start]; i >= 0; i--) {
            if (current + i >= result - 1) {
                // result 至少要比current+i大1，才有可能在之后的递归中更新result更小
                break;
            }
            dfsCoinChange(coins, start - 1, target - i * coins[start], current + i);
        }
    }

    private int result;

    public boolean hasAllCodes(String s, int k) {
        int total = 1 << k;
        if (s.length() < total + k - 1) return false;
        boolean[] hasCode = new boolean[total];
        char[] sChars = s.toCharArray();
        int carry = 0, allOne = total - 1;
        // 初始化前k-1位
        for (int i = 0; i < k-1; i++) {
            carry = (carry << 1) + (sChars[i] - '0');
        }
        for (int i = k-1; i < s.length(); i++) {
            // &操作是消除k+1位
            carry = ((carry << 1) + (sChars[i] - '0')) & allOne;
            if (!hasCode[carry]) {
                hasCode[carry] = true;
                if (--total == 0) return true;
            }
        }
        return false;
    }

    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0 || envelopes[0] == null || envelopes[0].length != 2) return 0;
        Arrays.sort(envelopes, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                // 宽正序，高倒序，以让高采用最长递增子序列的算法
                return o2[1] - o1[1];
            } else {
                return o1[0] - o2[0];
            }
        });
        int[] dp = new int[envelopes.length];
        int length = 0;
        for (int[] envelope : envelopes) {
            int index = Arrays.binarySearch(dp, 0, length, envelope[1]);
            if (index < 0) {
                // index找不到时返回的时插入点，此处转换成替换点
                index = -(index + 1);
            }
            dp[index] = envelope[1];
            if (index == length) length++;
        }
        return length;
    }

    public boolean reorderedPowerOf2(int n) {
        long sum = powerOfTenSum(n);
        for (int i = 0; i < 31; i++) {
            if (powerOfTenSum(1 << i) == sum) return true;
        }
        return false;
    }

    private long powerOfTenSum(int n) {
        long result = 0;
        for(; n > 0; n/=10) {
            result += Math.pow(10, n % 10);
        }
        return result;
    }

    public int[] movesToStamp(String stamp, String target) {
        char[] targetArray = target.toCharArray();
        List<Integer> result = new ArrayList<>();
        int stampedLength = 0;
        boolean[] stampedIndex = new boolean[target.length()];
        while (stampedLength < targetArray.length) {
            boolean isStamped = false;
            for (int i = 0; i <= targetArray.length - stamp.length(); i++) {
                if (stampedIndex[i]) continue;
                int oneStep = unStamp(stamp, targetArray, i);
                if (oneStep == 0) continue;
                stampedLength += oneStep;
                isStamped = true;
                stampedIndex[i] = true;
                result.add(i);
            }
            if (!isStamped) return new int[]{};
        }
        Collections.reverse(result);
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    private int unStamp(final String stamp, char[] targetArray, int index) {
        int stampLength = stamp.length();
        for (int i = 0; i < stamp.length(); i++) {
            if (targetArray[index + i] == '*') {
                --stampLength;
            } else if (targetArray[index + i] != stamp.charAt(i)) {
                return 0;
            }
        }
        Arrays.fill(targetArray, index, index + stamp.length(), '*');
        return stampLength;
    }

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] isVisited = new boolean[rooms.size()];
        queue.add(0);
        while (!queue.isEmpty()) {
            int room = queue.poll();
            isVisited[room] = true;
            for (Integer key : rooms.get(room)) {
                if (isVisited[key]) continue;
                queue.add(key);
            }
        }
        for (boolean b : isVisited) {
            if (!b) return false;
        }
        return true;
    }

    public int wiggleMaxLength(int[] nums) {
        int increase = 1, decrease = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i-1]) {
                increase = decrease + 1;
            } else if (nums[i] < nums[i-1]){
                decrease = increase + 1;
            }
        }
        return Math.min(nums.length, Math.max(increase, decrease));
    }

    public int numFactoredBinaryTrees(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        long result = 0, M = (long) (1e9 + 7);
        Arrays.sort(arr);
        Map<Integer, Long> dp = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            dp.put(arr[i], (long) 1);
            for (int j = 0; j < i; j++) {
                if (arr[i] % arr[j] == 0 && dp.containsKey(arr[i] / arr[j])) {
                    dp.put(arr[i], (dp.get(arr[i]) + dp.get(arr[j]) * dp.get(arr[i] / arr[j])) % M);
                }
            }
        }
        for (Map.Entry<Integer, Long> entry : dp.entrySet()) {
            Long value = entry.getValue();
            result = (result + value) % M;
        }
        return (int) result;
    }

    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m+1][n+1];
        for (String str : strs) {
            int zero = 0;
            int one = 0;
            for (int j = 0; j < str.length(); j++) {
                zero += '1' - str.charAt(j);
                one += str.charAt(j) - '0';
            }
            for (int i = m; i >= zero ; i--) {
                for (int j = n; j >= one ; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-zero][j-one] + 1);
                }
            }
        }
        return dp[m][n];
    }

    public boolean isIdealPermutation(int[] A) {
        int max = -1;
        for (int i = 0; i < A.length - 2; i++) {
            max = Math.max(max, A[i]);
            if (A[i+2] < max) return false;
        }
        return true;
    }

    public boolean halvesAreAlike(String s) {
        int n = s.length();
        int count = 0;
        char[] vowels = new char[] {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
        for (int i = 0; i < n/2; i++) {
            for (char vowel : vowels) {
                if (s.charAt(i) == vowel) count++;
                if (s.charAt(n-1-i) == vowel) count--;
            }
        }
        return count == 0;
    }

    private String[] phoneNumbers = new String[] {"", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinationsOld(String digits) {
        List<String> result = new ArrayList<>();
        if (digits.length() == 0) return result;
        result.add("");
        for (int i = 0; i < digits.length(); i++) {
            List<String> temp = new ArrayList<>();
            String str = phoneNumbers[digits.charAt(i) - '1'];
            for (int j = 0; j < str.length(); j++) {
                for (String s : result) {
                    temp.add(s+str.charAt(j));
                }
            }
            result = temp;
        }
        return result;
    }

    public List<String> letterCombinations(String digits) {

        List<String> result = new ArrayList<>();
        if(digits == null || digits.length() == 0) return result;
        letterDFS(0, new StringBuilder(), digits, result);
        return result;
    }

    private void letterDFS(int i, StringBuilder current, String digits, List<String> result) {
        if (i == digits.length()) {
            result.add(current.toString());
            return;
        }
        char currentChar = digits.charAt(i);
        for (char neighbor : phoneNumbers[currentChar - '1'].toCharArray()) {
            current.append(neighbor);
            letterDFS(i+1, current, digits, result);
            current.deleteCharAt(current.length() - 1);
        }
    }

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        int result = 1, m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result = Math.max(result, pathDFS(matrix, dp, i, j));
            }
        }
        return result;
    }

    private int pathDFS(int[][] matrix, int[][] dp, int i, int j) {
        if (dp[i][j] != 0) return dp[i][j];
        int max = 1, m = matrix.length, n = matrix[0].length;
        for (int[] direct : directs) {
            int x = i + direct[0], y = j + direct[1];
            if (x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] <= matrix[i][j]) {
                continue;
            }
            int length = 1 + pathDFS(matrix, dp, x, y);
            max = Math.max(max, length);
        }
        dp[i][j] = max;
        return max;
    }

    public boolean isAlienSorted(String[] words, String order) {
        Map<Character, Integer> charMap = new HashMap<>(26);
        for (int i = 0; i < order.length(); i++) {
            charMap.put(order.charAt(i), i);
        }
        for (int i = 1; i < words.length; i++) {
            String first = words[i-1], second = words[i];
            for (int j = 0; j < first.length() && j < second.length(); j++) {
                if (first.charAt(j) == second.charAt(j)) continue;
                if (charMap.get(first.charAt(j)) > charMap.get(second.charAt(j))) return false;
                else break;
            }
            if (first.length() > second.length() && first.substring(0, second.length()).equals(second)) return false;
        }
        return true;
    }

    public int[] constructArray(int n, int k) {
        int[] result = new int[n];
        int i = 1, j = n, index = 0;
        while (i <= j) {
            if (k > 1) {
                result[index] = k-- % 2 == 1 ? i++ : j--;
                index++;
            }
            else {
                result[index] = i++;
                index++;
            }
        }
        return result;
    }

    public int fibOld(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fib(n - 2) + fib(n - 1);
    }

    public int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        int first = 0, second = 1;
        while (n >= 2) {
            int sum = first + second;
            first = second;
            second = sum;
            n--;
        }
        return second;
    }

    public String countAndSay(int n) {
        String result = "1";
        while (--n > 0) {
            result = count(result);
        }
        return result;
    }

    private String count(String word) {
        StringBuilder sb = new StringBuilder();
        char temp = word.charAt(0);
        int count = 1;
        for (int i = 1; i < word.length(); i++) {
            if (temp == word.charAt(i)) {
                count++;
            } else {
                sb.append(count);
                sb.append(temp);
                temp = word.charAt(i);
                count = 1;
            }
        }
        if (count > 0) {
            sb.append(count);
            sb.append(temp);
        }
        return sb.toString();
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length == 0) return result;
        Arrays.sort(candidates);
        recursiveCombinationSum(candidates, target, result, new ArrayList<>(), 0);
        return result;
    }

    private void recursiveCombinationSum(int[] candidates, int target, List<List<Integer>> result, List<Integer> path, int start) {
        if (target == 0) {
            result.add(new ArrayList<>(path));
        } else if (target > 0) {
            for (int i = start; i < candidates.length; i++) {
                if (candidates[i] > target) {
                    break;
                }
                path.add(candidates[i]);
                recursiveCombinationSum(candidates, target - candidates[i], result, path, i);
                path.remove(path.size() - 1);
            }
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length == 0) return result;
        Arrays.sort(candidates);
        recursiveCombinationSum2(candidates, target, result, new ArrayList<>(), 0);
        return result;
    }

    private void recursiveCombinationSum2(int[] candidates, int target, List<List<Integer>> result, List<Integer> path, int start) {
        if (target == 0) {
            result.add(new ArrayList<>(path));
        } else if (target > 0) {
            for (int i = start; i < candidates.length; i++) {
                if (candidates[i] > target) {
                    break;
                }
                if (i > start && candidates[i] == candidates[i-1]) {
                    continue;
                }
                path.add(candidates[i]);
                recursiveCombinationSum2(candidates, target - candidates[i], result, path, i+1);
                path.remove(path.size() - 1);
            }
        }
    }

    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] > 0 && nums[i] < nums.length && nums[nums[i] - 1] != nums[i]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) return i + 1;
        }
        return nums.length + 1;
    }

    public int trap(int[] height) {
        int result = 0, left = 0, right = height.length - 1;
        while (left < right) {
            if (height[left] <= height[right]) {
                int c = height[left];
                left++;
                while (height[left] < c) {
                    result += c - height[left];
                    left++;
                }
            } else {
                int c = height[right];
                right--;
                while (height[right] < c) {
                    result += c - height[right];
                    right--;
                }
            }
        }
        return result;
    }

    public String multiply(String num1, String num2) {
        int[] values = new int[num1.length() + num2.length()];
        StringBuilder sb = new StringBuilder();
        for (int i = num1.length() - 1; i >= 0; i--) {
            for (int j = num2.length() - 1; j >= 0; j--) {
                int multiply = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int sum = multiply + values[i+j+1];
                values[i+j] += sum / 10;
                values[i+j+1] = sum % 10;
            }
        }
        for (int value : values) {
            if (sb.length() > 0 || value != 0) {
                sb.append(value);
            }
        }
        return sb.length() > 0 ? sb.toString() : "0";
    }

    public String removeDuplicatesOld(String s, int k) {
        Stack<Pair<Character, Integer>> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (stack.isEmpty() || stack.peek().getKey() != s.charAt(i)) {
                stack.push(new Pair<>(s.charAt(i), 1));
            } else {
                Pair<Character, Integer> pair = stack.pop();
                if (pair.getValue() + 1 < k) {
                    stack.push(new Pair<>(s.charAt(i), pair.getValue() + 1));
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Pair<Character, Integer> pair : stack) {
            for (int i = 0; i < pair.getValue(); i++) {
                sb.append(pair.getKey());
            }
        }
        return sb.toString();
    }

    public String removeDuplicates(String s, int k) {
        if (s.length() == 0) return "";
        int count = 1;
        char last = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == last) {
                count++;
            } else {
                count = 1;
                last = s.charAt(i);
            }
            if (count == k) {
                return removeDuplicates(s.substring(0, i - k + 1) + s.substring(i + 1), k);
            }
        }
        return s;
    }

    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int result = 0, m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                matrix[i][j] += matrix[i][j-1];
            }
        }
        Map<Integer, Integer> counter = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                counter.clear();
                counter.put(0, 1);
                int sum = 0;
                for (int[] ints : matrix) {
                    sum += ints[j] - (i == 0 ? 0 : ints[i - 1]);
                    if (counter.containsKey(sum - target)) {
                        result += counter.get(sum - target);
                    }
                    counter.put(sum, counter.getOrDefault(sum, 0) + 1);
                }
            }
        }
        return result;
    }

    public int subarraySum(int[] nums, int k) {
        // presum
        int result = 0, sum = 0;
        Map<Integer, Integer> counter = new HashMap<>();
        counter.put(0, 1);
        for (int num : nums) {
            sum += num;
            if (counter.containsKey(sum - k)) {
                result += counter.get(sum - k);
            }
            counter.put(sum, counter.getOrDefault(sum, 0) + 1);
        }
        return result;
    }

    public int combinationSum4Old(int[] nums, int target) {
        // 果然超时了
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result += recursiveCombinationSum4(nums, target, i);
        }
        return result;
    }

    private int recursiveCombinationSum4(int[] nums, int target, int start) {
        if (nums[start] > target) {
            return 0;
        } else if (nums[start] == target) {
            return 1;
        } else {
            // 从数组起始开始遍历
            return combinationSum4(nums, target - nums[start]);
        }
    }

    public int combinationSum4(int[] nums, int target)  {
        int[] dp = new int[target+1];
        dp[0] = 1;
        for (int i = 1; i < dp.length; i++) {
            for (int num : nums) {
                if (i >= num) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }

    public boolean isMatch(String s, String p) {
        int i = 0, j = 0, iStar = -1, jStar = -1, m = s.length(), n = p.length();
        while (i < m) {
            if (j < n && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')) {
                // 单字匹配直接下一个
                i++;
                j++;
            } else if (j < n && p.charAt(j) == '*') {
                // iStar记录*匹配的string最后一位，jStar记录pattern里*的位置
                iStar = i;
                jStar = j++;
            } else if (iStar >= 0) {
                // 未匹配且前文有*匹配时，可顺延一位来尝试匹配
                i = ++iStar;
                j = jStar + 1;
            } else {
                return false;
            }
        }
        while (j < n && p.charAt(j) == '*') {
            // 收pattern末尾多余的*
            j++;
        }
        return j==n;
    }

    public boolean canJump(int[] nums) {
        int far = nums[0];
        for (int i = 1; i < nums.length - 1; i++) {
            if (far >= i) {
                far = Math.max(far, nums[i] + i);
            }
        }
        return far >= nums.length - 1;
    }

    public int jump(int[] nums) {
        // dp 记录跳到i的步数
        // 贪婪 保证下一步跳的最大
        int result = 0, current_far = 0, far = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            far = Math.max(far, nums[i]+i);
            if (i == current_far) {
                result++;
                current_far = far;
            }
        }
        return result;
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        for (int i = triangle.size() - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                triangle.get(i).set(j, triangle.get(i).get(j) + Math.min(triangle.get(i+1).get(j), triangle.get(i+1).get(j+1)));
            }
        }
        return triangle.get(0).get(0);
    }

    public List<List<Integer>> permuteOld(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> oneOut = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        permuteDFS(nums, visited, oneOut, result);
        return result;
    }

    private void permuteDFS(int[] nums, boolean[] visited, List<Integer> oneOut, List<List<Integer>> result) {
        if (oneOut.size() == nums.length) {
            result.add(new ArrayList<>(oneOut));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            oneOut.add(nums[i]);
            permuteDFS(nums, visited, oneOut, result);
            oneOut.remove(oneOut.size() - 1);
            visited[i] = false;
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        permuteSwap(nums, result, 0);
        return result;
    }

    private void permuteSwap(int[] nums, List<List<Integer>> result, int level) {
        if (level == nums.length) {
            List<Integer> oneOut = new ArrayList<>();
            for (int num : nums) {
                oneOut.add(num);
            }
            result.add(oneOut);
        }
        for (int i = level; i < nums.length; i++) {
            // 对permuteUnique只需要加一个Set来指明哪个数字被使用即可
            swap(nums, level, i);
            permuteSwap(nums, result, level+1);
            swap(nums, level, i);
        }
    }

    private void swap(int[] nums, int i, int j) {
        if (i == j) return;
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> oneOut = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        Arrays.sort(nums);
        permuteUniqueDFS(nums, visited, oneOut, result);
        return result;
    }

    private void permuteUniqueDFS(int[] nums, boolean[] visited, List<Integer> oneOut, List<List<Integer>> result) {
        if (oneOut.size() == nums.length) {
            result.add(new ArrayList<>(oneOut));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;
            if (i > 0 && nums[i] == nums[i-1] && visited[i-1]) continue;
            visited[i] = true;
            oneOut.add(nums[i]);
            permuteUniqueDFS(nums, visited, oneOut, result);
            oneOut.remove(oneOut.size() - 1);
            visited[i] = false;
        }
    }

    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> map = new HashMap<>();
        int max= 0;
        for (List<Integer> cell : wall) {
            int cellSum = 0;
            for (int i = 0; i < cell.size()-1; i++) {
                Integer brick = cell.get(i);
                cellSum += brick;
                map.put(cellSum, map.getOrDefault(cellSum, 0) + 1);
                max = Math.max(map.get(cellSum), max);
            }
        }
        return wall.size() - max;
    }

    public void rotate(int[][] matrix) {
        // 方法1：直接四点互换，[i,j],[n-1-j, i],[n-1-i,n-1-j][j,n-1-i]
        int n = matrix.length;
        for (int i = 0; i < n/2; i++) {
            for (int j = i; j < n-1-i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n-1-j][i];
                matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
                matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
                matrix[j][n-1-i] = temp;
            }
        }
    }
    public void rotate(int[][] matrix, int type) {
        // 方法2：先斜对角互换，在左右或上下翻转
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
            for (int j = 0; j < n/2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n-1-j];
                matrix[i][n-1-j] = temp;
            }
        }
    }

    public double myPow(double x, int n) {
        if (n < 0) return 1 / myPow(x, -n-1) / x;
        double result = 1;
        // 想法是把n变成二进制，如10=2^3+2^1，所以2^10 = 2^(2^3)*2^(2^1)
        while (n > 0) {
            if ((n & 1) == 1) {
                result *= x;
            }
            x *= x;
            n = n >> 1;
        }
        return result;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<Long, List<String>> map = new HashMap<>();
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

    public List<List<String>> solveNQueens(int n) {
        boolean[][] matrix = new boolean[n][n];
        List<List<String>> result = new ArrayList<>();
        recursiveQueens(matrix, 0, result);
        return result;
    }

    private void recursiveQueens(boolean[][] matrix, int row, List<List<String>> result){
        if (row == matrix.length) {
            StringBuilder sb;
            List<String> oneSolution = new ArrayList<>();
            for (boolean[] oneRow : matrix) {
                sb = new StringBuilder();
                for (boolean column : oneRow) {
                    if (column) {
                        sb.append('Q');
                    } else {
                        sb.append('.');
                    }
                }
                oneSolution.add(sb.toString());
            }
            result.add(oneSolution);
        }
        for (int i = 0; i < matrix.length; i++) {
            if (isQueenValid(matrix, row, i)) {
                matrix[row][i] = true;
                recursiveQueens(matrix, row+1, result);
                matrix[row][i] = false;
            }
        }
    }

    private boolean isQueenValid(boolean[][] matrix, int i, int j) {
        for (int k = 0; k < i; k++) {
            if (matrix[k][j]) return false;
        }
        for (int k = 1; k <= i && k <= j; k++) {
            if (matrix[i-k][j-k]) return false;
        }
        for (int k = 1; k <=i && j+k < matrix.length; k++) {
            if (matrix[i-k][j+k]) return false;
        }
        return true;
    }

    public int totalNQueens(int n) {
        // 方法二，用三个数组分别记录列，对角线和逆对角是否有子。
        // 对角线，比如（1，0）（2，1）在对角线上，row-col的值相同。逆对角线同理，row+col相同。
        // 大小为2n是因为有2n-1条对角线
        boolean[] columns = new boolean[n], diag = new boolean[2*n], antiDiag = new boolean[2*n];
        result = 0;
        recursiveTotalQueens(columns, diag, antiDiag, 0);
        return result;
    }

    private void recursiveTotalQueens(boolean[] col, boolean[] diag, boolean[] anti, int row) {
        if (row == col.length) {
            result++;
        }
        for (int i = 0; i < col.length; i++) {
            int temp = i-row+col.length;
            if (col[i] || diag[temp] || anti[i+row]) continue;
            col[i] = diag[temp] = anti[i+row] = true;
            recursiveTotalQueens(col, diag, anti, row + 1);
            col[i] = diag[temp] = anti[i+row] = false;
        }
    }

    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE, currentMax =0;
        for (int num : nums) {
            currentMax = Math.max(num, currentMax+num);
            max = Math.max(max, currentMax);
        }
        return max;
    }

    public int countBinarySubstrings(String s) {
        int result = 0, count = 1, pre = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i-1)) {
                count++;
            } else {
                pre = count;
                count = 1;
            }
            if (pre >= count) result++;
        }
        return result;
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int up = 0, down = m - 1, left = 0, right = n - 1;
        List<Integer> result = new ArrayList<>();
        while (true) {
            for (int i = left; i <= right; i++) {
                result.add(matrix[up][i]);
            }
            if (++up > down) break;
            for (int i = up; i <= down; i++) {
                result.add(matrix[i][right]);
            }
            if (--right < left) break;
            for (int i = right; i >= left; i--) {
                result.add(matrix[down][i]);
            }
            if (--down < up) break;
            for (int i = down; i >= up; i--) {
                result.add(matrix[i][left]);
            }
            if (++left > right) break;
        }
        return result;
    }

    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int left = 0, right = n - 1, up = 0, down = n - 1, index = 1;
        while (true) {
            for (int i = left; i <= right; i++) {
                result[up][i] = index++;
            }
            if (++up > down) break;
            for (int i = up; i <= down; i++) {
                result[i][right] = index++;
            }
            if (--right < left) break;
            for (int i = right; i >= left; i--) {
                result[down][i] = index++;
            }
            if (--down < up) break;
            for (int i = down; i >= up; i--) {
                result[i][left] = index++;
            }
            if (++left > right) break;
        }
        return result;
    }

    public int[][] merge(int[][] intervals) {
        ArrayList<int[]> res = new ArrayList<>();
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        int[] current = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (current[1] >= intervals[i][0]) {
                current[1] = Math.max(current[1], intervals[i][1]);
            } else {
                res.add(current);
                current = intervals[i];
            }
        }
        res.add(current);
        return res.toArray(new int[0][]);
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        ArrayList<int[]> res = new ArrayList<>();
        if (intervals == null || intervals.length == 0) return new int[][] {newInterval};
        boolean found = false;
        for (int[] interval : intervals) {
            if (found || interval[1] < newInterval[0]) {
                // 在newInterval左，直接插入
                res.add(interval);
            } else if (interval[0] > newInterval[1]) {
                // newInterval在两个中间，且无交集
                res.add(new int[] {newInterval[0], newInterval[1]});
                res.add(interval);
                found = true;
            } else {
                // 存在交集
                newInterval[0] = Math.min(interval[0], newInterval[0]);
                newInterval[1] = Math.max(interval[1], newInterval[1]);
            }
        }
        if (!found) {
            res.add(new int[] {newInterval[0], newInterval[1]});
        }
        return res.toArray(new int[0][]);
    }

    public int lengthOfLastWord(String s) {
        int result = 0, n = s.length();
        while (n > 0 && s.charAt(n - 1) == ' ') n--;
        while (n > 0 && s.charAt(n - 1) != ' ') {
            result++;
            n--;
        }
        return result;
    }

    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int i = 1; i < heights.length; i++) {
            if (heights[i] > heights[i-1]) {
                heap.offer(heights[i] - heights[i-1]);
                if (heap.size() > ladders) {
                    bricks -= heap.poll();
                }
                if (bricks < 0) {
                    return i - 1;
                }
            }
        }
        return heights.length - 1;
    }

    public String getPermutation(int n, int k) {
        StringBuilder sb = new StringBuilder();
        boolean[] isUsed = new boolean[n];
        int multiply = 1;
        for (int i = 2; i < n; i++) {
            multiply *= i;
        }
        while (n != 1) {
            int used = (k-1) / multiply;
            k -= used * multiply;
            for (int i = 0; i < isUsed.length; i++) {
                if (isUsed[i]) continue;
                if (!isUsed[i] && used == 0) {
                    sb.append(i+1);
                    isUsed[i] = true;
                    break;
                }
                used--;
            }
            multiply /= n-1;
            n = n-1;
        }
        for (int i = 0; i < isUsed.length; i++) {
            if (!isUsed[i]) sb.append(i+1);
        }
        return sb.toString();
    }

    public int uniquePaths(int m, int n) {
        // 一共m+n-2步，选m-1步向下
        long result = 1, multiply = m+n-2;
        for (int i = 1; i < Math.min(m, n); i++) {
            result = result * multiply / i;
            multiply--;
        }
        return (int)result;
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1) return 0;
        obstacleGrid[0][0] = 1;
        for (int i = 1; i < m; i++) {
            if (obstacleGrid[i][0] == 1 || obstacleGrid[i-1][0] == 0) {
                obstacleGrid[i][0] = 0;
            } else {
                obstacleGrid[i][0] = 1;
            }
        }
        for (int i = 1; i < n; i++) {
            if (obstacleGrid[0][i] == 1 || obstacleGrid[0][i-1] == 0){
                obstacleGrid[0][i] = 0;
            } else {
                obstacleGrid[0][i] = 1;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    obstacleGrid[i][j] = 0;
                } else {
                    obstacleGrid[i][j] = obstacleGrid[i-1][j] + obstacleGrid[i][j-1];
                }
            }
        }
        return obstacleGrid[m-1][n-1];
    }

    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int i = 1; i < m; i++) {
            grid[i][0] += grid[i-1][0];
        }
        for (int i = 1; i < n; i++) {
            grid[0][i] += grid[0][i-1];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                grid[i][j] += Math.min(grid[i-1][j], grid[i][j-1]);
            }
        }
        return grid[m-1][n-1];
    }

    public boolean isNumberOld(String s) {
        boolean hasE = false, hasPoint = false, hasNum = false, numAfterE = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                if (i == 0 || s.charAt(i-1) == 'e' || s.charAt(i-1) == 'E') {
                    continue;
                } else {
                    return false;
                }
            }
            if (s.charAt(i) == '.') {
                if (hasE || hasPoint) {
                    return false;
                } else {
                    hasPoint = true;
                    continue;
                }
            }
            if (s.charAt(i) == 'e' || s.charAt(i) == 'E') {
                if (hasE || !hasNum) {
                    return false;
                } else {
                    hasE = true;
                    numAfterE = false;
                    continue;
                }
            }
            int distance = s.charAt(i) - '0';
            if (distance <= 9 && distance >= 0) {
                hasNum = true;
                numAfterE = true;
            } else {
                return false;
            }
        }
        return numAfterE && hasNum;
    }

    public boolean isNumber(String s) {
        // 有限自动机
        int[][] tranferTable = new int[][] {
                // 用于初始化和输入了空格的状态
                {-1, 0, 3, 1, 2, -1},
                // 输入了数字的状态
                {-1, 8, -1, 1, 4, 5},
                // 第一个输入是.
                {-1, -1, -1, 4, -1, -1},
                // 输入了符号
                {-1, -1, -1, 1, 2, -1},
                // 数字加.
                {-1, 8, -1, 4, -1, 5},
                // e
                {-1, -1, 6, 7, -1, -1},
                // e加符号
                {-1, -1, -1, 7, -1, -1},
                // e加数字
                {-1, -1, -1, 7, -1, -1},
                // 中间空格
                {-1, 8, -1, -1, -1, -1}
        };
        int state = 0;
        for (int i = 0; i < s.length(); i++) {
            InputType inputType = InputType.INVALID;
            if (s.charAt(i) == ' ') {
                inputType = InputType.SPACE;
            } else if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                inputType = InputType.SIGN;
            } else if (isDigit(s.charAt(i))) {
                inputType = InputType.DIGIT;
            } else if (s.charAt(i) == '.') {
                inputType = InputType.DOT;
            } else if (s.charAt(i) == 'e' || s.charAt(i) == 'E') {
                inputType = InputType.EXPONENT;
            }
            state = tranferTable[state][inputType.ordinal()];
            if (state == -1) return false;
        }
        return state == 1 || state == 4 || state == 7 || state == 8;
    }

    private boolean isDigit(char value) {
        int distance = value - '0';
        return distance >= 0 && distance <=9;
    }

    public boolean isPowerOfThree(int n) {
        return n > 0 && (int)(Math.log10(n) / Math.log10(3)) == Math.log10(n) / Math.log10(3);
    }

    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i]%=10;
            if (digits[i] != 0) {
                return digits;
            }
        }
        int[] result = new int[digits.length+1];
        // 无需复制数组，因为到这一步必然是全9
        result[0] = 1;
        return result;
    }

    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int m = a.length() - 1, n = b.length() - 1, carry = 0;
        while (m >= 0 || n >= 0) {
            int p = m >= 0 ? a.charAt(m--) - '0' : 0;
            int q = n >= 0 ? b.charAt(n--) - '0' : 0;
            int sum = p+q+carry;
            sb.append(sum % 2);
            carry = sum / 2;
        }
        if (carry == 1) {
            sb.append(1);
        }
        return sb.reverse().toString();
    }

    public List<String> fullJustify(String[] words, int maxWidth) {
        StringBuilder sb = new StringBuilder();
        List<String> result = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        // 初始化-1，n个词最少会存在n-1个空格
        int length = -1;
        for (String word : words) {
            if (length + word.length() + 1 > maxWidth) {
                int spaceTotal = maxWidth - length;
                for (int i = temp.size() - 1; i >= 1; i--) {
                    if (spaceTotal % i == 0) {
                        sb.append(temp.get(temp.size() - i - 1));
                        repeatAdd(sb, ' ', spaceTotal / i);
                        spaceTotal -= spaceTotal / i;
                    } else {
                        sb.append(temp.get(temp.size() - i - 1));
                        repeatAdd(sb, ' ', spaceTotal / i + 1);
                        spaceTotal -= spaceTotal / i + 1;
                    }
                }
                sb.append(temp.get(temp.size() - 1));
                if (sb.length() < maxWidth) {
                    repeatAdd(sb, ' ', maxWidth - sb.length());
                }
                result.add(sb.toString());
                temp.clear();
                temp.add(word);
                sb = new StringBuilder();
                length = word.length();
            } else {
                length += word.length() + 1;
                temp.add(word);
            }
        }
        // 处理文末
        for (String s : temp) {
            sb.append(s);
            if (sb.length() < maxWidth) {
                sb.append(' ');
            }
        }
        repeatAdd(sb, ' ', maxWidth - sb.length());
        result.add(sb.toString());
        return result;
    }

    private void repeatAdd(StringBuilder sb, char value, int len) {
        for (int i = 0; i < len; i++) {
            sb.append(value);
        }
    }

    public int mySqrt(int x) {
        int left = 0, right = 46341;
        while (right > left + 1) {
            int half = (left + right)/2;
            if (half * half == x) {
                return half;
            }else if (half * half < x) {
                left = half;
            } else {
                right = half;
            }
        }
        return left;
    }

    public int climbStairs(int n) {
        return fib(n+1);
    }

    public String simplifyPathOld(String path) {
        StringBuilder sb = new StringBuilder();
        Stack<String> words = new Stack<>();
        int index = 1;
        while (index < path.length()) {
            if (path.charAt(index) == '/') {
                index++;
                if (sb.length() > 0) {
                    words.push(sb.toString());
                    sb = new StringBuilder();
                }
                continue;
            }
            if (path.charAt(index) == '.' && path.charAt(index - 1) == '/') {
                index++;
                if (index < path.length() && path.charAt(index) == '.') {
                    index++;
                    if (index == path.length() || path.charAt(index) == '/') {
                        // /..为前一级
                        if (!words.isEmpty()) {
                            words.pop();
                        }
                    } else {
                        // ..视作word的一部分
                        sb.append("..");
                    }
                } else if (index < path.length() && path.charAt(index) != '/'){
                    // .视作word的一部分
                    sb.append('.');
                }
            } else {
                sb.append(path.charAt(index));
                index++;
            }
        }
        if (sb.length() > 0) {
            words.push(sb.toString());
            sb = new StringBuilder();
        }
        if (words.isEmpty()) {
            return "/";
        } else {
            for (String word : words) {
                sb.append('/');
                sb.append(word);
            }
            return sb.toString();
        }
    }

    public String simplifyPath(String path) {
        Stack<String> words = new Stack<>();
        String[] p = path.split("/");
        for (String s : p) {
            if (!words.isEmpty() && "..".equals(s)) {
                words.pop();
            }
            if (!".".equals(s) && !"..".equals(s) && !"".equals(s)) {
                words.push(s);
            }
        }
        List<String> list = new ArrayList<>(words);
        return "/" + String.join("/", list);
    }

    // edit distance
    public int minDistanceEdit(String word1, String word2) {
        // 递归: 三种策略，insert是比较word1[i]和word2[j+1]，remove是比较word1[i+1]和word2[j]，replace是比较word1[i+1]和word2[j+1]，每次取三种策略的最小值+1
        // dp: 若word1[i],word2[j]相同，则dp[i][j] = dp[i-1][j-1]；反之则在[i-1][j-1], [i-1][j], [i][j-1]三者中取最小并+1。
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m+1][n+1];
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= n; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j])) + 1;
                }
            }
        }
        return dp[m][n];
    }

    // delete distance
    public int minDistanceDelete(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m+1][n+1];
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= n; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(dp[i][j-1], dp[i-1][j]) + 1;
                }
            }
        }
        return dp[m][n];
    }

    // 优化空间 把dp压成两行数组
    public int minDistanceDelete2(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[] dp1 = new int[n+1];
        int[] dp2 = new int[n+1];
        for (int i = 0; i <= m; i++) {
            Arrays.fill(dp2, 0);
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp2[j] = i+j;
                } else if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp2[j] = dp1[j-1];
                } else {
                    dp2[j] = Math.min(dp2[j-1], dp1[j]) + 1;
                }
            }
            System.arraycopy(dp2, 0, dp1, 0, n+1);
        }
        return dp1[n];
    }

    private int recursiveMinDistance(String word1, String word2, int index1, int index2, int[][] memory) {
        if (index1 == word1.length()) return word1.length() - index2;
        if (index2 == word2.length()) return word2.length() - index1;
        if (memory[index1][index2] > 0) return memory[index1][index2];
        int result = 0;
        if (word1.charAt(index1) == word2.charAt(index2)) {
            return recursiveMinDistance(word1, word2, index1+1, index2+1, memory);
        } else {
            int insert = recursiveMinDistance(word1, word2, index1, index2+1, memory);
            int remove = recursiveMinDistance(word1, word2, index1+1, index2, memory);
            int replace = recursiveMinDistance(word1, word2, index1+1, index2+1, memory);
            result = Math.min(replace, Math.min(insert, remove)) + 1;
        }
        return memory[index1][index2] = result;
    }

    public void setZeroes(int[][] matrix) {
        // 1、先扫描第一行和第一列，若有0则flag为true
        // 2、扫描一遍剩下的数组，用第一行和第一列的对应位置记录该列、该行有无0
        // 3、根据第一行和第一列的记录，将剩下的数组有0的行和列setZero
        // 4、根据flag，将第一行和第一列setZero
        boolean rowZero = false, colZero = false;
        for (int[] ints : matrix) {
            if (ints[0] == 0) {
                colZero = true;
                break;
            }
        }
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                rowZero = true;
                break;
            }
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < matrix[0].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for (int i = 1; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                for (int j = 1; j < matrix.length; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
        if (rowZero) {
            Arrays.fill(matrix[0], 0);
        }
        if (colZero) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    public void sortColors(int[] nums) {
        int left = 0, right = nums.length-1;
        for (int i = 0; i <= right; i++) {
            if (nums[i] == 0) {
                swap(nums, left++, i);
            } else if (nums[i] == 2) {
                swap(nums, i--, right--);
            }
        }
    }

    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        Set<Integer> result = new HashSet<>();
        for (int i = 1; i < bound; i*=x) {
            for (int j = 1; i + j <= bound; j*=y) {
                result.add(i+j);
                if (y == 1) break;
            }
            if (x == 1) break;
        }
        return new ArrayList<>(result);
    }

    public String minWindow(String s, String t) {
        // 滑动窗口
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

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        recursiveCombine(n, k, result, new ArrayList<>(), 0);
        return result;
    }

    private void recursiveCombine(int n, int k, List<List<Integer>> result, List<Integer> one, int start) {
        if (k == one.size()) {
            result.add(new ArrayList<>(one));
            return;
        }
        for (int i = start; i < n; i++) {
            if (n - i + one.size() < k) {
                break;
            }
            one.add(i+1);
            recursiveCombine(n, k, result, one, i+1);
            one.remove(one.size() - 1);
        }
    }

    public int scheduleCourse(int[][] courses) {
        Queue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        Arrays.sort(courses, Comparator.comparingInt(o -> o[1]));
        int time = 0;
        for (int[] course : courses) {
            int dur = course[0], end = course[1];
            if (time + dur <= end) {
                time += dur;
                queue.offer(dur);
            } else if (!queue.isEmpty() && queue.peek() > dur) {
                time += dur - queue.poll();
                queue.offer(dur);
            }
        }
        return queue.size();
    }

    public int[] runningSum(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i-1];
        }
        return nums;
    }

    public boolean checkPossibility(int[] nums) {
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i-1]) {
                if (count == 0) return false;
                if (i == 1 || nums[i] >= nums[i-2]) nums[i-1] = nums[i];
                else nums[i] = nums[i-1];
                count--;
            }
        }
        return true;
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        recursiveSubsets(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private void recursiveSubsets(int[] nums, int start, List<Integer> one, List<List<Integer>> result) {
        result.add(new ArrayList<>(one));
        for (int i = start; i < nums.length; i++) {
            one.add(nums[i]);
            recursiveSubsets(nums, i + 1, one, result);
            one.remove(one.size()-1);
        }
    }

    public boolean exist(char[][] board, String word) {
        HashSet<Character> seen = new HashSet<>();
        int m = board.length, n = board[0].length;
        for (char[] chars : board) {
            for (int j = 0; j < n; j++) {
                seen.add(chars[j]);
            }
        }
        for (int i = 0; i < word.length(); i++) {
            if (!seen.contains(word.charAt(i))) return false;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (recursiveExist(board, word, 0, i, j)) return true;
            }
        }
        return false;
    }

    private boolean recursiveExist(char[][] board, String word, int index, int x, int y) {
        if (index == word.length()) return true;
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] != word.charAt(index)) return false;
        // 类似visited
        board[x][y] = '#';
        boolean res = false;
        for (int[] direct : directs) {
            res = res || recursiveExist(board, word, index+1, x+direct[0], y+direct[1]);
        }
        board[x][y] = word.charAt(index);
        return res;
    }

    public int removeDuplicates(int[] nums) {
        // i是有效数组末尾
        int i = 0;
        for (int num : nums) {
            if (i < 2 || num > nums[i-2]) {
                // 只要控制不让重复超过2，也即i和i-1无视，i-2和num不能重复
                nums[i++] = num;
            }
        }
        return i;
    }

    public int superpalindromesInRange(String left, String right) {
        result = 0;
        long leftValue = Long.parseLong(left), rightValue = Long.parseLong(right);
        for (char i = '0'; i <= '9'; i++) {
            // 奇数位
            recursiveSuperPalindromes(String.valueOf(i), leftValue, rightValue);
            // 偶数位
            recursiveSuperPalindromes(String.valueOf(i)+i, leftValue, rightValue);
        }
        return result;
    }

    private void recursiveSuperPalindromes(String current, long left, long right) {
        if (current.length() > 9) return;
        if (!current.isEmpty() && current.charAt(0) != '0') {
            long value = Long.parseLong(current);
            value *= value;
            if (value > right) return;
            if (value >= left && isPalindromes(value)) result++;
        }
        for (char i = '0'; i <= '9'; i++) {
            recursiveSuperPalindromes(i+current+i, left, right);
        }
    }

    private boolean isPalindromes(String str) {
        int left = 0, right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left++) != str.charAt(right--)) return false;
        }
        return true;
    }

    private boolean isPalindromes(long num) {
        return num == reverseNum(num);
    }

    private long reverseNum(long num) {
        long ans = 0;
        while (num > 0) {
            ans = ans * 10 + num % 10;
            num /= 10;
        }
        return ans;
    }

    public boolean isPossibleOld(int[] target) {
        // 反推法：通过最大的值减去剩余值的和来得到之前的值
        PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        long sum = 0;
        for (int i : target) {
            heap.add(i);
            sum += i;
        }
        while (true) {
            int largest = heap.poll();
            sum -= largest;
            if (largest == 1 || sum == 1) return true;
            // largest < sum 意味着无法通过
            if (largest < sum || sum == 0 || largest % sum == 0) return false;
            // 如largest=100, sum = 7, 显然100要不停减7直到largest-7比sum小
            largest %= sum;
            sum += largest;
            heap.add(largest);
        }
    }

    public boolean isPossible(int[] target) {
        // 不用最大堆
        int length = target.length;
        if (length == 1) return target[0] == 1;
        long sum = 0L;
        long max = 0L;
        int maxIndex = -1;
        for (int i = 0; i < length; i++) {
            long current = (long) target[i];
            sum += current;
            if (current > max) {
                max = current;
                maxIndex = i;
            }
        }
        sum -= max;
        if (max == 1 || sum == 1) return true;
        if (max < sum || sum == 0 || max % sum == 0) return false;
        target[maxIndex] = (int) (max % sum);
        return isPossible(target);
    }

    public int countPrimes(int n) {
        // Sieve of Eratosthenes
        if (n <= 2) return 0;
        int result = 1;
        boolean[] notPrime = new boolean[n];
        // 可作为factor的上届，之后的数据无需再更新notPrime，只需要输出
        int upper = (int) Math.sqrt(n);
        // 跳过偶数减少一半
        for (int i = 3; i < n; i+=2) {
            if (notPrime[i]) continue;
            result++;
            if (i > upper) continue;
            // i^2+2*i同样跳过偶数
            for (int j = i * i; j < n; j += 2 * i) {
                notPrime[j] = true;
            }
        }
        return result;
    }

    public int maxScore(int[] cardPoints, int k) {
        // 反推：数组中需要排除n-k个和最小的数
        int totalSum = 0, windowSum = 0, windowSumMin = 0, n = cardPoints.length;
        for (int i = 0; i < n; i++) {
            totalSum += cardPoints[i];
            if (i < n-k) {
                windowSum += cardPoints[i];
                windowSumMin = windowSum;
            } else {
                windowSum = windowSum + cardPoints[i] - cardPoints[i+k-n];
                windowSumMin = Math.min(windowSumMin, windowSum);
            }
        }
        return totalSum - windowSumMin;
    }

    public List<String> ambiguousCoordinates(String s) {
        List<String> result = new ArrayList<>();
        int n = s.length();
        for (int i = 1; i < n - 2; i++) {
            List<String> left = findAllAmbiguousCoordinates(s.substring(1, i+1));
            List<String> right = findAllAmbiguousCoordinates(s.substring(i+1, n-1));
            for (String s1 : left) {
                for (String s2 : right) {
                    result.add('(' + s1 + ", " + s2+')');
                }
            }
        }
        return result;
    }

    private List<String> findAllAmbiguousCoordinates(String s) {
        List<String> result = new ArrayList<>();
        int n = s.length();
        // ""或者"0xxx0"，非法
        if (n == 0 || (n > 1 && s.charAt(0) == '0' && s.charAt(n-1) == '0')) return result;
        if (n > 1 && s.charAt(0) == '0') {
            // "0xxx"只能0.xxx
            result.add("0." + s.substring(1));
            return result;
        }
        if (s.charAt(n-1) == '0') {
            // xxx0只能做整数
            result.add(s);
            return result;
        }
        result.add(s);
        for (int i = 1; i < n; i++) {
            result.add(s.substring(0, i) + '.' + s.substring(i));
        }
        return result;
    }

    public int MS1(int[] A) {
        int startWithHead = 0, startWithTail = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == i % 2) {
                // even position should be tail if start with tail;
                // odd position should be head if start with tail;
                startWithTail++;
            } else {
                // even position should be head if start with head;
                // odd position should be tail if start with head;
                startWithHead++;
            }
        }
        return Math.min(startWithHead, startWithTail);
    }

    public int MS2(String S) {
        // each one represent two step, one for >> and another for -1
        // each zero represent one step, >>
        // last step: 1 only need -1, do not need >>
        int zeroNum = 0, oneNum = 0;
        boolean startWithOne = false;
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == '1' && !startWithOne) {
                startWithOne = true;
            }
            if (S.charAt(i) == '0' && startWithOne) {
                zeroNum++;
            }
            if (S.charAt(i) == '1') {
                oneNum++;
            }
        }
        return 2*oneNum+zeroNum-1;
    }

    public int longestStrChainOld(String[] words) {
        Arrays.sort(words, Comparator.comparingInt(String::length));
        HashMap<String, Integer> dp = new HashMap<>();
        int result = 0;
        for (String word : words) {
            int oneBest = 0;
            for (int i = 0; i < word.length(); i++) {
                // 剔除i位置的char
                String previous = word.substring(0, i) + word.substring(i+1);
                oneBest = Math.max(oneBest, dp.getOrDefault(previous, 0)+1);
            }
            dp.put(word, oneBest);
            result = Math.max(result, oneBest);
        }
        return result;
    }

    public boolean isPredecessor(String a, String b) {
        int bi = 0;
        for (int ai = 0; ai < a.length();) {
            int t = (int)a.charAt(ai) - b.charAt(bi);
            ++bi;
            // 若a[ai]和b[bi]相等, t = 0, ((t ^ (t - 1)) >> 31) = -1, ai++
            // 不相等, ((t ^ (t - 1)) >> 31) = 0, ai不变
            ai -= (t ^ (t - 1)) >> 31;
            if (bi - ai >= 2)
                return false;
        }
        return true;
    }

    public int check(String cur, LinkedList<String>[] map, int index, int depth) {
        if (index >= map.length)
            return depth;
        Iterator<String> iter = map[index].iterator();
        int res = depth;
        while (iter.hasNext()) {
            String next = iter.next();
            if (isPredecessor(cur, next)) {
                iter.remove();
                res = Math.max(res, check(next, map, index + 1, depth + 1));
            }
        }
        return res;
    }
    public int longestStrChain(String[] words) {
        int kLen = 17;
        LinkedList<String>[] map = new LinkedList[kLen];
        for (int i = 0; i < kLen; ++i) {
            map[i] = new LinkedList<>();
        }
        for (String w: words) {
            map[w.length()].add(w);
        }
        int res = 1;
        for (int i = 1; (i < kLen - 1) && i <= (kLen - res); ++i) {
            // 从长度1开始找，然后1在长度2中找到是isPredecessor,找到的2接着递归直到该条线路所能找到的最远
            for (String prev: map[i]) {
                if (i > (kLen - res)) {
                    // 达成这个条件需要有一条长度大于n/2的chain，则剩余的word小于n/2，不足与产生更大的res
                    return res;
                }
                res = Math.max(res, check(prev, map, i + 1, 1));
            }
        }
        return res;
    }

    public List<List<String>> findDuplicate(String[] paths) {
        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String path : paths) {
            String[] pathArray = path.split(" ");
            for (int i = 1; i < pathArray.length; i++) {
                int index = pathArray[i].lastIndexOf("(");
                String content = pathArray[i].substring(index + 1, pathArray[i].length() - 1);
                String fileName = pathArray[i].substring(0, index);
                if (map.containsKey(content)) {
                    map.get(content).add(pathArray[0]+'/'+fileName);
                } else {
                    List<String> list = new ArrayList<>();
                    list.add(pathArray[0]+'/'+fileName);
                    map.put(content, list);
                }
            }
        }
        map.forEach((key, value) -> {
            if (value.size() > 1) {
                result.add(value);
            }
        });
        return result;
    }

    // [LeetCode] 81. Search in Rotated Sorted Array II
    public boolean searchII(int[] nums, int target) {
        // mid > right证明左半边有序，mid < right 证明右半边有序
        // II特例, mid == right则把right--继续判断
        int left = 0, right = nums.length-1;
        while (left <= right) {
            int mid = (left + right ) / 2;
            if (nums[mid] == target) return true;
            if (nums[mid] == nums[right]) {
                right--;
            } else if (nums[mid] < nums[right]) {
                if (nums[mid] < target && nums[right] >= target) {
                    // 右半边有序，且在mid到right范围内
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                if (nums[mid] > target && nums[left] <= target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return false;
    }

    public int largestRectangleAreaOld(int[] heights) {
        int result = 0;
        // 栈按从大到小放板子
        Stack<Integer> maxStack = new Stack<>();
        for (int i = 0; i < heights.length; i++) {
            while (!maxStack.isEmpty() && heights[maxStack.peek()] >= heights[i]) {
                // 遇到比栈顶小的板子，开始出栈所有比i大的板子，挨个更新result
                // 思想：如3，5，2三个板子，当遍历到2时，木桶出现最短板，3和5要不是按照2参与到后面的计算，要不就是5*1，3*2的方式使用自己的长度。
                int index = maxStack.pop();
                // 若本轮栈取空了，也即是i的板子是新的最小板子，
                result = Math.max(result, heights[index] * (maxStack.isEmpty() ? i : i - maxStack.peek() - 1));
            }
            maxStack.push(i);
        }
        while (!maxStack.isEmpty()) {
            // 再清一下栈里剩余的板子
            int index = maxStack.pop();
            result = Math.max(result, heights[index] * (maxStack.isEmpty() ? heights.length : heights.length - maxStack.peek() - 1));
        }
        return result;
    }

    public int largestRectangleArea(int[] heights) {
        if(heights == null || heights.length ==0){
            return 0;
        }
        int len=heights.length;
        int[] left = new int[len];
        int[]  right = new int[len];
        left[0]=-1;
        right[len-1]=len;
        for(int i=1;i<len;i++){
            int p=i-1;
            while(p>-1 && heights[p]>=heights[i]){
                p = left[p];
            }
            left[i]=p;
        }

        for(int i=len-2;i>=0;i--){
            int p = i+1;
            while(p<len && heights[p]>=heights[i]){
                p=right[p];
            }
            right[i]=p;
        }

        int max=0;
        for(int i=0;i<len;i++){
            max = Math.max(max,(right[i]-left[i]-1)*heights[i]);
        }
        return max;
    }

    public String isPossible(int a, int b, int c, int d) {
        // Write your code here
        if (a == c && b == d) {
            return "YES";
        } else if (c > d) {
            return isPossible(a, b, c-d, d);
        } else if (d > c){
            return isPossible(a, b, c, d-c);
        } else {
            return "NO";
        }
    }

    public String CalculateBinaryNumbers(List<String> array) {
        String result = array.get(0);
        for (int i = 1; i < array.size(); i++) {
            String current = array.get(i);
            if (i % 2 == 0) {
                // even: directly add
                result = addBinary(result, array.get(i));
            } else {
                // odd : reverse and add
                result = addBinary(result, new StringBuilder(current).reverse().toString());
            }
        }
        return String.valueOf(result);
    }

    public String getFavoriteCities(List<String> userCities) {
        Map<String, Integer> map = new HashMap<>();
        for (String userCity : userCities) {
            for (String city : userCity.split("->")) {
                if (map.containsKey(city)) {
                    map.put(city, map.get(city)+1);
                }else {
                    map.put(city, 1);
                }
            }
        }
        List<String> list = new LinkedList<>();
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > maxCount) {
                list = new ArrayList<>();
                list.add(entry.getKey());
                maxCount = entry.getValue();
            } else if (entry.getValue() == maxCount) {
                list.add(entry.getKey());
            }
        }
        Collections.reverse(list);
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s);
            sb.append(", ");
        }
        sb.append(maxCount);
        return sb.toString();
    }

    public int minMoves2(int[] nums) {
        int result = 0, n = nums.length, mid = (n+1) / 2;
        int target = quickSelect(mid, 0, n-1, nums);
        for (int num : nums) {
            result += Math.abs(target - num);
        }
        return result;
    }

    private int quickSelect(int k, int left, int right, int[] nums) {
        if (left == right) return nums[left];
        int pivot = nums[(left + right) >>> 1];
        int l = left, r = right;
        while (l <= r) {
            while (l <= r && nums[l] < pivot) l++;
            while (l <= r && nums[r] > pivot) r--;
            if (l < r) {
                int tmp = nums[l];
                nums[l] = nums[r];
                nums[r] = tmp;
            } else break;
            l++;
            r--;
        }
        if (nums[l] > pivot) l--;
        if (l >= k) {
            if (l == k && left + 1 == l) {
                return Math.max(nums[left], nums[l]);
            }
            return quickSelect(k, left, l, nums);
        }
        return quickSelect(k, l + 1, right, nums);
    }

    public int maximalRectangleOld(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length, n = matrix[0].length, result = 0;
        // 记录(i, j)前该行连续1的个数
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    if (j > 0){
                        dp[i][j] = dp[i][j-1] + 1;
                    } else {
                        dp[i][j] = 1;
                    }
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] == 0) {
                    continue;
                }
                int temp = dp[i][j];
                result = Math.max(result, temp);
                for (int k = i-1; k >= 0 && dp[k][j] != 0; k--) {
                    // 向上遍历行
                    temp = Math.min(temp, dp[k][j]);
                    result = Math.max(result, temp * (i - k + 1));
                }
            }
        }
        return result;
    }

    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length, n = matrix[0].length, result = 0;
        int[] height = new int[n];
        for (int i = 0; i < n; i++) {
            height[i] = matrix[0][i] - '0';
        }
        result = largestRectangleArea(height);
        for (int i = 1; i < m; i++) {
            resetHeight(matrix, height, i);
            result = Math.max(result, largestRectangleArea(height));
        }
        return result;
    }

    private void resetHeight(char[][] matrix, int[] height, int index) {
        // 计算index行的height
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[index][i] == '1') {
                height[i]++;
            }else {
                height[i] = 0;
            }
        }
    }

    public boolean isScramble(String s1, String s2) {
        if (s1.equals(s2)) return true;
        if (s1.length() != s2.length()) return false;
        return recursiveIsScramble(s1, s2, new HashMap<>());
    }

    private boolean recursiveIsScramble(String s1, String s2, Map<String, Boolean> dp) {
        if (s1.equals(s2)) return true;
        if (s1.length() != s2.length()) return false;
        int n = s1.length();
        String key = s1+s2;
        if (dp.containsKey(key)) {
            return dp.get(key);
        }

        int[] charS1 = new int[26];
        int[] charS2 = new int[26];
        int[] charS2Reverse = new int[26];
        boolean result = false;
        for (int i = 1; i < n; i++) {
            charS1[s1.charAt(i-1) - 'a']++;
            charS2[s2.charAt(i-1) - 'a']++;
            charS2Reverse[s2.charAt(n-i) - 'a']++;

            if (isSameLetters(charS1, charS2)) {
                result = recursiveIsScramble(s1.substring(0, i), s2.substring(0, i), dp)
                        && recursiveIsScramble(s1.substring(i), s2.substring(i), dp);
            }
            if (result) {
                break;
            }
            if (isSameLetters(charS1, charS2Reverse)) {
                result = recursiveIsScramble(s1.substring(0, i), s2.substring(n-i), dp)
                        && recursiveIsScramble(s1.substring(i), s2.substring(0, n-i), dp);
            }
            if (result) {
                break;
            }
        }
        dp.put(key, result);
        return result;
    }

    private boolean isSameLetters(int[] a, int[] b) {
        for (int i = 0; i < 26; i++) {
            if (a[i] - b[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>();
        int num = (int) Math.pow(2, n);
        for (int i = 0; i < num; i++) {
            result.add((i >> 1) ^ i);
        }
        return result;
    }

    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> result = new ArrayList<>();
        for (String word : words) {
            int[] w = new int[26], p = new int[26];
            int i = 0, n = pattern.length();
            for (; i < n; i++) {
                int wi = word.charAt(i) - 'a';
                int pi = pattern.charAt(i) - 'a';
                if (w[wi] != p[pi]) {
                    break;
                }
                w[wi] = p[pi] = i+1;
            }
            if (i == n) {
                result.add(word);
            }
        }
        return result;
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        recursiveSubsetsWithDup(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private void recursiveSubsetsWithDup(int[] nums, int start, List<Integer> one, List<List<Integer>> result) {
        result.add(new ArrayList<>(one));
        for (int i = start; i < nums.length; i++) {
            one.add(nums[i]);
            recursiveSubsetsWithDup(nums, i + 1, one, result);
            one.remove(one.size()-1);
            while (i + 1 < nums.length && nums[i+1] == nums[i]) {
                // 跳过重复的
                i++;
            }
        }
    }

    public int numDecodings(String s) {
        // 以全1来推导数学性质，1->1, 11->2, 111->3, 1111->5, 11111->8，类似斐波那契
        // 进一步推导，当s[i-1]和s[i]两位在11-26中时，s[0->i-2]s[i-1]s[i](dp[i-2]个可能), s[0->i-2]s[i-1],s[i](dp[i-1]个可能)两种情况
        // 特殊的，当s[i]=='0'时，由于s[i]不能单独拆出来，dp[i]=0
        if (s.length() == 0 || s.charAt(0) == '0') return 0;
        // first = dp[i-2], second = dp[i-1]
        int first = 1, second = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                second = 0;
            }
            if (s.charAt(i-1) == '1' || (s.charAt(i-1) == '2' && s.charAt(i) <= '6')){
                second = first + second;
                first = second - first;
            } else {
                first = second;
            }
        }
        return second;
    }

    public String shortestSuperstring(String[] words) {
        int N = words.length, powerSize = 1<<N;
        int[][] overlaps = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) continue;
                int lengthI = words[i].length();
                for (int k = Math.min(lengthI, words[j].length()); k > 0 ; k--) {
                    if (words[i].substring(lengthI-k).equals(words[j].substring(0, k))) {
                        overlaps[i][j] = k;
                        break;
                    }
                }
            }
        }
        // dp[mask][i] = most overlap with mask, ending with ith element
        int[][] dp = new int[powerSize][N];
        int[][] parent = new int[powerSize][N];
        for (int mask = 0; mask < (powerSize); ++mask) {
            Arrays.fill(parent[mask], -1);

            for (int bit = 0; bit < N; ++bit) {
                if (((mask >> bit) & 1) > 0) {
                    // Let's try to find dp[mask][bit].  Previously, we had
                    // a collection of items represented by pmask.
                    int pmask = mask ^ (1 << bit);
                    if (pmask == 0) continue;
                    for (int i = 0; i < N; ++i) {
                        if (((pmask >> i) & 1) > 0) {
                            // For each bit i in pmask, calculate the value
                            // if we ended with word i, then added word 'bit'.
                            int val = dp[pmask][i] + overlaps[i][bit];
                            if (val > dp[mask][bit]) {
                                dp[mask][bit] = val;
                                parent[mask][bit] = i;
                            }
                        }
                    }
                }
            }
        }

        // # Answer will have length sum(len(A[i]) for i) - max(dp[-1])
        // Reconstruct answer, first as a sequence 'perm' representing
        // the indices of each word from left to right.

        int[] perm = new int[N];
        boolean[] seen = new boolean[N];
        int t = 0;
        int mask = powerSize - 1;

        // p: the last element of perm (last word written left to right)
        int p = 0;
        for (int j = 0; j < N; ++j) {
            if (dp[powerSize - 1][j] > dp[powerSize - 1][p]) {
                p = j;
            }
        }

        // Follow parents down backwards path that retains maximum overlap
        while (p != -1) {
            perm[t++] = p;
            seen[p] = true;
            int p2 = parent[mask][p];
            mask ^= 1 << p;
            p = p2;
        }

        // Reverse perm
        for (int i = 0; i < t/2; ++i) {
            int v = perm[i];
            perm[i] = perm[t-1-i];
            perm[t-1-i] = v;
        }

        // Fill in remaining words not yet added
        for (int i = 0; i < N; ++i) {
            if (!seen[i]) {
                perm[t++] = i;
            }
        }

        // Reconstruct final answer given perm
        StringBuilder ans = new StringBuilder(words[perm[0]]);
        for (int i = 1; i < N; ++i) {
            int overlap = overlaps[perm[i-1]][perm[i]];
            ans.append(words[perm[i]].substring(overlap));
        }

        return ans.toString();
    }

    public String toLowerCase(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if (curr - 'A' <= 26 && curr - 'A' >= 0) {
                sb.append((char)(curr + 32));
            } else {
                sb.append(curr);
            }
        }
        return sb.toString();
    }

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (final String token : tokens) {
            switch (token) {
                case "+": {
                    stack.push(stack.pop() + stack.pop());
                    break;
                }
                case "-": {
                    stack.push(-stack.pop() + stack.pop());
                    break;
                }
                case "*": {
                    stack.push(stack.pop() * stack.pop());
                    break;
                }
                case "/": {
                    final int param_1 = stack.pop();
                    final int param_2 = stack.pop();
                    stack.push(param_2 / param_1);
                    break;
                }
                default:
                    stack.push(Integer.valueOf(token));
                    break;
            }
        }
        return stack.peek();
    }

    public int minPartitions(String n) {
        int length = Math.min(n.length(), 10);
        int max = n.charAt(0) - 48;
        for (int i = 1; i < length; i++) {
            if (n.charAt(i) - 48 > max) {
                max = n.charAt(i) - 48;
            }
        }
        return max;
    }

    public int maxProduct(String[] words) {
        int n = words.length, totalLength = 0;
        int[] letters = new int[n];
        for (int i = 0; i < n; i++) {
            String word = words[i];
            int letter = 0;
            for (int j = 0; j < word.length(); j++) {
                letter |= 1 << word.charAt(j) - 'a';
            }
            letters[i] = letter;
        }
        for (int i = 0; i < n-1; i++) {
            for (int j = i+1; j < n; j++) {
                if ((letters[i] & letters[j]) == 0) {
                    totalLength = Math.max(totalLength, words[i].length() * words[j].length());
                }
            }
        }
        return totalLength;
    }

    public int maximumUniqueSubarrayOld(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int max = 0, tempMax = 0, left = 0;
        for (int num : nums) {
            if (set.contains(num)) {
                max = Math.max(max, tempMax);
                while (nums[left] != num) {
                    tempMax -= nums[left];
                    set.remove(nums[left]);
                    left++;
                }
                left++;
            } else {
                set.add(num);
                tempMax += num;
            }
        }
        return Math.max(max, tempMax);
    }

    public int maximumUniqueSubarray(int[] nums) {
        // 存前缀(1-i)的和
        int[] ck = new int[10001];
        // 前一个重复数字之前的和
        int start = 0;
        // 两个重复数字之间的和，也即结果
        int max = 0;
        // 总和，当nums到结尾时，sum-start即为最后一段subarray的和
        int sum = 0;
        for (int num : nums) {
            sum += num;
            if (ck[num] > start) {
                // ck[num]大于start时，num对应重复的数字index在start对应的index之后，可能会更新start
                max = Math.max(max, sum - num - start);
                start = ck[num];
            }
            ck[num] = sum;
        }
        max = Math.max(max, sum - start);
        return max;
    }
}
