package thh.studycode.algorithm;

import java.util.*;

/**
 * 图的深度性优先遍历
 * 1.利用栈实现,新节点入栈即打印
 * 2.从源节点开始依次按照深度进栈，然后弹出
 * 3.每弹出一个，把该节点下一个没有进过栈的邻接点同弹出的节点一起放入栈
 * 4.直到栈变空
 */
public class A1105_Graph_DFS {

    /*
    将下面图数据转划成为自定义Graph，然后进行图的深度性优先遍历
     [7,2,3]
     [2,1,3]
     [4,1,5]
     [6,3,5]
     [8,5,7]
     [5,2,7]
     */

    public static A1102_Graph<Integer> generator(List<int[]> data) {
        A1102_Graph<Integer> graph = new A1102_Graph<Integer>();
        if (data == null || data.size() == 0) {
            return graph;
        }

        for (int i = 0; i < data.size(); i++) {
            int[] item = data.get(i);
            int priority = item[0];
            int from = item[1];
            int to = item[2];
            A1102_Node fromNode = graph.drawNodeIfNotExists(from);
            A1102_Node toNode = graph.drawNodeIfNotExists(to);
            A1102_Edge edge = graph.drawEdge(priority, fromNode, toNode);

            fromNode.out++;
            toNode.in++;
            if (fromNode.nexts.contains(toNode) == false) {
                fromNode.nexts.add(toNode);
            }

            fromNode.edges.add(edge);
        }
        return graph;
    }

    public static void printByDepth(A1102_Graph graph, int start) {
        if (graph == null || graph.nodeMap.containsKey(start) == false) {
            return;
        }
        Stack<A1102_Node> stack = new Stack();
        HashSet<A1102_Node> printedNode = new HashSet<A1102_Node>();

        A1102_Node root = (A1102_Node) graph.nodeMap.get(start);
        stack.add(root);
        printedNode.add(root);

        System.out.println("node: "+ root.value);

        while (stack.isEmpty() == false) {
            A1102_Node node = (A1102_Node)stack.pop();
            for (Object item : node.nexts) {
                A1102_Node n = (A1102_Node)item;

                if(printedNode.contains(n)==false){
                    System.out.println("node: "+ n.value);

                    printedNode.add(n);
                    stack.push(node);
                    stack.push(n);
                    break;
                }
            }

        }

    }

    public static void main(String[] args) {
        List<int[]> data = new ArrayList<>();
        int[] row1 = {7, 2, 3};
        int[] row2 = {2, 1, 3};
        int[] row3 = {4, 1, 5};
        int[] row4 = {6, 3, 5};
        int[] row5 = {8, 5, 7};
        int[] row6 = {5, 2, 7};
        int[] row7 = {5, 5, 2};
        data.add(row1);
        data.add(row2);
        data.add(row3);
        data.add(row4);
        data.add(row5);
        data.add(row6);
        data.add(row7);

        A1102_Graph graph = generator(data);
        printByDepth(graph,1);
//        graph.nodeMap.forEach((key, node) -> {
//            A1102_Node n = (A1102_Node) node;
//            System.out.println("node value: " + key + "\tnode in: " + n.in + "\tout: " + n.out);
//        });
//
//        Iterator<A1102_Edge> iter = graph.edgeSet.iterator();
//        while (iter.hasNext()) {
//            A1102_Edge edge = iter.next();
//            System.out.println("edge priority: " + edge.priority + "\tfrom: " + edge.from.value + "\tto: " + edge.to.value);
//        }
    }
}
