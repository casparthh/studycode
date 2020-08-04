package thh.studycode.jvm.juc;

import org.apache.commons.lang.time.DateFormatUtils;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class ReadWriterLockTest {

    public static void main(String[] args) {
        ReadLock readLock = (new ReentrantReadWriteLock()).readLock();
        WriteLock writeLock = (new ReentrantReadWriteLock()).writeLock();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                readLock.lock();
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                    System.out.println("Date: " + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss") + "read lock " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    readLock.unlock();
                }

            }, "Thread " + i).start();

            new Thread(() -> {
                writeLock.lock();
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                    System.out.println("Date: " + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss") + "write lock " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    writeLock.unlock();
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
