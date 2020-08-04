package thh.studycode.jvm.juc;

import lombok.AllArgsConstructor;

import java.util.concurrent.Exchanger;

public class ExchangerTest {

    public static void main(String[] args) {
        Exchanger exchanger = new Exchanger();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    User user = new User(Thread.currentThread().getName());
                    User u = (User) exchanger.exchange(user);
                    System.out.println("My name is " + Thread.currentThread().getName() + ", I get " + u.name);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "Thread "+ i).start();
        }
    }

    @AllArgsConstructor
    static class User {
        String name;
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
