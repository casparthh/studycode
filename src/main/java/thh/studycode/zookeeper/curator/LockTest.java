package thh.studycode.zookeeper.curator;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class LockTest {


    public void testCurator(){

    }

    @Test
    public void testLock() {
        String PATH = "/examples/locks";
        CuratorFramework client = CuratorClient.newClient();
        client.start();

        int nums = 500;
        CountDownLatch latch = new CountDownLatch(nums);
        ExecutorService es = Executors.newFixedThreadPool(100);
        try {
            for (int i = 0; i < nums; i++) {
                es.submit(() -> {
                    InterProcessMutex lock = new InterProcessMutex(client, PATH);
                    try {
                        if (lock.acquire(3, TimeUnit.SECONDS)) {
                            String name = Thread.currentThread().getName();
                            log.info(name + " 拿到锁了");
                            log.info(name + " 干活ing...");
                            log.info(name + " 干完活，释放锁");
                        } else {
                            log.error("lock timeout....");
                        }
                    } catch (Exception e) {
                        log.error("error occured,", e);
                    } finally {
                        try {
                            latch.countDown();
                            lock.release();
                        }catch (Exception e){
                            log.error("error occured,", e);
                        }
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
