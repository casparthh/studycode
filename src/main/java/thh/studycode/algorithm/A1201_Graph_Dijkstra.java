package thh.studycode.algorithm;

import java.util.*;

/**
 * Dijkstra（迪杰特斯拉）算法
 * 用Dijkstra算法求A到图中各点的最短路径
 * todo
 */
public class A1201_Graph_Dijkstra {

    public static Map<A1102_Node, Integer> dijkstra(A1102_Node startNode) {
        Map<A1102_Node, Integer> nodeMap = new HashMap<>();
        nodeMap.put(startNode, 0);

        Queue<A1102_Node> queue = new LinkedList();
        queue.add(startNode);

        while (queue.isEmpty() == false) {
            A1102_Node curr = queue.poll();
            for (Object o : curr.edges) {
                A1102_Edge edge = (A1102_Edge) o;
                if (nodeMap.containsKey(edge.to)) {
                    nodeMap.put(edge.to, Math.min(nodeMap.get(edge.to), nodeMap.get(curr) + edge.priority));
                } else {
                    nodeMap.put(edge.to, nodeMap.get(curr)+edge.priority);
                    queue.add(edge.to);
                }
            }
        }
        return nodeMap;
    }

    public static void main(String[] args) {
        List<int[]> data = new ArrayList<>();
        int[] row1 = {7, 2, 3};
        int[] row2 = {2, 1, 3};
        int[] row3 = {4, 1, 5};
        int[] row4 = {6, 3, 5};
        int[] row5 = {8, 5, 7};
        int[] row6 = {1, 2, 7};
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
        Map<A1102_Node, Integer> map = dijkstra(node);
        map.forEach((k,v)->{
            System.out.println("node: "+ k.value+"\tdistance: "+v);
        });



    }


}
