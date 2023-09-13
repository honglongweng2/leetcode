package Top.ArrayOrString;

public class RemoveDuplicates {
    public static void main(String[] args) {
        RemoveDuplicates main = new RemoveDuplicates();
        System.out.println(main.removeDuplicates(new int[] {1, 2, 2, 3, 4}));
    }
    /**
     * No.26 以排序的数组去重，返回数组末尾
     * 思路：双指针
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length == 1) return 1;
        int lastOfUniqueNum = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i-1]) {
                nums[lastOfUniqueNum] = nums[i];
                lastOfUniqueNum++;
            }
        }
        return lastOfUniqueNum;
    }
    /**
     * No.80 最多重复两次
     */
    public int removeDuplicates2(int[] nums) {
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
}
