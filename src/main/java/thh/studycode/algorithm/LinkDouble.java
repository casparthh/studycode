package thh.studycode.algorithm;

public class LinkDouble {

    static class Node{
        int value;
        Node prev;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        n1.prev = null;
        n1.next = n2;
        n2.prev = n1;
        n2.next = n3;
        n3.prev = n2;
        n3.next = n4;
        n4.prev = n3;
        n4.next = n5;
        n5.prev = n4;
        n5.next = null;

        Node head = reverse(n1);
        while(head != null){
            System.out.println(head.value);
            head = head.next;
        }
    }

    /**
     *  双向链表反转
     * @param head
     * @return
     */
    public static Node reverse(Node head){
        while (true){
            Node bak = head.next;
            head.next = head.prev;
            head.prev = bak;
            if(bak == null){
                return head;
            } else {
                head = bak;
            }
        }
    }

}
