package thh.studycode.algorithm;

/**
 * 链表结构实现固定长度的队列
 */
public class A204_LimitQueueByLink {

    static class Node{
        private int i;
        private Node next;

        public Node(int i) {
            this.i = i;
        }
    }

    private Node head;
    private int maxSize = 5;
    private int size = 0;

    public void addFromHead(int num){
        if (maxSize == size){
            System.out.println("没有位置放了");
            throw new RuntimeException();
        }
        Node node = new Node(num);
        if(head == null){
            head = node;
        } else {
            node.next = head;
            head = node;
        }
        size ++;
    }

    public int pollFromHead(){
        if(size == 0){
            System.out.println("没有了");
            throw new RuntimeException();
        }
        if (head == null){
            System.out.println("没有了");
            throw new RuntimeException();
        }
        Node node = head;
        head = head.next;
        node.next = null;
        size --;
        return node.i;
    }

    public static void main(String[] args) {
        A204_LimitQueueByLink queue = new A204_LimitQueueByLink();
        queue.addFromHead(1);
        queue.addFromHead(2);
        queue.pollFromHead();
        queue.addFromHead(3);
        queue.addFromHead(4);
        queue.addFromHead(5);
        queue.pollFromHead();
        queue.pollFromHead();
        queue.addFromHead(5);
        queue.addFromHead(6);
        queue.pollFromHead();
        queue.pollFromHead();
        queue.addFromHead(5);
        queue.addFromHead(6);
        while (queue.head != null){
            System.out.println(queue.pollFromHead());
        }

    }
}
