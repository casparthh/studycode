package thh.studycode.jvm.juc;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {
    static int[] nums = {1, 2, 3, 4};
    static char[] letters = {'a', 'b', 'c', 'd'};
    static Thread letterThread = null;
    static Thread numThread = null;

    public static void main(String[] args) {
        numThread = new Thread(() -> {
            for (int i = 0; i < nums.length; i++) {
                System.out.println(nums[i]);
                LockSupport.unpark(letterThread);
                LockSupport.park();
            }
        });

        letterThread = new Thread(() -> {
            for (int i = 0; i < letters.length; i++) {
                LockSupport.park();
                System.out.println(letters[i]);
                LockSupport.unpark(numThread);
            }
        });

        try {
            letterThread.start();
            numThread.start();
            letterThread.join();
            numThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
