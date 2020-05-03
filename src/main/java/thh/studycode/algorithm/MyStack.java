package thh.studycode.algorithm;

public class MyStack {

    static class Node {
        int value;
        Node prev;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    Node tail;

    public void push(Node node) {
        if (tail == null) {
            tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
    }

    public Node pop() {
        if (tail == null) {
            return null;
        }
        Node cur = tail;
        if (cur.prev != null) {
            tail = cur.prev;
            tail.next = null;
        } else {
            tail = null;
        }
        cur.prev = null;
        return cur;
    }

    public Node getMin(){

        return tail;
    }

    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push(new Node(1));
        stack.push(new Node(2));
        stack.push(new Node(3));
        stack.push(new Node(4));

        Node n =stack.pop();
        while (n != null){
            System.out.print(n.value+"\t");
            n = stack.pop();
        }
        System.out.println();
    }

}
