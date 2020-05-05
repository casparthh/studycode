package thh.studycode.algorithm;

/**
 * 链表快慢指针问题
 */
public class Link01 {

    public static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }

        public Node findUpMid() {
            if (this == null || this.next == null) {
                return this;
            }

            Node slow = this;
            Node fast = this;
            while (fast != null && fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            if (fast.next == null) {
                //进入这里，说明是奇数。返回中间的Node
                return slow;
            } else {
                //进入这里，说明是偶数,返回上Node
                return slow;
            }
        }

        public Node findDownMid() {
            if (this == null || this.next == null) {
                return this;
            }

            Node slow = this.next;
            Node fast = this.next;
            while (fast != null && fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            if (fast.next == null) {
                //进入这里，说明是奇数。返回中间的Node
                return slow;
            } else {
                //进入这里，说明是偶数,返回上Node
                return slow;
            }
        }
    }

    public static void main(String[] args) {
        Node root = new Node(0);
        Node current = root;
        for (int i = 1; i < 3; i++) {
            Node n = new Node(i);
            current.next = n;
            current = n;
        }
        current = root;
        while (current != null) {
            System.out.print(current.value +"\t");
            current = current.next;
        }

        System.out.println("-----");
        System.out.println(root.findUpMid().value);
        System.out.println(root.findDownMid().value);

    }

}
