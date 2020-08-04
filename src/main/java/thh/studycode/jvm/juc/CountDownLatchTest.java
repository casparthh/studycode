package thh.studycode.jvm.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest {


    public static void main(String[] args) throws InterruptedException{
        CountDownLatch latch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
                latch.countDown();
            }, "test" + i).start();
        }
        latch.await();
        System.out.println("DONE!!!");
    }


}
