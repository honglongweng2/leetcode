package Aug11th;

import java.time.Duration;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class Test {
    public static void main(String[] args) {
//        Interview interview = Interview.getInstance();
//        System.out.println(interview.search(new int[] {1, 2, 3, 4, 5}, 6));
        ForkJoinPool executor = new ForkJoinPool(10);
        SumTask sumTask = new SumTask(1, 10000);
        ForkJoinTask<Long> future = executor.submit(sumTask);

        if (future.isCompletedAbnormally()) {
            future.getException().printStackTrace();
        }

        try {
            System.out.println("result: " + future.get());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
