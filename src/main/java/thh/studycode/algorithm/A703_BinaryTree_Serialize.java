package thh.studycode.algorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 完全二叉树:
 * 从上到下，从左到右
 * 左边的子节点：2*i+1
 * 右边的子节点：2*i+2
 * 子节点求父节点：(i-1)/2
 * <p>
 * 如果从1开始计算
 * 左边的子节点：2*i
 * 右边的子节点：2*i+1
 * 左边的子节点求父节点：i/2
 * 由于频繁的计算*2, 如是从1开始计算，可以用位移操作，减少计算。
 * <p>
 * 堆是在二叉树的基础之上，实现的大根堆，或小根堆， 提到堆的时候必段是大根堆或小根堆，不存在别的
 * <p>
 * 大根堆，每一棵子树的最大值，都是头节点的值。
 * 大根堆，每一棵子树的最小值，都是头节点的值。
 * <p>
 * <p>
 * 二叉树
 * 实现前序，中序，后序遍历
 */
public class A703_BinaryTree_Serialize {

    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public void prePrint(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.value + "\t");
        prePrint(node.left);
        prePrint(node.right);
    }

    public void midPrint(Node node) {
        if (node == null) {
            return;
        }
        midPrint(node.left);
        System.out.print(node.value + "\t");
        midPrint(node.right);
    }

    public void postPrint(Node node) {
        if (node == null) {
            return;
        }
        postPrint(node.left);
        postPrint(node.right);
        System.out.print(node.value + "\t");
    }

    public void prePrintWithStack(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<Node>();
        stack.push(node);
        while (stack.isEmpty() == false) {
            node = stack.pop();
            System.out.print(node.value + "\t");
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    /**
     * 不用递归,用1个Stack中序打印(左中右)
     *
     * @param node
     */
    public void midPrintWithStack(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<Node>();
        stack.push(node);

        while (stack.isEmpty() == false) {
            if (node.left != null) {
                //先将左边的入栈
                node = node.left;
                stack.push(node);
            } else {
                //如果没有左边了，弹出一个并打印，先打印了'左'，当前栈顶即为'中'
                node = stack.pop();
                System.out.print(node.value + "\t");
                if (node.right != null) {
                    //当打印了左边，则判断
                    node = node.right;
                    stack.push(node);
                }
            }
        }
    }

    public void postPrintWithStack(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<Node>();
        Stack<Node> help = new Stack<Node>();
        stack.push(node);
        while (stack.isEmpty() == false) {
            node = stack.pop();
            help.push(node);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        while (help.isEmpty() == false) {
            System.out.print(help.pop().value + "\t");
        }
    }

    public void postPrintWithStack1(Node node) {
        //todo
    }


    /**
     * 统计二叉树最大的宽度
     * 首先将根节点做为第一层加入队列，然后poll第一层，同时将第二层的节点入队并计数。并和第一层的数量进行对比，找出当前最大值。
     * 再将poll第二层,同时将第三层的节点入队并计数。。并和第二层的数量进行对比，找出当前最大值。
     * while中重复以上步骤。直到队列为空，说明已经没有下一层了。返回最大值。
     *
     * @param node
     */
    public int countMaxWidth(Node node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null || node.right == null) {
            return 1;
        }
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(node);
        int maxWidth = 1;
        int lastWidth = 1;
        int currentLevelWidth = 0;


        while (queue.isEmpty() == false) {
            for (int i = 0; i < lastWidth; i++) {
                node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                    currentLevelWidth++;
                }
                if (node.right != null) {
                    queue.add(node.right);
                    currentLevelWidth++;
                }
            }
            lastWidth = currentLevelWidth;
            currentLevelWidth = 0;
            maxWidth = Math.max(maxWidth, lastWidth);
        }
        return maxWidth;
    }

    /**
     * 前序---序列化一个二叉树
     *
     * @param node
     */
    public Queue<Integer> serialize(Node node) {
        Queue<Integer> queue = new LinkedList<Integer>();
        serialize(queue, node);
        return queue;
    }

    public void serialize(Queue queue, Node node) {
        if (node == null) {
            queue.add(null);
            return;
        }
        queue.add(node.value);
        serialize(queue, node.left);
        serialize(queue, node.right);
    }

    /**
     * 前序---返序列化一个二叉树
     *
     * @param queue
     * @return
     */
    public Node deserialize(Queue<Integer> queue) {
        if (queue == null || queue.isEmpty()) {
            return null;
        }
        Integer num = queue.poll();
        if (num == null) {
            return null;
        }
        Node head = new Node(num);
        head.left = deserialize(queue);
        head.right = deserialize(queue);
        return head;
    }

    public static void main(String[] args) {
        A703_BinaryTree_Serialize tree = new A703_BinaryTree_Serialize();
        Node n1 = initNode();
        //1	2	4	8	9	5	10	11	3	6	12	13	7	14	15
        System.out.println("前序打印……");
        tree.prePrint(n1);
        System.out.println("\n不用递归,用1个Stack前序打印……");
        tree.prePrintWithStack(n1);

        //8	4	9	2	10	5	11	1	12	6	13	3	14	7	15
        System.out.println("\n\n中序打印……");
        tree.midPrint(n1);
        System.out.println("\n不用递归,用1个Stack中序打印……");
        tree.midPrintWithStack(n1);

        //8	9	4	10	11	5	2	12	13	6	14	15	7	3	1
        System.out.println("\n\n后序打印……");
        tree.postPrint(n1);
        System.out.println("\n不用递归,用2个Stack后序打印……");
        tree.postPrintWithStack(n1);
//        System.out.println("\n不用递归,用1个Stack后序打印……");
//        tree.postPrintWithStack(n1);

        System.out.println("\n\n找出二叉树最宽的值……");
        System.out.println(tree.countMaxWidth(n1));

        System.out.println("\n\n前序序列化二叉树……");
        Queue<Integer> queue = tree.serialize(n1);
//        while (queue.isEmpty() == false){
//            System.out.print(queue.poll()+"\t");
//        }

        System.out.println("\n\n前序反序列化二叉树……");
        Node root = tree.deserialize(queue);
        System.out.println("前序打印……");
        tree.prePrint(root);
        System.out.println();


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
        return n1;
    }
}
