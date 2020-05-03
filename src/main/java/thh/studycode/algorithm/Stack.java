package thh.studycode.algorithm;

public class Stack {

    static class Node {
        int value;
        Node prev;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    Node tail;

    public void add(Node node) {
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

    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.add(new Node(1));
        stack.add(new Node(2));
        stack.add(new Node(3));
        stack.add(new Node(4));

        Node n =stack.pop();
        while (n != null){
            System.out.print(n.value+"\t");
            n = stack.pop();
        }
        System.out.println();
    }

}
