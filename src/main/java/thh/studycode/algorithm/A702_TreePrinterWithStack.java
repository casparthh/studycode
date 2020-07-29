package thh.studycode.algorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class A702_TreePrinterWithStack {

    static class Node {
        private int value;
        private Node lChild;
        private Node rChild;

        public Node(int value) {
            this.value = value;
        }
    }


    //先序打印：（头左右）任何子树的处理顺序都是，先头节点，再左子树，然后右子树
    public void printByLeft(Node node) {
        Stack<Node> stack = new Stack<Node>();
        //先将根节点压入栈 先压入右节点，再压入左节点。 【头左右】
        Node rNode = node;
        stack.push(rNode);
        while (stack.isEmpty() == false) {
            //出栈并立即打印
            Node n = stack.pop();
            System.out.print(n.value + "\t");
            //如果有右节点，先入栈
            if (n.rChild != null) {
                stack.push(n.rChild);
            }
            //如果有左节点，入栈
            if (n.lChild != null) {
                stack.push(n.lChild);
            }
        }
    }

    //中序打印：（左头右）任何子树的处理顺序都是，先左子树，再头节点，然后右子树
    public void printByCenter(Node node) {
        Stack<Node> stack = new Stack<Node>();
        //第一步先将左节点全压入栈
        Node n = node;
        while (node != null) {
            stack.push(node);
            node = node.lChild;
        }

        while (stack.isEmpty() == false) {
            //弹出一个节点并打印
            Node pNode = stack.pop();
            System.out.print(pNode.value + "\t");

            //如果弹出的节点有右节点，再执行第一步将左节点全压入栈
            if (pNode.rChild != null) {
                Node rn = pNode.rChild;
                while (rn != null) {
                    stack.push(rn);
                    rn = rn.lChild;
                }
            }

        }


    }

    //后序打印：（左右头）任何子树的处理顺序都是，先左子树，再右子树，然后头节点
    public void printByRight(Node node) {
        Stack<Node> stack = new Stack<Node>();
        Stack<Node> stack1 = new Stack<Node>();
        //先将根节点压入栈, 先压入左节点，再压入右节点。【头右左】
        Node rNode = node;
        stack.push(rNode);
        while (stack.isEmpty() == false) {
            //出栈并立即打印
            Node n = stack.pop();
            //再添加到辅助栈，
            stack1.push(n);
            //如果有左节点，入栈
            if (n.lChild != null) {
                stack.push(n.lChild);
            }
            //如果有右节点，入栈
            if (n.rChild != null) {
                stack.push(n.rChild);
            }
        }
        //辅助栈再弹出打印，即【左右头】
        while (stack1.isEmpty() == false) {
            System.out.print(stack1.pop().value + "\t");
        }
    }

    public void printeByLeve(Node node) {
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(node);
        while (queue.isEmpty() == false) {
            Node n = queue.poll();
            System.out.print(n.value + "\t");
            if (n.lChild != null) {
                queue.add(n.lChild);
            }
            if (n.rChild != null) {
                queue.add(n.rChild);
            }
        }
    }


    public int getMaxWidth(Node node) {
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(node);
        int width = 0;
        int max = 0;
        Node currEnd = node;
        Node nextRowEnd = null;
        while (queue.isEmpty() == false) {
            Node n = queue.poll();
            width++;
            if (n.lChild != null) {
                queue.add(n.lChild);
                nextRowEnd = n.lChild;
            }
            if (n.rChild != null) {
                queue.add(n.rChild);
                nextRowEnd = n.rChild;
            }

            if (n == currEnd) {
                //一行的结束了
                max = Math.max(max, width);
                width = 0;
                currEnd = nextRowEnd;
                nextRowEnd = null;
            }
        }
        return max;
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
        n1.lChild = n2;
        n1.rChild = n3;

        n2.lChild = n4;
        n2.rChild = n5;

        n3.lChild = n6;
        n3.rChild = n7;

        n4.lChild = n8;
        n4.rChild = n9;

        n5.lChild = n10;
        n5.rChild = n11;

        n6.lChild = n12;
        n6.rChild = n13;

        n7.lChild = n14;
        n7.rChild = n15;

        n8.lChild = n16;
        n8.rChild = n17;

        n9.lChild = n18;

        A702_TreePrinterWithStack treePrinter = new A702_TreePrinterWithStack();
        System.out.print("前序打印：");
        treePrinter.printByLeft(n1);
        System.out.println("");

        System.out.print("中序打印：");
        treePrinter.printByCenter(n1);
        System.out.println("");

        System.out.print("后序打印：");
        treePrinter.printByRight(n1);
        System.out.println("");

        System.out.print("按行打印：");
        treePrinter.printeByLeve(n1);
        System.out.println("");

        System.out.println("max width:" + treePrinter.getMaxWidth(n1));

    }
}
