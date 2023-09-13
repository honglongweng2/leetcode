package Top.TwoPointer;

public class Palindrome {

    public static void main(String[] args) {
        Palindrome main = new Palindrome();
//        System.out.println(main.isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(main.isPalindrome(121));
    }
    /**
     * No.125
     *
     * 思路：双指针
     */
    public boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            // 过滤无关符号
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) left++;
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) right--;
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    /**
     * No.9
     *
     * 思路：把一半的数转移到sum
     * 奇数位时 x == sum/10，因为多了最中间的数
     */
    public boolean isPalindrome(int x) {
        if (x < 0 || (x > 0 && x%10 == 0)) return false;
        int sum = 0;
        while (x > sum) {
            sum = sum*10 + x%10;
            x = x/10;
        }
        return (x == sum) || (x == sum/10);
    }
}
