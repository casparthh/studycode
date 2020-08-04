package thh.studycode.jvm.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {

    public static char[] letters = {'a', 'b', 'c', 'd'};
    public static int[] nums = {1, 2, 3, 4};

    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition read = lock.newCondition();
        Condition write = lock.newCondition();


        System.out.println(" ---- done !!!!");
    }
}
