package thh.studycode.algorithm;

import java.util.*;

/**
 * 图的拓扑排序算法
 * 1）在国中找到所有入度为0的点输出
 * 2）把所有入度为0的点在图中删掉，继续找入度为0的点输出，周而复始
 * 3）图的所有点都被删除后，依次输出的顺序就是拓扑排序
 * 要求：有向图且其中没有环
 * 应用：事件安排编译顺序
 */
public class A1106_Graph_Topology {


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

    public static List<A1102_Node> topology(A1102_Graph grath){
        Map<A1102_Node, Integer> inMap = new HashMap<>();

        Map<Integer, A1102_Node> map = grath.nodeMap;
        Queue<A1102_Node> queue = new LinkedList();
        map.forEach((k,v)->{
            if(v.in == 0){
                queue.add(v);
            } else {
                inMap.put(v, v.in);
            }
        });


        List<A1102_Node> selectedNodeList = new ArrayList<>();
        while (queue.isEmpty() == false) {
            A1102_Node curr = queue.poll();
            selectedNodeList.add(curr);
            for (Object next : curr.nexts) {
                A1102_Node nextNode = (A1102_Node) next;
                int in = inMap.get(nextNode);
                inMap.put(nextNode, --in);
                if (in == 0) {
                    queue.add(nextNode);
                }
            }
        }
        return selectedNodeList;
    }

    public static void main(String[] args) {
        List<int[]> data = new ArrayList<>();
        int[] row1 = {7, 1, 2};
        int[] row2 = {2, 2, 3};
        int[] row3 = {4, 3, 4};
        int[] row4 = {6, 4, 5};
        int[] row5 = {8, 5, 6};
        int[] row6 = {5, 1, 3};
        int[] row7 = {5, 2, 4};
        int[] row8 = {5, 3, 5};
        int[] row9 = {5, 4, 6};
        data.add(row1);
        data.add(row2);
        data.add(row3);
        data.add(row4);
        data.add(row5);
        data.add(row6);
        data.add(row7);
        data.add(row8);
        data.add(row9);

        A1102_Graph graph = generator(data);
        List<A1102_Node> list = topology(graph);
        for (A1102_Node node : list) {
            System.out.println(node.value);
        }

    }
}
