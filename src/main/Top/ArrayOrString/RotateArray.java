package Top.ArrayOrString;

/**
 * No.189 把数组向右移动k步
 * 思路：反转0->n-1-k，再反转n-k->n-1,最后反转整个数组
 */
public class RotateArray {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k%n;
        if (k == n) return;
        reverse(nums, 0, n-1-k);
        reverse(nums, n-k, n-1);
        reverse(nums, 0, nums.length-1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    public void swap(int[] nums, int left, int right) {
        if (nums[left] == nums[right]) return;
        nums[left] = nums[left] ^ nums[right];
        nums[right] = nums[left] ^ nums[right];
        nums[left] = nums[left] ^ nums[right];
    }

    public void swap2(int[] nums, int start, int end) {
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
    }

    public static void main(String[] args) {
        RotateArray main = new RotateArray();
        int[] nums = new int[1000];
//        int[] nums = new int[] {-1, -99, 1, 7};
        long time = System.currentTimeMillis();
        main.rotate(nums, 100);
        System.out.println(System.currentTimeMillis() - time);
    }
}
