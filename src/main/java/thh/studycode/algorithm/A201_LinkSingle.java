package thh.studycode.algorithm;

/**
 * 单向链表
 */
public class A201_LinkSingle {

    static class Node{
        int value;
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
        Node n6 = new Node(6);
        Node n7 = new Node(3);
        Node n8 = new Node(8);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = n8;

        Node head = reverse(n1);
        while(head != null){
            System.out.print(head.value + "\t");
            head = head.next;
        }

        System.out.println("");
        head = delete(n8, 3);
        while(head != null){
            System.out.print(head.value + "\t");
            head = head.next;
        }
        System.out.println("");
    }

    /**
     * 反转
     * @param head
     * @return
     */
    public static Node reverse(Node head){
        Node prev = null;
        Node current = head;
        while (current != null){
            Node bak = current.next;
            current.next = prev;
            prev = current;
            current = bak;
        }
        return prev;
    }


    /**
     * 把给定值的node删除
     * @param head
     * @return
     */
    public static Node delete(Node head, int value){
        Node prev = null;
        Node current = head;
        while (current != null){
            if(current.value == value){
                if(prev != null){
                    prev.next = current.next;
                } else {
                    head = current.next;
                }
            } else {
                prev = current;
            }
            current = current.next;
        }
        return head;
    }
}
