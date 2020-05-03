package thh.studycode.algorithm;

import java.util.Stack;

/**
 * 用栈实现队列结构，实现先进先出
 */
public class StackAsQueue {

    //申请一个存放数据栈
    Stack<Integer> stack = new Stack<Integer>();
    //申请一个辅助栈
    Stack<Integer> help = new Stack<Integer>();

    public int pop() {
        while(stack.size() > 0) {
            help.push(stack.pop());
        }
        return help.size() > 0 ? help.pop() : -1;
    }

    public void push(int value) {
        while (help.size() > 0) {
            stack.push(help.pop());
        }
        stack.push(value);
    }

    public static void main(String[] args) {
        StackAsQueue queue = new StackAsQueue();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        queue.push(4);
        queue.push(5);
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
    }

}
