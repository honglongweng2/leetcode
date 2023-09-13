package Aug11th;

import java.util.concurrent.RecursiveTask;

/**
 * ForkJoinPool学习
 *
 * 一般直接继承三种子类
 * RecursiveAction：用于递归执行但不需要返回结果的任务。
 * RecursiveTask ：用于递归执行需要返回结果的任务。
 * CountedCompleter ：在任务完成执行后会触发执行一个自定义的钩子函数
 */
public class SumTask extends RecursiveTask<Long> {
    private final int start;
    private final int end;

    public SumTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum = 0;
        if (end - start < 100) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            // 拆分任务
            int mid = (start + end) / 2;
            SumTask left = new SumTask(start, mid);
            SumTask right = new SumTask(mid+1, end);
            left.fork();
            right.fork();

            // 等待子任务完成
            long leftSum = left.join();
            long rightSum = right.join();
            sum = leftSum + rightSum;
        }
        return sum;
    }
}
