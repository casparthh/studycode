package thh.studycode.algorithm;

/**
 * 前缀树
 * 1）单个字符串中，字符从前到后的加到一棵多叉树上
 * 2）字符放在路上，节点上有专属的数据项（常见的是pass和end值）
 * 3）所有样本都这样添加，如果没有路就新建，如有路就复用
 * 4）沿途节点的pass值增加1，每个字符串结束时来到的节点end值增加1
 */
public class A501_Trie {

    public static class Node {
        public int pass;
        public int end;
        public Node[] nodes;

        public Node() {
            this.pass = 0;
            this.end = 0;
            this.nodes = new Node[26];
        }
    }

    public static class Trie {
        public Node root;

        public Trie() {
            this.root = new Node();
        }

        public void insert(String word) {
            if (word == null || word.trim().isEmpty()) {
                return;
            }
            char[] letters = word.toCharArray();
            Node node = root;
            Node child = null;
            root.pass++;
            for (int i = 0; i < letters.length; i++) {
                int path = letters[i] - 'a';
                child = node.nodes[path];
                if (child == null) {
                    child = new Node();
                    node.nodes[path] = child;
                }
                child.pass++;
                node = child;
            }
            child.end++;
        }

        public int countFull(String word) {
            if (word == null || word.trim().isEmpty()) {
                return -1;
            }

            char[] letters = word.toCharArray();
            Node node = root;
            for (int i = 0; i < letters.length; i++) {
                int path = letters[i] - 'a';
                node = node.nodes[path];
            }
            return node.end;
        }

        public int countPrefix(String word) {
            if (word == null || word.trim().isEmpty()) {
                return -1;
            }
            char[] letters = word.toCharArray();
            Node node = root;
            for (int i = 0; i < letters.length; i++) {
                int path = letters[i] - 'a';
                node = node.nodes[path];
            }
            return node.pass;
        }

        public void delete(String word) {
            if (word == null || word.trim().isEmpty() || this.countFull(word) <= 0) {
                return;
            }
            char[] letters = word.toCharArray();
            Node node = root;
            Node child = null;
            root.pass--;
            for (int i = 0; i < letters.length; i++) {
                int path = letters[i] - 'a';
                child = node.nodes[path];
                child.pass--;
                if(child.pass == 0){
                    node.nodes[path] = null;
                    return;
                }
                node = child;
            }
            child.end--;
        }
    }


    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("abcd");
        trie.insert("abab");
        trie.insert("abdeg");
        trie.insert("ababcde");
        trie.insert("ababada");
        trie.insert("ababaddfas");
        trie.insert("ababc");
        trie.insert("ababc");
        System.out.println(trie.root);

        System.out.println("以a开始的有" + trie.countPrefix("a") + "个单词");
        System.out.println("以ab开始的有" + trie.countPrefix("ab") + "个单词");
        System.out.println("以abc开始的有" + trie.countPrefix("abc") + "个单词");
        System.out.println("以abab开始的有" + trie.countPrefix("abab") + "个单词");
        System.out.println("以ababc出现了" + trie.countFull("ababc") + "个单词");

        trie.delete("ababaddfas");

        System.out.println("以a开始的有" + trie.countPrefix("a") + "个单词");
        System.out.println("以ab开始的有" + trie.countPrefix("ab") + "个单词");
        System.out.println("以abc开始的有" + trie.countPrefix("abc") + "个单词");
        System.out.println("以abab开始的有" + trie.countPrefix("abab") + "个单词");
        System.out.println("以ababc出现了" + trie.countFull("ababc") + "个单词");
    }


}
