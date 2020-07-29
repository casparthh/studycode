package thh.studycode.algorithm;

import lombok.AllArgsConstructor;

/**
 * 给定一棵二叉树的头节点head，和另外两个节点a和b。返回a和b最低公共祖先。
 */
public class A903_BinaryTreeRecursion_SameHead {

    static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    @AllArgsConstructor
    static class Info {
        Node head;
        boolean hasA;
        boolean hasB;
    }

    /**
     * 给定一棵二叉树的头节点head，和另外两个节点a和b。返回a和b最低公共祖先。
     * @param head
     * @param a
     * @param b
     * @return
     */
    public Info findSameHead(Node head, Node a, Node b) {
        if (head == null || a == null || a == null) {
            return new Info(null, false, false);
        }
        if (a == b) {
            return new Info(a, true, true);
        }
        Info linfo = findSameHead(head.left, a, b);
        Info rinfo = findSameHead(head.right, a, b);

        if (linfo.hasA && linfo.hasB) {
            return linfo;
        }
        if (rinfo.hasA && rinfo.hasB) {
            return rinfo;
        }
        if ((linfo.hasA || linfo.hasB) && (rinfo.hasA || rinfo.hasB)) {
            return new Info(head, true, true);
        }
        if (head == a) {
            return new Info(head, true, linfo.hasB || rinfo.hasB);
        }
        if (head == b) {
            return new Info(head, linfo.hasA || rinfo.hasA, true);
        }
        return new Info(null, linfo.hasA || rinfo.hasA , linfo.hasB || rinfo.hasB);
    }

    public static void main(String[] args) {
        A903_BinaryTreeRecursion_SameHead tree = new A903_BinaryTreeRecursion_SameHead();
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);
        Node n9 = new Node(9);
        Node n10 = new Node(10);
        Node n11 = new Node(11);
        Node n12 = new Node(12);
        Node n13 = new Node(13);
        Node n14 = new Node(14);
        Node n15 = new Node(15);
        Node n16 = new Node(16);
        Node n17 = new Node(17);
        Node n18 = new Node(18);

        n1.left = n2;
        n1.right = n3;

        n2.left = n4;
//        n2.right = n5;

//        n3.left = n6;
        n3.right = n7;

        n4.left = n8;
        n4.right = n9;

//        n5.left = n10;
//        n5.right = n11;

//        n6.left = n12;
//        n6.right = n13;

        n7.left = n14;
        n7.right = n15;

        n8.left = n16;
        n8.right = n17;

//        n11.left = n18;

        Info info = tree.findSameHead(n8, n8,n1);
        if(!info.hasA || !info.hasB || info.head == null){
            System.out.println("no same head!!!");
        } else {
            System.out.println(info.head.value);
        }

    }

}
