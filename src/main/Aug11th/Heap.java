package Aug11th;

public class Heap {
    private static int[] nums = new int[1000];
    private int end = 0;
    public int pop() {
        int result = nums[0];
        swap(0, end);
        end--;
        reHeap();
        return result;
    }

    public void add(int num) {
        nums[end] = num;
        end++;
        reHeap();
    }

    public void swap(int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    public void reHeap() {

    }
}
