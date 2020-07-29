package thh.studycode.algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * rand 指针是单链表节点结构中新增的指针，rand可能指向链表的任意一个节点，也可能指向null.
 * 给定一个由Node节点类型组成的无环单链表的头节点head,请实现一个函数完成这个链表的复制，并返回复制的新链表的头节点
 */
public class A604_LinkCopy {

    static class Node {
        int value;
        Node next;
        Node rand;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 使用hashmap实现
     * 1.遍历并将复制节点存放在hashmap中，形成节点与复制节点对
     * 2.遍历并将新节点的next 和rand设置好。
     * 返回复制链表head
     *
     * @param head
     * @return
     */
    public Node copyNodeWithRand(Node head) {
        Map<Node, Node> copyNodeMap = new HashMap<Node, Node>();
        Node curr = head;
        while (curr != null) {
            copyNodeMap.put(curr, new Node(curr.value));
            curr = curr.next;
        }

        curr = head;
        while (curr != null) {
            Node copyNode = copyNodeMap.get(curr);
            copyNode.rand = copyNodeMap.get(curr.rand);
            copyNode.next = copyNodeMap.get(curr.next);
            curr = curr.next;
        }


        return copyNodeMap.get(head);
    }

    /**
     * 1.复制节点，并插在中间 1->2->3 变成 1->1->2->2->3->3
     * 2.设置复制节点的rand
     * 3.拆分出新链表并返回head
     *
     * @param head
     * @return
     */
    public Node copyNodeWithRand2(Node head) {
        Node curr = head;
        //复制节点，并插在中间 1->2->3 变成 1->1->2->2->3->3
        while (curr != null) {
            Node copyNode = new Node(curr.value);
            Node next = curr.next;
            curr.next = copyNode;
            copyNode.next = next;
            curr = next;
        }

        //设置复制节点的rand
        curr = head;
        while (curr != null){
            Node currCopy = curr.next;
            if(curr.rand != null) {
                currCopy.rand = curr.rand.next;
            }
            curr = currCopy.next;
        }

        //拆分出新链表并返回head
        Node newHead = head.next;
        curr = head;
        Node currCopy = newHead;
        while (curr != null){
            curr.next = curr.next.next;
            if(curr.next != null) {
                currCopy.next = currCopy.next.next;
            }
            curr = curr.next;
            currCopy= currCopy.next;
        }
        return newHead;
    }

    public static void main(String[] args) {
        A604_LinkCopy link = new A604_LinkCopy();
        Node root = new Node(new Random().nextInt(100));
        Node n1 = new Node(new Random().nextInt(100));
        Node n2 = new Node(new Random().nextInt(100));
        Node n3 = new Node(new Random().nextInt(100));
        Node n4 = new Node(new Random().nextInt(100));
        Node n5 = new Node(new Random().nextInt(100));
        Node n6 = new Node(new Random().nextInt(100));
        root.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;

        root.rand = n3;
        n3.rand = n2;
        n4.rand = n4;
        n5.rand = n2;
        n6.rand = n1;

        Node newRoot = link.copyNodeWithRand(root);
        root = link.copyNodeWithRand2(root);

        Node n = root;
        Node newN = newRoot;
        while (n != null && newN != null) {
            if (n.value != newN.value) {
                System.out.println(false);
            }
            if (n.rand !=null && n.rand.value != newN.rand.value) {
                System.out.println(false);
            }
            n = n.next;
            newN = newN.next;
        }
        if (n != null || newN != null) {
            System.out.println(false);
        }
        System.out.println("--- true");
    }
}
