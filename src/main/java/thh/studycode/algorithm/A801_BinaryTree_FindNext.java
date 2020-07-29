package thh.studycode.algorithm;

/**
 * 给定二叉树中的某个节点，返回该节点的后继节点
 * 后继节点：指一棵二叉树中，在中序遍历序列中，一个节点的下一个节点
 * 前驱节点：指一颗二叉树中，在中序遍历的序列中，一个节点的前一个节点。
 */
public class A801_BinaryTree_FindNext {

    static class Node {
        int value;
        Node left;
        Node right;
        Node parent;

        public Node(int value) {
            this.value = value;
        }
    }


    /**
     * 打印前驱节点
     *
     * @param node
     * @return
     */
    public Node getNextNode(Node node) {
        if (node == null) {
            return null;
        }

        //中序遍历 【左中右】

        //1. 如果存在右节点存在右节点，返回右树上的最左节点
        //2. 如果当前节点是父节点的左节点，返回父节点
        //3. 如果当前节点是父节点的右节点，一直往父节点找，直到找出某个节点，它又是自己父节点的左节点。如果找不到就返回null
        //4. 其它情况返回null

//        if (node.parent == null || node == node.parent.left) {
        if (node.right != null) {
            Node r = node.right;
            while (r.left != null) {
                r = r.left;
            }
            return r;
        } else if (node.parent != null && node == node.parent.left) {
            return node.parent;
        } else if (node.parent != null && node == node.parent.right) {
            node = node.parent;
            while (node != node.parent.left) {
                node = node.parent;
                if (node == null || node.parent == null) {
                    return null;
                }
            }
            return node.parent;
        } else {
            return null;
        }
    }


    public static void main(String[] args) {
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
        Node n19 = new Node(19);

        n19.left = n1;

        n1.left = n2;
        n2.parent = n1;
        n1.right = n3;
        n3.parent = n1;

        n2.left = n4;
        n4.parent = n2;
        n2.right = n5;
        n5.parent = n2;

        n3.left = n6;
        n6.parent = n3;
        n3.right = n7;
        n7.parent = n3;

        n4.left = n8;
        n8.parent = n4;
        n4.right = n9;
        n9.parent = n4;

        n5.left = n10;
        n10.parent = n5;
        n5.right = n11;
        n11.parent = n5;

        n6.left = n12;
        n12.parent = n6;
        n6.right = n13;
        n13.parent = n6;

        n7.left = n14;
        n14.parent = n7;
        n7.right = n15;
        n15.parent = n7;

        n8.left = n16;
        n16.parent = n8;
        n8.right = n17;
        n17.parent = n8;

        n9.left = n18;
        n18.parent = n9;

        A801_BinaryTree_FindNext tree = new A801_BinaryTree_FindNext();
        System.out.println("n1 next = " + tree.getNextNode(n1).value);
        System.out.println("n2 next = " + tree.getNextNode(n2).value);
        System.out.println("n3 next = " + tree.getNextNode(n3).value);
        System.out.println("n4 next = " + tree.getNextNode(n4).value);
        System.out.println("n5 next = " + tree.getNextNode(n5).value);
        System.out.println("n6 next = " + tree.getNextNode(n6).value);
        System.out.println("n7 next = " + tree.getNextNode(n7).value);
        System.out.println("n8 next = " + tree.getNextNode(n8).value);
        System.out.println("n9 next = " + tree.getNextNode(n9).value);
        System.out.println("n10 next = " + tree.getNextNode(n10).value);
        System.out.println("n11 next = " + tree.getNextNode(n11).value);
        System.out.println("n12 next = " + tree.getNextNode(n12).value);
        System.out.println("n13 next = " + tree.getNextNode(n13).value);
        System.out.println("n14 next = " + tree.getNextNode(n14).value);
        System.out.println("n15 next = " + (tree.getNextNode(n15) == null ? "null" : "not null"));
        System.out.println("n16 next = " + tree.getNextNode(n16).value);
        System.out.println("n17 next = " + tree.getNextNode(n17).value);
        System.out.println("n18 next = " + tree.getNextNode(n18).value);
        System.out.println("n19 next = " + (tree.getNextNode(n19) == null ? "null" : "not null"));


    }

    private static Node initNode() {
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
        n2.parent = n1;
        n1.right = n3;
        n3.parent = n1;

        n2.left = n4;
        n4.parent = n2;
        n2.right = n5;
        n5.parent = n2;

        n3.left = n6;
        n6.parent = n3;
        n3.right = n7;
        n7.parent = n3;

        n4.left = n8;
        n8.parent = n4;
        n4.right = n9;
        n9.parent = n4;

        n5.left = n10;
        n10.parent = n5;
        n5.right = n11;
        n11.parent = n5;

        n6.left = n12;
        n12.parent = n6;
        n6.right = n13;
        n13.parent = n6;

        n7.left = n14;
        n14.parent = n7;
        n7.right = n15;
        n15.parent = n7;

        n8.left = n16;
        n16.parent = n8;
        n8.right = n17;
        n17.parent = n8;

        n9.left = n18;
        n18.parent = n9;
        return n1;
    }
}
