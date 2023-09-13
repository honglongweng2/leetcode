package pre;

import java.util.HashSet;

public class Palindromes {
    public int superpalindromesInRange(String left, String right) {
        a = Long.parseLong(left);
        b = Long.parseLong(right);
        square = 9L;
        add();
        doit(0, "");
        return ans.size();
    }

    long a;
    long b;
    HashSet<Long> ans = new HashSet<Long>();

    void add() {
        if(a <= square  && square <= b) ans.add(square);
    }

    void doit(int i, String x) {
        if(i == 5) {
        } else {
            String rev = (new StringBuilder(x)).reverse().toString();
            String val = x + rev;
            if(is_good(val)) {
                add();
            }
            for(int j = 0; j <= 2; j++) {
                val = x + j + rev;
                if(is_good(val)) {
                    add();
                }
            }
            for(int j = 0; j <= 2; j++) {
                doit(i+1, j + x);
            }
        }
    }

    long square;

    boolean is_good(String str) {
        if(str.length() == 0) return false;
        long x = Long.parseLong(str);
        long xx = x*x;
        square = xx;
        return is_integer_palindrome(x) && is_integer_palindrome(xx);
    }

    boolean is_integer_palindrome(long x) {
        String s = String.valueOf(x);
        int n = s.length();
        for(int i = 0; i < n; i++) {
            if(s.charAt(i) != s.charAt(n-i-1)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Palindromes p = new Palindromes();
        p.superpalindromesInRange("4", "1000");
    }
}
