package thh.studycode.jvm.juc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程与高并发面试题一、
 * 实现2个容器，提供两个方法 add、size ，写两个线程：
 * 线程1，添加10个元素到容器中
 * 线程2，实时监控元素个数，当数到5时，线程2给出提示并结束
 */
public class TheadInterviewQuestion1 {

    static class Container<T> {
        volatile int i;
        List<T> datalist;

        public Container() {
            this.i = 0;
            this.datalist = Collections.synchronizedList(new ArrayList<>());
        }

        public void add(T t) {
            datalist.add(t);
            i++;
        }

        public int size() {
            return i;
        }
    }

    public static void main(String[] args) {
        Container<Integer> container = new Container<Integer>();
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                lock.lock();
                try {
                    container.add(i);
                    if (i == 4) {
                        condition.await();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
            System.out.println("添加10个元素到容器中完成！！！");
        }, "provider").start();

        new Thread(() -> {
            while (true) {
                if (container.size() == 5) {
                    System.out.println("有5个了，我退出了。。。");
                    lock.lock();
                    try {
                        condition.signal();
                    } finally {
                        lock.unlock();
                    }
                    break;
                }
            }
        }, "consumer").start();
    }
}
