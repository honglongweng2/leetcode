package Top.Stack;

public class ValidParentheses {
    /**
     * No.20 括号合法
     *
     * 思路：模拟栈，左括号亚栈，右括号出栈
     */
    public boolean isValid(String s) {
        // 栈顶index
        int index = -1;
        char[] stack = new char[s.length()];
        for (char c : s.toCharArray()) {
            if ((c == '(') || (c == '[') || (c == '{')) {
                stack[++index] = c;
            } else if (index >= 0 && ((stack[index] == '(' && c == ')')
                    || (stack[index] == '[' && c == ']')
                    || (stack[index] == '{' && c == '}'))) {
                index--;
            } else {
                return false;
            }
        }
        return index == -1;
    }

    public static void main(String[] args) {
        ValidParentheses main = new ValidParentheses();
        System.out.println(main.isValid("()"));
    }
}
