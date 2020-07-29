package thh.studycode.algorithm;

import java.util.*;

/**
 * 最小生成树算法之Prim (普里姆算法)
 * 1）
 */
public class A1107_Graph_Prim {

    static Comparator<A1102_Edge> comparator = new Comparator<A1102_Edge>() {
        @Override
        public int compare(A1102_Edge o1, A1102_Edge o2) {
            return o1.priority - o2.priority;
        }
    };


    public static List<A1102_Edge> prim(A1102_Node startNode) {
        List<A1102_Edge> edges = new ArrayList<>();
        PriorityQueue<A1102_Edge> queue = new PriorityQueue(comparator);

        HashSet<A1102_Node> selectedNode = new HashSet<>();

        selectedNode.add(startNode);
        queue.addAll(startNode.edges);

        while (queue.isEmpty() ==false){
            A1102_Edge edge = queue.poll();
            if(selectedNode.contains(edge.to) == false){
                selectedNode.add(edge.to);
                edges.add(edge);
                queue.addAll(edge.to.edges);
            }
        }
        return edges;
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

        A1102_Graph graph = A1103_GraphGenerator.generator(data);
        A1102_Node node = (A1102_Node) graph.nodeMap.get(1);
        List<A1102_Edge> edges = prim(node);
        edges.forEach(edge -> {
            System.out.println("priority:" + edge.priority + "\tfrom:" + edge.from.value + "\tto:" + edge.to.value);
        });
    }
}
