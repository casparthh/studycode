package thh.studycode.jvm;



import sun.misc.Unsafe;

import java.util.concurrent.TimeUnit;

public class VolatileTest {

    static volatile boolean stop = false;

    public void run() {
        System.out.println("start run...");
        Unsafe unsafe = Unsafe.getUnsafe();
        long a = unsafe.allocateMemory(1);
        while (!stop) {
            synchronized (this){

            }
        }
        System.out.println("end run...");
    }

    public static void main(String[] args) throws Exception {
        VolatileTest t = new VolatileTest();
        new Thread(t::run).start();
        TimeUnit.SECONDS.sleep(1);
        stop = true;
    }
}
