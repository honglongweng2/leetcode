package Top.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Intervals {

    /**
     * No.56 合并间隔
     */
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
        // res.toArray(T[] a)方法。若res的size>a则直接Arrays.copyOf操作新生成一个；反之则是把res copyOf到a上，超过size的部分为null
        return res.toArray(new int[0][]);
    }

    /**
     * No.57 插入新间隔
     *
     * 思路：1.与newInterval无交集的interval直接插入 2.newInterval与某个interval有交集，合并成新的newInterval（为了进入下一轮比较）
     */
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

    /**
     * No.228 统计为interval
     */
    public List<String> summaryRanges(int[] nums) {
        int n = nums.length;
        ArrayList<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int start = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] != nums[i-1] + 1) {
                sb.append(nums[start]);
                if (i > start + 1) {
                    sb.append("->").append(nums[i - 1]);
                }
                res.add(sb.toString());
                sb = new StringBuilder();
                start = i;
            }
        }
        if (start < n) {
            sb.append(nums[start]);
            if (start != n - 1) {
                sb.append("->").append(nums[n - 1]);
            }
            res.add(sb.toString());
        }
        return res;
    }

    public static void main(String[] args) {
        Intervals main = new Intervals();
        System.out.println(main.summaryRanges(new int[] {0, 2, 3}));
    }
}
