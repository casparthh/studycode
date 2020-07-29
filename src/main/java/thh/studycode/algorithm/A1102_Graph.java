package thh.studycode.algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 *
 * 1）由点的集合与边的集合构成
 * 2）虽然存在有向图和无向图的概念，但实际上都可以用有向图来表达
 * 3）边上可能带有权值
 *
 * 图结构常见的表达法（邻接表法，邻接矩阵法，除此之外还有众多的方式...）
 */
public class A1102_Graph<T> {

    Map<T, A1102_Node<T>> nodeMap;
    HashSet<A1102_Edge> edgeSet;


    public A1102_Graph() {
        this.nodeMap = new HashMap<T, A1102_Node<T>>();
        this.edgeSet = new HashSet<A1102_Edge>();
    }


    public A1102_Node drawNodeIfNotExists(T t) {
        if (nodeMap.containsKey(t) == false) {
            A1102_Node<T> node = new A1102_Node<T>(t);
            nodeMap.put(t, node);
            return node;
        }
        return nodeMap.get(t);
    }

    public A1102_Edge drawEdge(int vlaue, A1102_Node fromNode, A1102_Node toNode) {
        A1102_Edge edge = new A1102_Edge(vlaue, fromNode, toNode);
        edgeSet.add(edge);
        return edge;
    }

    public boolean existsNote(T t) {
        return nodeMap.containsKey(t);
    }
}
