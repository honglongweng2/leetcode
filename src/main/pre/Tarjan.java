package pre;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tarjan {
    private List<List<Integer>> result;
    // 图
    private List<Integer>[] map;
    // 时间戳，记录到达节点的时间
    int[] timestamp;
    // 时间
    int time;
    boolean[] visited;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        // tarjan算法
        result = new ArrayList<>();
        map = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            map[i] = new ArrayList<>();
        }
        for (List<Integer> connection : connections) {
            map[connection.get(0)].add(connection.get(1));
            map[connection.get(1)].add(connection.get(0));
        }

        timestamp = new int[n];
        // 初始化-1
        Arrays.fill(timestamp, -1);
        time = 0;
        visited = new boolean[n];
        dfs(0, -1);
        return result;
    }

    private void dfs(int current, int previous) {
        visited[current] = true;
        timestamp[current] = time;
        int currentDeep = time;
        time++;
        for (int i : map[current]) {
            if (i == previous) {
                // 不回头
                continue;
            }
            if (!visited[i]) {
                // 从没走过，继续dfs
                dfs(i, current);
            }
            // 同一环上的节点的时间戳相同，都赋值最小时间戳
            timestamp[current] = Math.min(timestamp[current], timestamp[i]);
            if (currentDeep < timestamp[i]) {
                // 如果到达i节点的时间大于当前时间，则当前点不在闭环上
                // 则当前点与下一个点i就是其中应该答案。
                result.add(Arrays.asList(current, i));
            }
        }
    }

    public static void main(String[] args) {
        Tarjan test = new Tarjan();
        List<List<Integer>> in = new ArrayList<>();
        in.add(Arrays.asList(0, 1));
        in.add(Arrays.asList(1, 4));
        in.add(Arrays.asList(1, 5));
        in.add(Arrays.asList(4, 5));
        in.add(Arrays.asList(0, 2));
        in.add(Arrays.asList(0, 3));
        in.add(Arrays.asList(2, 3));
        in.add(Arrays.asList(4, 6));
        in.add(Arrays.asList(1, 6));
        System.out.println(test.criticalConnections(7, in));
    }
}
