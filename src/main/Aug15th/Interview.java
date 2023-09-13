package Aug15th;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;

public class Interview {
    private static Map<String, LongAdder> cache = new ConcurrentHashMap<>();
    public static void main(String[] args) {
        Interview main = new Interview();
//        System.out.println(main.traverse(new int[][] {
//                {1, 2, 3},
//                {4, 5, 6},
//                {7, 8, 9}
//        }));
//        System.out.println(main.sum(Arrays.asList("1", "2", "3", "4", "1", "2", "3", "4", "1", "2", "3", "4","1", "2", "3", "4", "1", "2", "3", "4", "1", "2", "3", "4")));
        main.incRequestCount("test1");
        main.incRequestCount("test1");
        main.incRequestCount("test2");
        System.out.println(main.getRequestCount("test1"));
        System.out.println(main.getRequestCount("test3"));
    }

    /**
     在内存中统计每个key的请求次数，假设key的基数不超过1000。
     比如： incRequestCount(“k1”);
     incRequestCount(“k1”);
     getRequestCount(“k1”)==2;
     注意：这些方法可能会被多个线程同时调用。
     */
    public void incRequestCount(String key){
        cache.computeIfAbsent(key, k -> new LongAdder()).increment();
    }

    public long getRequestCount(String key){
        return cache.getOrDefault(key, new LongAdder()).longValue();
    }


    /**
     用多线程实现获取多个key的值并求和，sum方法处理时间不超过200ms，即sum方法的timeout=200ms
     假设输入的key list总是非空。
     非多线程求值(无法控制处理时间)：
     int result=0;
     for(String key:keys){
     result+=getValue(key);
     }
     return result;
     */

    private ExecutorService exec;
    private List<Future<Integer>> tasks = new ArrayList<>();
    class SumCalc implements Callable<Integer> {
        List<String> keys;
        int start, end;

        public SumCalc(List<String> keys, int start, int end) {
            this.keys = keys;
            this.start = start;
            this.end = end;
        }

        @Override
        public Integer call() {
            System.out.println("线程"+ Thread.currentThread().getName());
            int sum = 0;
            for (int i = start; i < end; i++) {
                sum += getValue(keys.get(i));
            }
            return sum;
        }
    }
    public int sum(List<String> keys){
        int coreNum = Runtime.getRuntime().availableProcessors();
        exec = Executors.newFixedThreadPool(coreNum);
        int increment = keys.size() / coreNum + 1;
        long timestamp = System.currentTimeMillis();
        for (int i = 0; i < coreNum; i++) {
            int start = increment * i;
            int end = Math.min(start + increment, keys.size());
            SumCalc cal = new SumCalc(keys, start, end);
            FutureTask<Integer> task = new FutureTask<>(cal);
            tasks.add(task);
            if (!exec.isShutdown()) {
                exec.submit(task);
            }
        }
        exec.shutdown();
        System.out.println(System.currentTimeMillis() - timestamp);
        int result = getResult();
        System.out.println(System.currentTimeMillis() - timestamp);
        return result;
    }

    private int getResult() {
        int result = 0;
        for (Future<Integer> task : tasks) {
            try {
                result += task.get(200, TimeUnit.MILLISECONDS);
            } catch (InterruptedException | ExecutionException ignored) {

            } catch (TimeoutException timeout) {
                System.out.println(Thread.currentThread().getName() + "超时");
                task.cancel(true);
            }
        }
        return result;
    }

    /**
     假设getValue已经实现，从远端获取key对应的value，平均耗时为50ms。
     */
    public int getValue(String key){
        try {
            Thread.sleep(50);
        } catch (InterruptedException ignored) {}
        return 0;
    }

    /**
     打平list，list的element可能也是list，假设不存在循环引用
     比如输入[a,[b,[c],d],e], 输出[a,b,c,d,e]
     */
    public List<Object> flattenList(List<Object> list){
        List<Object> result = new ArrayList<>();
        for (Object o : list) {
            if (o instanceof List) {
                List<Object> entity = (List<Object>) o;
                result.addAll(flattenList(entity));
            } else {
                result.add(o);
            }
        }
        return result;
    }

    /**
     正方形正斜线遍历。
     比如输入1,2,3 , 输出1,2,4,3,5,7,6,8,9
            4,5,6
            7,8,9
     */
    public List<Integer> traverse(int[][] data){
        int m = data.length;
        List<Integer> result = new ArrayList<>();
        int startX = 0, startY = 0, sum = 0;
        while (result.size() < m * m) {
            for (; startX < m && startY >= 0; startX++, startY--) {
                result.add(data[startX][startY]);
            }
            sum++;
            startY = Math.min(sum, m-1);
            startX = Math.max(sum - startY, 0);
        }
        return result;
    }

}
