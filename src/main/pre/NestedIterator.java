package pre;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class NestedIterator implements Iterator<Integer> {

    private Stack<NestedInteger> stack;

    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            stack.push(nestedList.get(i));
        }
    }

    @Override
    public Integer next() {
        NestedInteger next = stack.pop();
        return next.getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()) {
            NestedInteger next = stack.peek();
            if (next.isInteger()) return true;
            stack.pop();
            for (int i = next.getList().size() - 1; i >= 0; i--) {
                stack.push(next.getList().get(i));
            }
        }
        return false;
    }

    private List<Integer> flatten(NestedInteger item) {
        List<Integer> result = new ArrayList<>();
        if (item.isInteger()) {
            result.add(item.getInteger());
        } else {
            for (NestedInteger children : item.getList()) {
                result.addAll(flatten(children));
            }
        }
        return result;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */