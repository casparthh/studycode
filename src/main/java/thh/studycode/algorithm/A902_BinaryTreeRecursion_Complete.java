package thh.studycode.algorithm;

import lombok.AllArgsConstructor;
import org.apache.commons.lang.math.RandomUtils;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 完全二叉树
 */
public class A902_BinaryTreeRecursion_Complete {

    static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 1. 有右无左 返回 false
     * 2. 遇到第一个左右孩子不双全的节点，后续遇到的所有节点，都必须是叶子节点 （没有子节点）
     *
     * @param node
     * @return
     */
    public boolean isCompleteBinaryTreeByQueue(Node node) {
        if (node == null) {
            return true;
        }
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(node);

        boolean leap = false;
        while (queue.isEmpty() == false) {
            Node n = queue.poll();
            if (n.left != null) {
                queue.add(n.left);
            }
            if (n.right != null) {
                queue.add(n.right);
            }
            if (leap == true && (n.left != null || n.right != null)) {
                //后续遇到的所有节点，都必须是叶子节点，否则返回false
                return false;

            } else if (n.left == null && n.right != null) {
                //有右无左 返回 false
                return false;

            } else if (n.left == null || n.right == null) {
                //遇到第一个左右孩子不双全的节点, 标记leap
                leap = true;
            }
        }
        return true;
    }

    @AllArgsConstructor
    static class Info {
        boolean isCBT;
        boolean isFBT;
        int level;
    }

    /**
     * 1. 整个就是满二叉树即左右都是满的，且高度一致
     * 2. 左边是完全二叉树，且右边是满二叉树，且左边level比右边大1
     * 3. 如果左右都是满二叉树，且level相等，或左边level比右边大1
     * 4. 如果左边是满二叉树，右边是完二叉树，且高度一致
     *
     * @return
     */
    public Info isCompleteBinaryTreeByRecursion(Node node) {
        if (node == null) {
            return new Info(true, true, 0);
        }

        Info linfo = isCompleteBinaryTreeByRecursion(node.left);
        Info rinfo = isCompleteBinaryTreeByRecursion(node.right);
        if (linfo.isFBT && rinfo.isFBT && linfo.level == rinfo.level) {
            return new Info(true, true, linfo.level + 1);

        } else if (linfo.isFBT && rinfo.isFBT && linfo.level - rinfo.level == 1) {
            return new Info(true, false, linfo.level + 1);

        } else if (linfo.isCBT && rinfo.isFBT && linfo.level - rinfo.level == 1) {
            return new Info(true, false, linfo.level + 1);

        } else if (linfo.isFBT && rinfo.isCBT && linfo.level == rinfo.level) {
            return new Info(true, false, linfo.level + 1);

        } else {
            return new Info(false, false, Math.max(linfo.level, rinfo.level) + 1);
        }
    }

    public static void main(String[] args) {
        A902_BinaryTreeRecursion_Complete completeTree = new A902_BinaryTreeRecursion_Complete();
        int maxLevl = 5;
        int maxValue = 1000;
        int testTimes = 10000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generate(1, maxLevl,maxValue);
            if(completeTree.isCompleteBinaryTreeByQueue(head)  != completeTree.isCompleteBinaryTreeByRecursion(head).isCBT){
                System.out.println("Oops!");
            }
        }
        System.out.println("VERY GOOD!");
    }


    public static Node generate(int level, int maxLevel, int maxValue){
        if(level> maxLevel || RandomUtils.nextInt(100)< 5){
            return null;
        }
        Node head = new Node(RandomUtils.nextInt(maxValue));
        Node left = generate(level+1, maxLevel, maxValue);
        Node right = generate(level+1, maxLevel, maxValue);
        head.left = left;
        head.right = right;
        return head;
    }

}
