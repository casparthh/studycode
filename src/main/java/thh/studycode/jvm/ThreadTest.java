package thh.studycode.jvm;

public class ThreadTest {

    public static void main(String[] args) throws Exception{
         Thread t1 = new Thread(()->{
             System.out.println(1);
         });

        Thread t2 = new Thread(()->{
            System.out.println(2);
            try {
                t1.join();
                t1.start();
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        Thread t3 = new Thread(()->{
            System.out.println(3);
            try {
                t2.join();
                t2.start();
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        Thread t4 = new Thread(()->{
            System.out.println(4);
            try {
                t3.join();
                t3.start();
            }catch (Exception e){
                e.printStackTrace();
            }
        });


        t4.join();
        t4.start();
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
