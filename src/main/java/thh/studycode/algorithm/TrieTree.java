package thh.studycode.algorithm;

/**
 * 前缀树
 */
public class TrieTree {

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

        public int countPreifx(String word) {
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

        System.out.println("以a开始的有" + trie.countPreifx("a") + "个单词");
        System.out.println("以ab开始的有" + trie.countPreifx("ab") + "个单词");
        System.out.println("以abc开始的有" + trie.countPreifx("abc") + "个单词");
        System.out.println("以abab开始的有" + trie.countPreifx("abab") + "个单词");
        System.out.println("以ababc出现了" + trie.countFull("ababc") + "个单词");

        trie.delete("ababaddfas");

        System.out.println("以a开始的有" + trie.countPreifx("a") + "个单词");
        System.out.println("以ab开始的有" + trie.countPreifx("ab") + "个单词");
        System.out.println("以abc开始的有" + trie.countPreifx("abc") + "个单词");
        System.out.println("以abab开始的有" + trie.countPreifx("abab") + "个单词");
        System.out.println("以ababc出现了" + trie.countFull("ababc") + "个单词");
    }


}
