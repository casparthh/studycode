package thh.studycode.algorithm;

/**
 * 图（有向图，无向图）
 * （邻接表法，邻接矩阵法）
 */
public class A1102_Edge {

    int priority;
    A1102_Node from;
    A1102_Node to;

    public A1102_Edge(int priority, A1102_Node from, A1102_Node to) {
        this.priority = priority;
        this.from = from;
        this.to = to;
    }

    public A1102_Edge() {
    }
}
