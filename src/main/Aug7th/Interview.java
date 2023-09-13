package Aug7th;

import java.util.*;
import pre.ListNode;

/**
 * 1. 某城市的道路可以看做一个规则的棋盘，每个坐标可以用(m,n)表示，1 <=m,n<=10^6。现在小明在A点想开车去B点，但当时
 * 恰好是晚间早高峰，城市有若干个堵车点，现在小明有至多一次机会化解某个点的堵车，求问小明从A点到B点的最短路径长度是多少？
 *     a. 输入 A点坐标[x1,y1] B点坐标[x2,y2].堵车点坐标[[i1, j1],[i2, j2],[i3, j3]... [in, jn]]
 *     b. 输出 路径
 *
 * 2. 反转链表，定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
 * 示例：
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 *
 * /**
 *  * Definition for singly-linked list.
 *  * public class ListNode {
 *  *     int val;
 *  *     ListNode next;
 *  *     ListNode(int x) { val = x; }
 *  * }
 *  */
public class Interview {

    public int Matrix(int x1, int y1, int x2, int y2, int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        int result = 0;
        Queue<Point> queue = new ArrayDeque<>();
        queue.add(new Point(x1, y1, 0));
        while (queue.isEmpty() || visited[x2][y2]) {
            Point point = queue.poll();
            int x = point.getX();
            int y = point.getY();
            if (x-1 > 0 && x-1 <m && !visited[x-1][y]) {
                visited[x-1][y] = true;
                queue.add(new Point(x-1, y, point.getLevel() + 1));
            }
            if (y-1 > 0 && y-1 <m && !visited[x][y-1]) {
                visited[x][y-1] = true;
                queue.add(new Point(x, y-1, point.getLevel() + 1));
            }
            if (x+1 > 0 && x+1 <m && !visited[x-1][y]) {
                visited[x-1][y] = true;
                queue.add(new Point(x-1, y, point.getLevel() + 1));
            }
            if (x-1 > 0 && x-1 <m && !visited[x-1][y]) {
                visited[x-1][y] = true;
                queue.add(new Point(x-1, y, point.getLevel() + 1));
            }
            result = Math.max(result, point.getLevel() + 1);
        }
        return result;
    }

    public class Point {
        private int x;
        private int y;
        private int level;
        public Point(int x, int y, int level) {
            this.x = x;
            this.y = y;
            this.level = level;
        }
        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
        public int getLevel() {
            return level;
        }
    }

    public static void main(String[] args) {
        Interview main = new Interview();
        ListNode first = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);
        first.next = second;
        second.next = third;
        ListNode result = main.reverseList(first);
        for (int i = 0; i < 3; i++) {
            System.out.println(result.val);
            result = result.next;
        }
    }

    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }


}
