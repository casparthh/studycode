package thh.studycode.algorithm;

import lombok.AllArgsConstructor;
import org.apache.commons.lang.math.RandomUtils;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 满二叉树  总节点数 等于 (2^level-1)
 */
public class A901_BinaryTreeRecursion_Full {

    static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 队列实现 满二叉树判断
     *
     * @param node
     * @return
     */
    public boolean isFullBinaryTreeByQueue(Node node) {
        if (node == null) {
            return true;
        }
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(node);
        int lastRowSize = 0;
        int currRowSize = 0;
        Node lastEndNode = node;
        Node currEndNode = null;
        while (queue.isEmpty() == false) {
            Node n = queue.poll();
            currRowSize++;
            if (n.left != null) {
                queue.add(n.left);
                currEndNode = n.left;
            }
            if (n.right != null) {
                queue.add(n.right);
                currEndNode = n.right;
            }
            if (n == lastEndNode) {
                lastEndNode = currEndNode;
                if (lastRowSize > 0 && currRowSize >> 1 != lastRowSize) {
                    return false;
                }
                lastRowSize = currRowSize;
                currRowSize = 0;
                currEndNode = null;
            }
        }
        return true;
    }

    @AllArgsConstructor
    static class Info {
        boolean isFBT;
        int level;
    }

    /**
     * 递归实现 满二叉树判断
     *
     * @param node
     * @return
     */
    public Info isFullBinaryTreeByRecursion(Node node) {
        if (node == null) {
            return new Info(true, 0);
        }

        Info linfo = isFullBinaryTreeByRecursion(node.left);
        Info lright = isFullBinaryTreeByRecursion(node.right);
        if (linfo.isFBT && lright.isFBT && linfo.level == lright.level) {
            return new Info(true, linfo.level + 1);
        } else {
            return new Info(false, linfo.level + 1);
        }
    }

    public static void main(String[] args) {
        A901_BinaryTreeRecursion_Full full = new A901_BinaryTreeRecursion_Full();

        int maxLevl = 10;
        int maxValue = 1000;
        int testTimes = 10000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generate(1, maxLevl,maxValue);
            if(full.isFullBinaryTreeByQueue(head)  != full.isFullBinaryTreeByRecursion(head).isFBT){
                System.out.println("Oops!");
            }
        }
        System.out.println("VERY GOOD!");
    }


    public static Node generate(int level, int maxLevel, int maxValue){
        if(level> maxLevel || RandomUtils.nextInt(10)< 4){
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
