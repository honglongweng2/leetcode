package Top;

public class MyMath {
    /**
     * No.69 sqrt
     * right是根据x最大值取的
     */
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

    /**
     * No.50 Pow(x, n)
     *
     * x^n = x*x^2*x^4*x^8...(1+2+4+8...=n)
     */
    public double myPow(double x, int n) {
        if (n < 0) return 1 / myPow(x, -n-1) / x;
        double result = 1;
        while (n > 0) {
            // 当前位为0则无需
            if ((n & 1) == 1) {
                result *= x;
            }
            x *= x;
            n = n >> 1;
        }
        return result;
    }

    /**
     * No.66 digits数组表示一个数，求该数+1时的结果
     *
     * 思路：从末尾+1，若=0则发生了进位，向左一格继续。
     */
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i]%=10;
            if (digits[i] != 0) {
                return digits;
            }
        }
        int[] result = new int[digits.length+1];
        result[0] = 1;
        return result;
    }

    /**
     * No.67 二进制相加
     *
     * 思路：两个指针，一个a的index，一个b的index（保证两者同步向前直到为0），carry存上一步的进位
     */
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int m = a.length() - 1, n = b.length() - 1, carry = 0;
        while (m >= 0 || n >= 0) {
            int p = m >= 0 ? a.charAt(m--) - '0' : 0;
            int q = n >= 0 ? b.charAt(n--) - '0' : 0;
            int sum = p+q+carry;
            sb.insert(0, sum % 2);
            carry = sum / 2;
        }
        if (carry == 1) {
            sb.insert(0, 1);
        }
        return sb.toString();
    }

    /**
     * No.190 二进制翻转数(无符号数）
     *
     * 思路：(n>>i)&1获取n第i位的数，构造新数。
     */
    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result |= ((n >> i) & 1) << (31 - i);
        }
        return result;
    }

    public static void main(String[] args) {
        MyMath main = new MyMath();
        System.out.println(main.reverseBits(43261596));
    }
}
