package thh.studycode.algorithm;


/**
 * 二叉树的递归套路
 * 1)假设以X节点为头，假设可以向X左树和X右树要任何信息
 * 2)在上一步的假设下，讨论以X为头节点的树，得到答案的可能性(最重要)
 * 3)列出所有可能性后，确定到底需要向左树和右树要什么样的信息
 * 4)把左树信息和右树信息求全集，就是任何一棵子树都需要返回的信息S
 * 5)递归函数都返回S，每一棵子树都这么要求
 * 6)写代码，在代码中考虑如何把左树的信息和右树信息整合出整棵树的信息
 */
public class A804_BinaryTreeRecursion_Distance {

    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }


    public class DistanceInfo {
        int maxDistance;
        int level;

        public DistanceInfo(int maxDistance, int level) {
            this.maxDistance = maxDistance;
            this.level = level;
        }
    }

    /**
     * 给定一棵二叉树的头节点head,任何两个节点之间都存在距离，返回整棵二叉树的最大距离
     */
    public DistanceInfo getDistance(Node node) {
        if (node == null) {
            return new DistanceInfo(0, 0);
        }
        DistanceInfo linfo = getDistance(node.left);
        DistanceInfo rinfo = getDistance(node.right);

        int maxDistance = Math.max(linfo.maxDistance, rinfo.maxDistance);
        maxDistance = Math.max(maxDistance, linfo.level+rinfo.level+1);
        return new DistanceInfo(maxDistance, Math.max(linfo.level, rinfo.level)+1);
    }

    public static void main(String[] args) {
        A804_BinaryTreeRecursion_Distance tree = new A804_BinaryTreeRecursion_Distance();
        Node n1 = initNode();


        //给定一棵二叉树的头节点head,任何两个节点之间都存在距离，返回整棵二叉树的最大距离
        DistanceInfo dinfo = tree.getDistance(n1.left);
        System.out.println("最大的距离是："+dinfo.maxDistance);

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
        n1.right = n3;

        n2.left = n4;
        n2.right = n5;

        n3.left = n6;
        n3.right = n7;

        n4.left = n8;
        n4.right = n9;

        n5.left = n10;
        n5.right = n11;

        n6.left = n12;
        n6.right = n13;

        n7.left = n14;
        n7.right = n15;

        n8.left = n16;
        n8.right = n17;

        n11.left = n18;
        return n1;
    }
}
