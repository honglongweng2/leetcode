package Top;

/**
 * 给定数组，求排序后相邻两数的最大差值，需要O(n)，也既牺牲空间求时间。
 *
 * 思路：n个数给定n+1个桶，桶间距为最大最小值的差值/n，将数分类到桶；依次比较后桶最小值与前桶最大值，得到结果。一共三次n循环。
 * 多一个桶是关键，人为制造至少一个空桶，使最终的差值至少大于桶间距，这样就避免了桶内比较
 */
public class MaxGap {
    public static void main(String[] args) {
        MaxGap main = new MaxGap();
        System.out.println(main.getMaxGap(new int[] {9, 0, 17, 4, 63, 72, 65, 67, 99}));
    }

    public int getMaxGap(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        int len = nums.length;
        // 最大最小值
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            max = Math.max(num, max);
            min = Math.min(num, min);
        }
        // 构建桶
        boolean[] hasNum = new boolean[len+1];
        int[] maxs = new int[len+1];
        int[] mins = new int[len+1];
        for (int num : nums) {
            int bucketId = bucket(num, max, min, len);
            maxs[bucketId] = hasNum[bucketId] ? Math.max(maxs[bucketId], num) : num;
            mins[bucketId] = hasNum[bucketId] ? Math.min(mins[bucketId], num) : num;
            hasNum[bucketId] = true;
        }
        // 比较
        int result = 0;
        // 上一个非空桶的最大值
        int lastMax = maxs[0];
        for (int i = 1; i < len+1; i++) {
            if (hasNum[i]) {
                result = Math.max(mins[i] - lastMax, result);
                lastMax = maxs[i];
            }
        }
        return result;
    }

    public int bucket (int current, int max, int min, int length) {
        return (int)(current - min) * length / (max-min);
    }
}
