package thh.studycode.algorithm;

import java.util.LinkedList;
import java.util.Queue;

public class A701_TreePrinter {

    static class Node{
        private int value;
        private Node lChild;
        private Node rChild;

        public Node(int value) {
            this.value = value;
        }
    }

    //先序打印：（头左右）任何子树的处理顺序都是，先头节点，再左子树，然后右子树
    public void printByLeft(Node node){
        if(node == null){
            return;
        }
        System.out.print(node.value + "\t");
        printByLeft(node.lChild);
        printByLeft(node.rChild);
    }

    //中序打印：（左中右）任何子树的处理顺序都是，先左子树，再头节点，然后右子树
    public void printByCenter(Node node){
        if(node == null){
            return;
        }
        printByCenter(node.lChild);
        System.out.print(node.value + "\t");
        printByCenter(node.rChild);
    }

    //后序打印：（右左中）任何子树的处理顺序都是，先左子树，再右子树，然后头节点
    public void printByRight(Node node){
        if(node == null){
            return;
        }
        printByRight(node.lChild);
        printByRight(node.rChild);
        System.out.print(node.value + "\t");
    }

    /**
     * 按层遍历
     *
     * @param node
     */
    public void loopByLevel(Node node) {
        if (node == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(node);
        while (queue.isEmpty() == false) {
            node = queue.poll();
            System.out.print(node.value + "\t");
            if (node.lChild != null) {
                queue.add(node.lChild);
            }
            if (node.rChild != null) {
                queue.add(node.rChild);
            }
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
        n1.lChild = n2;
        n1.rChild = n3;

        n2.lChild = n4;
        n2.rChild = n5;

        n3.lChild = n6;
        n3.rChild = n7;

        n4.lChild =n8;
        n4.rChild =n9;

        A701_TreePrinter treePrinter = new A701_TreePrinter();
        treePrinter.printByLeft(n1);
        System.out.println("\r\n -----------   ");

        treePrinter.printByCenter(n1);
        System.out.println("\n -----------   ");

        treePrinter.printByRight(n1);
        System.out.println("\n -----------   ");

        treePrinter.loopByLevel(n1);
        System.out.println("\r\n -----------   ");
    }
}
