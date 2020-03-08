package thh.studycode.zookeeper.lock;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Slf4j
public class TestRunner {

    /**
     * 监控当前持有锁的节点，该模式有问题，当线程持有锁到释放锁的时间小于10毫秒时，可能会出现getData被删除的问题。
     */
    @Test
    public void test() {
        CountDownLatch downLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                DefaultLock lock = new DefaultLock();
                lock.lock();
                log.info(Thread.currentThread().getName()+" 拿到锁++++++++++++");
                lock.lock();
                log.info(Thread.currentThread().getName()+" 拿到重入锁++++++++++++");
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    log.error("error occured.", e);
                }
                System.out.println(" i am working....");
                log.info(Thread.currentThread().getName()+" 干活。。。。。。。");
                lock.unlock();
                log.info(Thread.currentThread().getName()+" 释放了锁-----------");

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    log.error("error occured.", e);
                }

                lock.lock();
                log.info(Thread.currentThread().getName()+" 再次拿到锁++++++++++++");
                log.info(Thread.currentThread().getName()+" 干活。。。。。。。");
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    log.error("error occured.", e);
                }
                lock.unlock();
                log.info(Thread.currentThread().getName()+" 再次释放了锁-----------");
                downLatch.countDown();

            }).start();
        }

        try {
            downLatch.await();
            System.out.println("finish job ........................");
        } catch (InterruptedException e) {
            log.error("error occured.", e);
        }
    }


}
