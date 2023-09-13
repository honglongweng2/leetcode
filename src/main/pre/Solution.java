package pre;

import java.util.*;

class Solution {

    private double radius;
    private double xCenter;
    private double yCenter;
    private int i;

    public Solution(double radius, double x_center, double y_center) {
        this.radius = radius;
        this.xCenter = x_center;
        this.yCenter = y_center;
    }
    public Solution() {}

    public double[] randPoint() {
        double len = Math.sqrt(Math.random()) * radius;
        double theta = Math.PI * 2 * Math.random();
        return new double[]{xCenter + len * Math.cos(theta), yCenter + len * Math.sin(theta)};
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        TreeNode root = new TreeNode(1);
//        TreeNode left = new TreeNode(2);
//        TreeNode right = new TreeNode(5);
//        TreeNode leftLeft = new TreeNode(3);
//        TreeNode leftRight = new TreeNode(4);
//        TreeNode rightRight = new TreeNode(6);
//        left.left = leftLeft;
//        left.right = leftRight;
//        right.right = rightRight;
//        root.left = left;
//        root.right = right;
//        System.out.println(solution.minCameraCover(root));
        ListNode first = new ListNode(1);
        ListNode second = new ListNode(1);
        ListNode third = new ListNode(1);
        ListNode forth = new ListNode(4);
        ListNode fifth = new ListNode(5);
        ListNode sixth = new ListNode(6);
        first.next = second;
//        second.next = third;
//        third.next = forth;
//        forth.next = fifth;
//        fifth.next = sixth;
        System.out.println(solution.deleteDuplicates(first));
    }
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        result.add((double) root.val);
        LinkedList<TreeNode> queue = new LinkedList<>();
        if (root.left == null && root.right == null) {
            return result;
        } else {
            queue.add(root);
            int pCount = 1, cCount = 0;
            double levelSum = 0;
            while (!queue.isEmpty()) {
                TreeNode parent = queue.getFirst();
                queue.removeFirst();
                pCount--;
                if (parent.left != null) {
                    queue.add(parent.left);
                    cCount++;
                    levelSum += parent.left.val;
                }
                if (parent.right != null) {
                    queue.add(parent.right);
                    cCount++;
                    levelSum += parent.right.val;
                }
                if (pCount == 0 && cCount > 0) {
                    result.add(levelSum / cCount);
                    pCount = cCount;
                    cCount = 0;
                    levelSum = 0;
                }
            }
        }
        return result;
    }

    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        List<Integer> result = new ArrayList<>();
        if (!recurseFlip(root, voyage, result)) {
            result.clear();
            result.add(-1);
        }
        return result;
    }

    private boolean recurseFlip(TreeNode root, int[] voyage, List<Integer> result) {
        if (root == null) {
            return true;
        }
        if (root.val != voyage[i++]) {
            return false;
        }
        if (root.left != null && root.left.val != voyage[i]) {
            result.add(root.val);
            return recurseFlip(root.right, voyage, result) && recurseFlip(root.left, voyage, result);
        }
        return recurseFlip(root.left, voyage, result) && recurseFlip(root.right, voyage, result);
    }

    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) {
            TreeNode temp = new TreeNode(val);
            temp.left = root;
            return temp;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        for (int i = 1; i < depth - 1; i++) {
            // 在queue里放入depth-1深度的节点
            int count = queue.size();
            for (int j = 0; j < count; j++) {
                TreeNode temp = queue.getFirst();
                queue.removeFirst();
                if (temp.left != null) queue.addLast(temp.left);
                if (temp.right != null) queue.addLast(temp.right);
            }
        }
        if (!queue.isEmpty()) {
            for (TreeNode treeNode : queue) {
                TreeNode tempLeft = new TreeNode(val);
                tempLeft.left = treeNode.left;
                treeNode.left = tempLeft;
                TreeNode tempRight = new TreeNode(val);
                tempRight.right = treeNode.right;
                treeNode.right = tempRight;
            }
        }
        return root;
    }

    public int deepestLeavesSum(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int result = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            result = 0;
            while (size > 0) {
                TreeNode temp = queue.poll();
                size--;
                if (temp.left != null) queue.add(temp.left);
                if (temp.right != null) queue.add(temp.right);
                if (temp.left == null && temp.right == null) result += temp.val;
            }
        }
        return result;
    }

    public boolean isPalindromeOld(ListNode head) {
        if (head == null || head.next == null) return true;
        ListNode fast = head, slow = head;
        // 快慢指针找单向链表中点
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode last = slow.next;
        // 翻转链表后半
        while (last.next != null) {
            ListNode temp = last.next;
            last.next = temp.next;
            temp.next = slow.next;
            slow.next = temp;
        }
        ListNode first = head;
        while (slow.next != null) {
            slow = slow.next;
            if (first.val != slow.val) return false;
            first = first.next;
        }
        return true;
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        ListNode fast = head;
        ListNode newHead = null;
        // 通过快慢指针找到中点，同时翻转前半链表
        while (fast != null) {
            if (fast.next == null) {
                head = head.next;
                break;
            } else {
                fast = fast.next.next;
            }

            ListNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        while (newHead != null) {
            if (newHead.val != head.val) return false;
            newHead = newHead.next;
            head = head.next;
        }
        return true;
    }

    public ListNode swapNodes(ListNode head, int k) {
        ListNode temp = head;
        for (int i = 0; i < k - 1; i++) {
            temp = temp.next;
        }
        ListNode left = temp;
        ListNode right = head;
        while (temp.next != null) {
            temp = temp.next;
            right = right.next;
        }
        int val = left.val;
        left.val = right.val;
        right.val = val;
        return head;
    }

    public ListNode partition(ListNode head, int x) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode tail = dummy, pointer = head;
        while (tail.next != null && tail.next.val < x) {
            tail = tail.next;
        }
        pointer = tail;
        while (pointer.next != null) {
            if (pointer.next.val >= x) {
                pointer = pointer.next;
            } else {
                ListNode tmp = pointer.next;
                pointer.next = tmp.next;
                tmp.next = tail.next;
                tail.next = tmp;
                tail = tail.next;
            }
        }
        return dummy.next;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode a = dummy, b = head;
        for (int i = 0; i < n && b != null; i++) {
            b = b.next;
        }
        while (b != null) {
            a = a.next;
            b = b.next;
        }
        a.next = a.next.next;
        return dummy.next;
    }

    public List<Integer> preorder(Node root) {
        // dfs
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node temp = stack.pop();
            result.add(temp.val);
            for (int i = temp.children.size() - 1; i >= 0; i--) {
                stack.push(temp.children.get(i));
            }
        }
        return result;
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) return head;
        ListNode prefix = head;
        int length = 0;
        while (prefix != null) {
            prefix = prefix.next;
            length++;
        }
        prefix = head;
        for (int j = 0; j < k % length; j++) {
            prefix = prefix.next;
        }
        ListNode newHead = head;
        while (prefix.next != null) {
            newHead = newHead.next;
            prefix = prefix.next;
        }
        prefix.next = head;
        head = newHead.next;
        newHead.next = null;
        return head;
    }

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return new TreeNode(head.val);
        ListNode fast = head, slow = head, last = slow;
        while (fast != null && fast.next != null) {
            last = slow;
            fast = fast.next.next;
            slow = slow.next;
        }
        last.next = null;
        TreeNode newHead = new TreeNode(slow.val);
        if (head != slow) {
            // 只有一个，也即叶子节点
            newHead.left = sortedListToBST(head);
        }
        newHead.right = sortedListToBST(slow.next);
        return newHead;
    }

    public void flatten(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode last = new TreeNode(-1);
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
            last.left = null;
            last.right = current;
            last = current;
        }
    }

    int result;
    public int minCameraCover(TreeNode root) {
        // 核心思想，放置相机的最优解是在叶子节点的父节点处
        result = 0;
        return (recursiveCamera(root) < 1 ? 1 : 0) + result;
    }

    private int recursiveCamera(TreeNode node) {
        // 三种返回状态，leaf=0，parent with camera=1，被相机拍到的=2
        // null无需被拍
        if (node == null) return 2;
        int left = recursiveCamera(node.left), right = recursiveCamera(node.right);
        if (left == 0 || right == 0) {
            // 子节点有leaf, 当前为1且需要加一个camera
            result++;
            return 1;
        }
        return (left == 1 || right == 1) ? 2 : 0;
    }

    // Remove Duplicates from Sorted List
    public ListNode deleteDuplicates(ListNode head) {
        ListNode current = head;
        while (current != null && current.next != null) {
            if (current.val == current.next.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }

    // Remove Duplicates from Sorted ListII
    public ListNode deleteDuplicatesII(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = new ListNode(-101), last = newHead, current = head;
        last.next = head;
        while (current != null) {
            while(current.next != null && current.val == current.next.val) {
                current = current.next;
            }
            if (current == last.next) {
                last = current;
            } else {
                last.next = current.next;
            }
            current = current.next;
        }
        return newHead.next;
    }

    public ListNode deleteDuplicatesIIRecursive(ListNode head) {
        // 递归作法
        if (head == null) return head;
        if (head.next != null && head.next.val == head.val) {
            while (head.next != null && head.next.val == head.val) {
                head = head.next;
            }
            return deleteDuplicatesIIRecursive(head.next);
        } else {
            head.next = deleteDuplicatesIIRecursive(head.next);
            return head;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> oneResult = new ArrayList<>();
            while (size > 0) {
                TreeNode temp = queue.poll();
                size--;
                oneResult.add(temp.val);
                if (temp.left != null) queue.add(temp.left);
                if (temp.right != null) queue.add(temp.right);
            }
            result.add(oneResult);
        }
        return result;
    }
}