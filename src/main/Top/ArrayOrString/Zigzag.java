package Top.ArrayOrString;

/**
 * No.6 N字形打印，("ABCDEFGHIJ", 4) -> A     G
 *                                    B   F H
 *                                    C E   I
 *                                    D     J
 *
 * 思路：相邻字符基础间隔（如A->G, B->H)为2*numRows-2(interval)
 * 相邻字符特殊间隔为interval-2*行号（如B->F=4,C->E=2)
 */
public class Zigzag {
    public static void main(String[] args) {
        Zigzag main = new Zigzag();
        System.out.println(main.convert("ABCDEFGHIJ", 4));
    }
    public String convert(String s, int numRows) {
        int length = s.length();
        if (length <= numRows || numRows == 1) return s;
        char[] zig = new char[length];
        int interval = 2 * numRows - 2;
        int count = 0;
        for (int i = 0; i < numRows; i++) {
            int step = interval - 2 * i;
            for (int j = i; j < length; j += interval) {
                zig[count++] = s.charAt(j);
                if (step > 0 && step < interval && j + step < length) {
                    zig[count++] = s.charAt(j + step);
                }
            }
        }
        return new String(zig);
    }
}
