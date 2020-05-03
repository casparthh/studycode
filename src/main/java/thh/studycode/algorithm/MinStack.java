package thh.studycode.algorithm;

import java.util.Stack;

public class MinStack {
    Stack<Integer> stack = new Stack<Integer>();
    Stack<Integer> help = new Stack<Integer>();

    public void push(int value) {
        stack.push(value);
        int min = help.size()>0?help.peek(): value;
        help.push(min > value ? value : min);
    }

    public int pop() {
        help.pop();
        return stack.pop();
    }

    public int getMin() {
        return help.peek();
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(7);
        minStack.push(4);
        minStack.push(-2);
        minStack.push(1);
        minStack.push(8);
        minStack.push(-4);
        System.out.println(minStack.getMin());
        System.out.println(minStack.pop());
        System.out.println(minStack.pop());
        System.out.println(minStack.getMin());
        System.out.println(minStack.pop());
        System.out.println(minStack.pop());
        System.out.println(minStack.getMin());
        System.out.println(minStack.pop());
        System.out.println(minStack.pop());
        System.out.println(minStack.getMin());
    }

}
