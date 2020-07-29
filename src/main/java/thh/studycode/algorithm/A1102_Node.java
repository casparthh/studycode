package thh.studycode.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 图（有向图，无向图）
 * （邻接表法，邻接矩阵法）
 */
public class A1102_Node<T> {
    T value;
    int in;     //有多少个点连向他
    int out;    //他又连向多少个点
    List<A1102_Edge> edges; //从他出去的边集合，
    List<A1102_Node> nexts;   //他出去连向的点集合 out = nexts.size

    public A1102_Node(T value) {
        this.value = value;
        in = 0;
        out = 0;
        edges = new ArrayList<A1102_Edge>();
        nexts = new ArrayList<A1102_Node>();
    }
}
