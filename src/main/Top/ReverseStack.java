package Top;

import java.util.Stack;

/**
 * 只能用递归实现栈逆序
 * 思路：pop栈顶直到栈空，last返回并push次栈顶。
 */
public class ReverseStack {
    public int getAndRemoveLastElement(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = getAndRemoveLastElement(stack);
            stack.push(result);
            return last;
        }
    }
}
