package Aug11th;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 交替打印：
 * 上锁(synchronize或者lock都可以)，然后打印方法里判断该不该自己打印，不是自己的轮次就wait()或者unlock()
 */
public class LockPrint {
    private static int num;
    private final static Lock lock = new ReentrantLock();
    private static void print(int condition) {
        while (num < 100) {
            lock.lock();
            if (num % 3 == condition && num < 100) {
                System.out.println(Thread.currentThread().getName() + " " + num++);
            }
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> print(0), "A");
        Thread t2 = new Thread(() -> print(1), "B");
        Thread t3 = new Thread(() -> print(2), "C");
        t1.start();
        t2.start();
        t3.start();
    }

}
class MyTask {
    public synchronized void printNumber(int i) {
        try{
            notifyAll();
            System.out.println(Thread.currentThread().getName() + " " + i);
            wait();
        } catch (InterruptedException ignored) {}
    }
}