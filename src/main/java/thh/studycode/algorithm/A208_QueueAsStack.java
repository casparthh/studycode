package thh.studycode.algorithm;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 用队列实现栈结构，实现后进先出
 */
public class A208_QueueAsStack {

    //申请一个存放数据队列
    Queue<Integer> queue = new ArrayBlockingQueue<Integer>(Integer.MAX_VALUE);
    //申请一个辅助队列
    Queue<Integer> help = new ArrayBlockingQueue<Integer>(Integer.MAX_VALUE);

    public int pop() {
        while(help != null){

        }
      return 0;
    }

    public void push(int value) {
        queue.add(value);
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new ArrayBlockingQueue<Integer>(100);
        queue.add(1);
        queue.add(2);
        queue.add(3);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        queue.add(4);
        queue.add(5);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }

}
