package Aug11th;

public class Interview {
    /**
     * 单例模式
     */

    /**
     * 双重校验锁模式，线程安全需要给instance加volatile关键字
     */
//    private static volatile Interview instance;
//    public static Interview getInstance() {
//        if (instance == null) {
//            // 对Interview类加锁
//            synchronized (Interview.class) {
//                if(instance == null) {
//                    instance = new Interview();
//                }
//            }
//        }
//        return instance;
//    }
    /**
     * 懒汉法，线程不安全
     */
//    private static Interview instance;
//    public static Interview getInstance() {
//        if (instance == null) {
//            return new Interview();
//        }
//        return instance;
//    }

    /**
     * 静态内部类，推荐
     */
    public Interview() {
    }

    private static class InstanceHolder {
        private final static Interview instance = new Interview();
    }

    public static Interview getInstance() {
        return InstanceHolder.instance;
    }

    /**
     * 二分搜索
     */
    public int search(int[] nums, int target) {
        return recursiveSearch(nums, 0, nums.length - 1, target);
    }

    public int recursiveSearch(int[] nums, int left, int right, int target) {
        if (left > right) return -1;
        int mid = (right + left) / 2;
        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] > target) {
            return recursiveSearch(nums, left, mid - 1, target);
        } else {
            return recursiveSearch(nums, mid + 1, right, target);
        }
    }
}
