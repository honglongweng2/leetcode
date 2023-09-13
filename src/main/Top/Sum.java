package Top;

import java.util.*;

public class Sum {
    /**
     * No.1 两数和target
     *
     * 思路：遍历，若存在target-num[i]就是答案
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> temp = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (temp.containsKey(target - nums[i])) {
                return new int[]{temp.get(target - nums[i]), i};
            }
            temp.put(nums[i], i);
        }
        return null;
    }

    /**
     * No.167 有序数组的两数和
     *
     * 双指针逼近
     */
    public int[] twoSumInOrder(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            if (numbers[left] + numbers[right] == target) {
                return new int[] {left, right};
            } else if (numbers[left] + numbers[right] > target) {
                //remove the repeat numbers.
                while (left < right && numbers[right--] == numbers[right]) {}
            } else {
                while (left < right && numbers[left++] == numbers[left]) {}
            }
        }
        return null;
    }

    /**
     * No.15 三数和为0的组合
     *
     * 思路：twoSum的升级版，每次求两数和是不是-nums[i]。数组有序后有利于遍历，可以left,right双指针逼近
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) return result;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i ++) {
            if (nums[i] > 0) break; //sum of three positive number will never be 0;
            if (i > 0 && nums[i] == nums[i-1]) continue; //do not repeat proccess;
            int sum = -nums[i];
            int left = i+1, right = nums.length -1;
            while (left < right) {
                if (nums[left] + nums[right] == sum) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[left]);
                    temp.add(nums[right]);
                    result.add(temp);
                    //then remove the repeat numbers.
                    while (left < right && nums[left++] == nums[left]) {}
                    while (left < right && nums[right--] == nums[right]) {}
                } else if (nums[left] + nums[right] < sum) {
                    while (left < right && nums[left++] == nums[left]) {}
                } else {
                    while (left < right && nums[right--] == nums[right]) {}
                }
            }
        }
        return result;
    }


}
