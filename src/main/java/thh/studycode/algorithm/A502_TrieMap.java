package thh.studycode.algorithm;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * 前缀树
 * 1）单个字符串中，字符从前到后的加到一棵多叉树上
 * 2）字符放在路上，节点上有专属的数据项（常见的是pass和end值）
 * 3）所有样本都这样添加，如果没有路就新建，如有路就复用
 * 4）沿途节点的pass值增加1，每个字符串结束时来到的节点end值增加1
 */
public class A502_TrieMap {

    @Getter
    @Setter
    class Node {
        private int pass;
        private int end;
        private Map<Character, Node> map = new HashMap<>();
    }

    private Node root;

    public A502_TrieMap() {
        root = new Node();
    }

    public void insert(String keyword) {
        char[] chars = keyword.toCharArray();
        Node curr = root;
        curr.pass++;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (curr.map.containsKey(c) == false) {
                curr.map.put(c, new Node());
            }
            curr = curr.map.get(c);
            curr.pass++;
            if (i == chars.length - 1) {
                curr.end++;
            }
        }
    }

    public void delete(String keyword) {
        if (countFull(keyword) > 0) {
            char[] chars = keyword.toCharArray();
            Node curr = root;
            curr.pass--;
            for (int i = 0; i < chars.length; i++) {
                char c = chars[i];

                //如果只有最后一个pass，直接删除子节点树。
                if (curr.map.get(c).pass == 1) {
                    curr.map.remove(c);
                    return;
                }

                curr = curr.map.get(c);
                curr.pass--;
                if (i == chars.length - 1) {
                    curr.end--;
                }
            }
        }
    }

    public int countFull(String keyword) {
        char[] chars = keyword.toCharArray();
        Node curr = root;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if(curr.map.containsKey(c)){
                curr = curr.map.get(c);
                if(i == chars.length -1){
                    return curr.end;
                }
            } else {
                return 0;
            }
        }
        return 0;
    }

    public int countPrefix(String keyword) {
        char[] chars = keyword.toCharArray();
        Node curr = root;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if(curr.map.containsKey(c)){
                curr = curr.map.get(c);
                if(i == chars.length -1){
                    return curr.pass;
                }
            } else {
                return 0;
            }
        }
        return 0;
    }


    public static void main(String[] args) {
        A501_Trie.Trie trie = new A501_Trie.Trie();
        A502_TrieMap trieMap = new A502_TrieMap();
        trie.insert("abcd");
        trie.insert("abab");
        trie.insert("abdeg");
        trie.insert("ababcde");
        trie.insert("ababada");
        trie.insert("ababaddfas");
        trie.insert("ababc");
        trie.insert("ababcd");

        trieMap.insert("abcd");
        trieMap.insert("abab");
        trieMap.insert("abdeg");
        trieMap.insert("ababcde");
        trieMap.insert("ababada");
        trieMap.insert("ababaddfas");
        trieMap.insert("ababc");
        trieMap.insert("ababcd");

        System.out.println("以a开始的有" + trie.countPrefix("a") + "个单词");
        System.out.println("以ab开始的有" + trie.countPrefix("ab") + "个单词");
        System.out.println("以abc开始的有" + trie.countPrefix("abc") + "个单词");
        System.out.println("以abab开始的有" + trie.countPrefix("abab") + "个单词");
        System.out.println("ababc出现了" + trie.countFull("ababc") + "个单词");

        System.out.println("----Map-----以a开始的有" + trieMap.countPrefix("a") + "个单词");
        System.out.println("----Map-----以ab开始的有" + trieMap.countPrefix("ab") + "个单词");
        System.out.println("----Map-----以abc开始的有" + trieMap.countPrefix("abc") + "个单词");
        System.out.println("----Map-----以abab开始的有" + trieMap.countPrefix("abab") + "个单词");
        System.out.println("----Map-----ababc出现了" + trieMap.countFull("ababc") + "个单词");

        trie.delete("ababaddfas");
        trieMap.delete("ababaddfas");

        System.out.println("以a开始的有" + trie.countPrefix("a") + "个单词");
        System.out.println("以ab开始的有" + trie.countPrefix("ab") + "个单词");
        System.out.println("以abc开始的有" + trie.countPrefix("abc") + "个单词");
        System.out.println("以abab开始的有" + trie.countPrefix("abab") + "个单词");
        System.out.println("ababc出现了" + trie.countFull("ababc") + "个单词");

        System.out.println("----Map-----以a开始的有" + trieMap.countPrefix("a") + "个单词");
        System.out.println("----Map-----以ab开始的有" + trieMap.countPrefix("ab") + "个单词");
        System.out.println("----Map-----以abc开始的有" + trieMap.countPrefix("abc") + "个单词");
        System.out.println("----Map-----以abab开始的有" + trieMap.countPrefix("abab") + "个单词");
        System.out.println("----Map-----ababc出现了" + trieMap.countFull("ababc") + "个单词");

        System.out.println("done");
    }


}
