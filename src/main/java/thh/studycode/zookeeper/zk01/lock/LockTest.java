package thh.studycode.zookeeper.zk01.lock;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class LockTest {

    @Test
    public void testLock() {
        int nums = 100;
        CountDownLatch latch = new CountDownLatch(nums);
        ExecutorService es = Executors.newFixedThreadPool(100);
        try {
            for (int i = 0; i < nums; i++) {
                es.submit(() -> {
                    try {
                        LockUtils util = new LockUtils();
                        boolean locked = util.lock(10);
                        if (locked) {
                            util.lock(10);
                            String name = Thread.currentThread().getName();
                            log.info(name + " 拿到锁了");
                            log.info(name + " 干活ing...");
                            log.info(name + " 干完活，释放锁");
                            latch.countDown();
                            util.unlock();
                            util.unlock();
                        } else {
                            log.error("lock timeout....");
                        }
                    } catch (Exception e) {
                        log.error("error occured,", e);
                    }
                });
            }

            latch.await();
            log.info("-----------DONE-------------");
        } catch (InterruptedException e) {
            log.error("error occured.", e);
        }
    }
}
