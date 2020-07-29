package thh.studycode.algorithm;

import java.util.Random;

/**
 * 链表快慢指针问题
 * 输入链表头节点，奇数长度返回中点，偶数长度返回上中点
 * 输入链表头节点，奇数长度返回中点，偶数长度返回下中点
 */
public class A601_QuickSlowLinkPointer {

    public static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }

        //返回上中点
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
            //奇数长度返回中点，偶数长度返回上中点
            return slow;
        }

        //返回下中点
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

            //奇数长度返回中点，偶数长度返回下中点
            return slow;
        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            int len = (new Random()).nextInt(100)+10;
            Node root = null;
            Node current = null;

            for (int j = 0; j < len; j++) {
                Node n = new Node(j);
                if (j == 0){
                    current = n;
                    root = n;
                } else{
                    current.next = n;
                }
                current = n;
            }
            System.out.println("len :" + len);
            System.out.println(root.findUpMid().value+1);
            System.out.println(root.findDownMid().value+1);

        }

    }

}
