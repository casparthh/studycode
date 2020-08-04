package thh.studycode.jvm.juc;

import org.apache.commons.lang.time.DateFormatUtils;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class PhaserTest {

    public static void main(String[] args) {
        Phaser phaser = new Phaser(2);
        for (int i = 0; i < 14; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
            } catch (Exception e) {
            }
            new Thread(() -> {
                phaser.arriveAndAwaitAdvance();
                System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss SSS ") + Thread.currentThread().getName());

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
