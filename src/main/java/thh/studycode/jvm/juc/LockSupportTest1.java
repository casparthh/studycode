package thh.studycode.jvm.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class LockSupportTest1 {


    static class MyThread extends Thread {
        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LockSupport.park();
            LockSupport.park();
            System.out.println("My name is " + Thread.currentThread().getName() + ". I'm running...");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new MyThread(), "T1");
        Thread t2 = new Thread(new MyThread(), "T2");
        t1.start();
        t2.start();
        LockSupport.unpark(t2);
        LockSupport.unpark(t1);
        TimeUnit.MILLISECONDS.sleep(2000);
        LockSupport.unpark(t2);
        LockSupport.unpark(t1);
//        LockSupport.unpark(t2);
//        LockSupport.unpark(t1);
    }
}
