package Top;

/**
 * No.202 数字各位平方和，最后能不能得到1
 *
 * 思路：不停平方和的过程中，判断是否成环
 * 验证成环的最简单：快慢指针
 * ps. 10以内只有1和7符合条件，可作为思路2
 */
public class HappyNumber {
    public static void main(String[] args) {
        HappyNumber main = new HappyNumber();
        System.out.println(main.isHappy(19));
    }
    public boolean isHappy(int n) {
        int slow = n, fast = n;
        do {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
        } while (slow != fast);
        return slow == 1;
    }

    private int getNext(int n) {
        int result = 0;
        while (n > 0) {
            result += Math.pow(n%10,2);
            n /= 10;
        }
        return result;
    }

}
