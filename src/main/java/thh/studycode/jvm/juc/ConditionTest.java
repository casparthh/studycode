package thh.studycode.jvm.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 写一个固定容量同步容器，拥有put和get 方法，能够支持2个生产者以及10个消费者线程的阻塞调用。
 */
public class ConditionTest {

    static class Container<T> {
        int size;
        List<T> datalist;
        int max;

        Lock lock = new ReentrantLock();
        Condition provider = lock.newCondition();
        Condition consumer = lock.newCondition();

        public Container(int max) {
            this.datalist = new ArrayList();
            this.max = max;
        }

        public int put(T t) throws InterruptedException {
            lock.lock();
            try {
                while (size == max) {
                    provider.await();
                }
                datalist.add(t);
                size++;
                consumer.signalAll();
            } finally {
                lock.unlock();
            }
            return size;
        }

        public T get() throws InterruptedException {
            T t = null;
            lock.lock();
            try {
                while (size == 0) {
                    consumer.await();
                }
                t = datalist.get(0);
                datalist.remove(0);
                size--;
                provider.signalAll();
            } finally {
                lock.unlock();
            }
            return t;
        }
    }

    public static void main(String[] args) throws Exception {
        final Container<Integer> container = new Container(5);
        //2个生产者
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    try {
                        int num = container.put(j);
                        System.out.println(Thread.currentThread().getName() + " put " + j + ", size :" + num);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "provider-" + i).start();
        }


        //10个消费者线程
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 200; j++) {
                    try {
                        int num = container.get();
                        System.out.println("------" + Thread.currentThread().getName() + " get " + num);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }, "consumer-" + i).start();
        }
    }
}
