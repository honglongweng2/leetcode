package Top.Stack;

import java.util.Stack;

public class ReversePolishNotation {
    /**
     * No.150 计算逆波兰表达式
     *
     * 思路一：用栈存操作数，遇到符号时出栈计算。
     * 思路二：构建树，父节点操作符，计算就是递归
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (final String token : tokens) {
            switch (token) {
                case "+" -> stack.push(stack.pop() + stack.pop());
                case "-" -> stack.push(-stack.pop() + stack.pop());
                case "*" -> stack.push(stack.pop() * stack.pop());
                case "/" -> {
                    int param_1 = stack.pop();
                    int param_2 = stack.pop();
                    stack.push(param_2 / param_1);
                }
                default -> stack.push(Integer.valueOf(token));
            }
        }
        return stack.peek();
    }
}
