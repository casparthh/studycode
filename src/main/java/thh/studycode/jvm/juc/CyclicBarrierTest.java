package thh.studycode.jvm.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierTest {


    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(2, () -> {
            System.out.println("人满，小火车出发！！！");
        });

        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " is ready !!!");
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, "Person-" + i).start();
            TimeUnit.MILLISECONDS.sleep(100);
        }
    }

    /*
    ReentrantLock
    Condition
    LockSuport
    CountDownLatch
    CyclicBarrier
    Phaser
    ReadWriteLock
    Semaphore
    Exchanger

    AQS(AbstractQueuedSynchronizer)

     */
}
