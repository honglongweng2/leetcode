package Top.ArrayOrString;

public class RomanNumber {
    /**
     * No.13 罗马数字转int
     */
    public int romanToInt(String s) {
        int result = 0;
        int temp = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            int value = romanToNum(s.charAt(i));
            if (temp <= value) {
                result += value;
            }else {
                result -= value;
            }
            temp = value;
        }
        return result;
    }
    private int romanToNum(char c) {
        return switch (c) {
            case 'I' -> 1;
            case 'V' -> 5;
            case 'X' -> 10;
            case 'L' -> 50;
            case 'C' -> 100;
            case 'D' -> 500;
            case 'M' -> 1000;
            default -> 0;
        };
    }

    /**
     * No.12 数字转罗马数字
     */
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        int thousand = num / 1000;
        int rest = num % 1000;
        sb.append("M".repeat(Math.max(0, thousand)));
        if (rest >= 900) {
            rest -= 900;
            sb.append("CM");
        } else if (rest >= 500) {
            rest -= 500;
            sb.append('D');
        } else if (rest >= 400) {
            rest -= 400;
            sb.append("CD");
        }
        int hundred = rest / 100;
        rest = rest % 100;
        sb.append("C".repeat(Math.max(0, hundred)));
        if (rest >= 90) {
            rest -= 90;
            sb.append("XC");
        } else if (rest >= 50) {
            rest -= 50;
            sb.append('L');
        } else if (rest >= 40) {
            rest -= 40;
            sb.append("XL");
        }
        int ten = rest / 10;
        rest = rest % 10;
        sb.append("X".repeat(Math.max(0, ten)));
        if (rest >= 9) {
            rest -= 9;
            sb.append("IX");
        } else if (rest >= 5) {
            rest -= 5;
            sb.append('V');
        } else if (rest >= 4) {
            rest -= 4;
            sb.append("IV");
        }
        sb.append("I".repeat(Math.max(0, rest)));
        return sb.toString();
    }
}
