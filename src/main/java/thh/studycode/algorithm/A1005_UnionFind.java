package thh.studycode.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 并查集
 * <p>
 * 1) 有若干个样本a、b、c、d.类型假设是V
 * 2)在并查集中-开始认为每个样本都在单独的集合里
 * 3)用户可以在任何时候调用如下两个方法:
 * boolean isSameSet(V x, V y) :查询样本x和样本y是否属于- -个集合
 * void union(V x, V y):把x和y各自所在集合的所有样本合并成一个集合
 * 4) isSameSet和union方 法的代价越低越好
 */
public class A1005_UnionFind {

    static class Node {
        String value;
        int size;
        Node root;

        public Node(String value) {
            this.value = value;
            this.root = this;
            this.size = 1;
        }
    }

    static Map<String, Node> nodeMap = new HashMap<String, Node>();

    public static void init() {
        for (int i = 0; i < 10; i++) {
            Node n = new Node(i + "");
            nodeMap.put(i + "", n);
        }
    }

    public static boolean isSameSet(String a, String b) {
        if (nodeMap.containsKey(a) == false || nodeMap.containsKey(b) == false) {
            return false;
        }
        Node na = findRoot(nodeMap.get(a));
        Node nb = findRoot(nodeMap.get(b));
        return na == nb;
    }

    /**
     * 通过找root时
     * @param node
     * @return
     */
    public static Node findRoot(Node node) {
        List<Node> nodeList = new ArrayList<>();
        while (node != node.root) {
            nodeList.add(node);
            node = node.root;
        }
        for (Node n : nodeList) {
            if(n.root != node){
                n.root = node;
                n.size=0;
                node.size++;
            }
        }
        return node;
    }

    public static void union(String a, String b) {
        if (nodeMap.containsKey(a) == false || nodeMap.containsKey(b) == false) {
            return;
        }
        Node na = findRoot(nodeMap.get(a));
        Node nb = findRoot(nodeMap.get(b));
        if (na != nb) {
            if (na.size >= nb.size) {
                nb.root = na;
                na.size += nb.size;
                nb.size = 0;
            } else {
                na.root = nb;
                nb.size += na.size;
                na.size = 0;
            }
        }
    }

    public static void main(String[] args) {
        init();
        union("1", "2");
        System.out.println(isSameSet("1", "2"));

        union("1", "4");
        System.out.println(isSameSet("1", "4"));

        union("2", "6");
        System.out.println(isSameSet("1", "6"));

        System.out.println(isSameSet("4", "6"));
    }

}
