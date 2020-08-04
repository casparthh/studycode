package thh.studycode.jvm.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock(true);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName());
                } finally {
                    lock.unlock();
                }
            }, "Thread " + i).start();
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
