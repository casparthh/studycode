package thh.studycode.jvm.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FixedThreadPoolTest {
    volatile static int index = 0;

    public static void main(String[] args) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);



        for (int i = 0; i < 5; i++) {
            executor.submit(() -> {
                try {
                    System.out.println("开始执行" + (++index));
                    TimeUnit.MILLISECONDS.sleep(1000);
                    System.out.println("执行完成" + (index));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        System.out.println("done");
        executor.shutdown();
    }
}
