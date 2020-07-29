package thh.studycode.algorithm;

/**
 * 双端队列，可以从前后添加或弹出
 */
public class A203_DoubleEndsQueue {

    private Node head;
    private Node tail;

    static class Node<T>{
        private T t;
        private Node next;
        private Node prev;

        public Node(T t) {
            this.t = t;
        }
    }


    public void addFromHead(int i){
        Node node = new Node(i);
        if(head == null){
            head = node;
            tail = node;
            return;
        }
        node.next = head;
        head.prev = node;
        head = node;
    }

    public void addFromTail(int i){
        Node node = new Node(i);
        if(head == null){
            head = node;
            tail = node;
            return;
        }
        tail.next = node;
        node.prev = tail;
        tail = node;
    }

    public Node pollFromHead(){
        if(head == null){
            return null;
        }
        if(head == tail) {
            Node result = head;
            head = null;
            tail = null;
            return result;
        }
        Node result = head;
        head = head.next;
        head.prev = null;
        result.next = null;
        return result;
    }

    public Node pollFromTail(){
        if (tail == null){
            return null;
        }
        if(head == tail) {
            Node result = head;
            head = null;
            tail = null;
            return result;
        }
        Node result = tail;
        tail = tail.prev;
        tail.next = null;
        result.prev = null;
        return result;
    }

    public static void main(String[] args) {
        A203_DoubleEndsQueue queue = new A203_DoubleEndsQueue();
        queue.addFromHead(1);
        queue.addFromHead(2);
        queue.addFromHead(3);
        queue.addFromHead(4);
        queue.addFromHead(5);
        while (queue.head != null){
            System.out.println(queue.pollFromHead().t);
        }
        System.out.println("-------------");
        queue.addFromTail(1);
        queue.addFromTail(2);
        queue.addFromTail(3);
        queue.addFromTail(4);
        queue.addFromTail(5);
        while (queue.tail != null){
            System.out.println(queue.pollFromTail().t);
        }
    }

}
