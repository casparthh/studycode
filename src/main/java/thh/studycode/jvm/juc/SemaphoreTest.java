package thh.studycode.jvm.juc;

import org.apache.commons.lang.time.DateFormatUtils;

import java.util.Date;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    private static void print(){

    }
    public static void main(String[] args) throws Exception{
        Semaphore semaphore = new Semaphore(10);
        for (int i = 0; i < 50; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss SSS ") + Thread.currentThread().getName());
                semaphore.release();
            },"Thread "+ i).start();
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
