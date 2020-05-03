package thh.studycode.algorithm;

public class Deque {

    static class Node {
        int value;
        Node prev;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }


    Node head;
    Node tail;

    public void addFront(Node node) {
        if (tail == null) {
            head = node;
            tail = node;
        }
        node.next = head;
        head.prev = node;
        head = node;
    }

    public void addEnd(Node node) {
        if (head == null) {
            head = node;
            tail = node;
        }
        node.prev = tail;
        tail.next = node;
        tail = node;
    }

    public Node popFront() {
        if(head == null){
            return null;
        }
        Node cur = head;
        if(head == tail){
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        cur.next = null;
        return cur;
    }

    public Node popEnd() {
        if(tail == null){
            return null;
        }
        Node cur = tail;
        if(tail == head){
            tail = null;
            head = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        cur.prev = null;
        return cur;
    }

    public static void main(String[] args) {
        Deque deque = new Deque();
        deque.addFront(new Node(2));
        deque.addFront(new Node(1));
        deque.addEnd(new Node(3));
        deque.addEnd(new Node(4));
        deque.addFront(new Node(0));
        deque.addEnd(new Node(8));

        Node node = deque.head;
        while (node != null) {
            System.out.print(node.value + "\t");
            node = node.next;
        }
        System.out.println();

        Node n1 = deque.popFront();
        Node n2 = deque.popEnd();
        Node n3 = deque.popFront();
        Node n4 = deque.popEnd();
        Node n5 = deque.popFront();
        Node n6 = deque.popEnd();

        node = deque.head;
        while (node != null) {
            System.out.print(node.value + "\t");
            node = node.next;
        }
        System.out.println();
    }


}
