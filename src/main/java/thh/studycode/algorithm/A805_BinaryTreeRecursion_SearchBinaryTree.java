package thh.studycode.algorithm;


/**
 * 平衡二叉树，又称AVL树，
 * 指的是左子树上的所有节点的值都比根节点的值小，
 * 而右子树上的所有节点的值都比根节点的值大，
 * 且左子树与右子树的高度差最大为1。
 * 因此，平衡二叉树满足所有二叉排序（搜索）树的性质。
 */
public class A805_BinaryTreeRecursion_SearchBinaryTree {

    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }


    public static class Info {
        boolean isSearchBinaryTree;
        int min;
        int max;
        int size;

        public Info(boolean isSearchBinaryTree, int min, int max, int size) {
            this.isSearchBinaryTree = isSearchBinaryTree;
            this.min = min;
            this.max = max;
            this.size = size;
        }
    }

    /**
     * 给定一棵二叉树的头节点head，返回这棵二叉树中最大的二叉搜索子树的头节点
     * 最大的二叉搜索子树：
     * 左子树上的所有节点的值都比根节点的值小，
     * 而右子树上的所有节点的值都比根节点的值大，
     *
     * @param node
     * @return
     */
    public Info findSearchBinaryTree(Node node) {
        if (node == null) {
            return new Info(true, 0, 0, 0);
        }

        Info linfo = findSearchBinaryTree(node.left);
        Info rinfo = findSearchBinaryTree(node.right);
        Info currInfo = null;

        if (linfo.isSearchBinaryTree && rinfo.isSearchBinaryTree) {
            if (linfo.size == 0 && rinfo.size == 0) {
                currInfo = new Info(true, node.value, node.value, 1);

            } else if (linfo.size == 0 && node.value < rinfo.min) {
                currInfo = new Info(true, node.value, rinfo.max, rinfo.size + 1);

            } else if (rinfo.size == 0 && linfo.max < node.value) {
                currInfo = new Info(true, linfo.min, node.value, linfo.size + 1);

            } else if (linfo.max < node.value && node.value < rinfo.min) {
                currInfo = new Info(true, linfo.min, rinfo.max, linfo.size + rinfo.size + 1);
            } else {
                currInfo = linfo.size > rinfo.size ? linfo : rinfo;
                currInfo.isSearchBinaryTree = false;
            }
        } else {
            currInfo = linfo.size > rinfo.size ? linfo : rinfo;
            currInfo.isSearchBinaryTree = false;
        }
        return currInfo;
    }


    public static void main(String[] args) {
        A805_BinaryTreeRecursion_SearchBinaryTree searchBinaryTree = new A805_BinaryTreeRecursion_SearchBinaryTree();
        Node n1 = initNode();

        Info info = searchBinaryTree.findSearchBinaryTree(n1);
        System.out.println("size:" + info.size);
        System.out.println("min:" + info.min);
        System.out.println("max:" + info.max);
    }

    private static Node initNode() {
        Node n0 = new Node(0);
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

        n7.left = n3;
        n7.right = n8;
        n3.left = n1;
        n3.right = n5;
        n8.right = n9;
        n9.right = n10;
        n10.right = n11;
        n11.right = n12;

//        n5.left = n6;
//        n10.left = n13;
//
//        n1.left = n0;
//        n1.right = n2;

        return n7;
    }
}
