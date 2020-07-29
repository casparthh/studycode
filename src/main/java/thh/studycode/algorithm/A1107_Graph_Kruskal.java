package thh.studycode.algorithm;

import java.util.*;

/**
 * 最小生成树算法之Kruskal (克鲁斯卡尔算法)
 * 1）总是从权最小的边开始考虑，依次考察权值依次变大的边
 * 2）当前的边要么进入最小生成树的集合，要么丢弃
 * 3）如果当前的边进入最小生成树的集合中不会形成环，就要当前边
 * 4）如果当前的边进入最小生成树的集合中会形成环，就不要当前边
 * 5）考察完所有边之后，最小生成树的集合也得到了
 */
public class A1107_Graph_Kruskal {

    static Comparator<A1102_Edge> comparator = new Comparator<A1102_Edge>() {
        @Override
        public int compare(A1102_Edge o1, A1102_Edge o2) {
            return o1.priority - o2.priority;
        }
    };

    static class Node {
        A1102_Node n;
        Node root;
        int size;

        public Node(A1102_Node n) {
            this.n = n;
            this.root = this;
            size = 1;
        }
    }

    static Map<A1102_Node, Node> nodeMap = new HashMap();

    public static Node findRoot(A1102_Node n) {
        Node node = nodeMap.get(n);
        Queue<Node> queue = new LinkedList<>();
        while (node.root != node) {
            queue.add(node);
            node = node.root;
        }
        while (queue.isEmpty()==false) {
            Node resetNode = queue.poll();
            resetNode.root = node;
        }
        return node;
    }

    public static boolean isSameSet(A1102_Node n1, A1102_Node n2) {
        if (n1 == null || n2 == null) {
            return false;
        }
        Node r1 = findRoot(n1);
        Node r2 = findRoot(n2);
        return r1 == r2;
    }

    public static void union(A1102_Node n1, A1102_Node n2) {
        if (n1 == null || n2 == null) {
            return;
        }
        Node r1 = findRoot(n1);
        Node r2 = findRoot(n2);
        if (r1 == r2) {
            return;
        }
        if(r1.size>=r2.size){
            r2.root = r1;
            r1.size += r2.size;
            r2.size = 0;
        } else {
            r1.root = r2;
            r2.size += r1.size;
            r1.size = 0;
        }
    }

    public static List<A1102_Edge> kruskal(A1102_Graph graph) {
        graph.nodeMap.forEach((k,v)->{
            A1102_Node n = (A1102_Node) v;
            nodeMap.put(n, new Node(n));
        });
        List<A1102_Edge> edges = new ArrayList<>();
        PriorityQueue<A1102_Edge> queue = new PriorityQueue(comparator);
        for (Object edge : graph.edgeSet) {
            queue.add((A1102_Edge) edge);
        }

        while (queue.isEmpty() == false) {
            A1102_Edge edge = queue.poll();
            A1102_Node from = edge.from;
            A1102_Node to = edge.to;
            if(isSameSet(from, to) == false){
                edges.add(edge);
                union(from, to);
            }
        }
        return edges;
    }

    public static void main(String[] args) {
        List<int[]> data = new ArrayList<>();
        int[] row1 = {7, 2, 3};
        int[] row2 = {20, 1, 3};
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
        List<A1102_Edge> edges = kruskal(graph);
        edges.forEach(edge -> {
            System.out.println("priority:" + edge.priority + "\tfrom:" + edge.from.value + "\tto:" + edge.to.value);
        });
    }
}
