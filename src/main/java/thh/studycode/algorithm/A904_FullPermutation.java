package thh.studycode.algorithm;

import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * 数组全排列
 */
public class A904_FullPermutation {

    /**
     * @param resources 数据源
     * @param used      使用过的下标
     * @param path      当前拼接成的内容
     * @param all       所有拼接成的内容
     */
    public static void permutation(String[] resources, HashSet<Integer> used, String path, HashSet<String> all) {
        if (used.size() == resources.length) {
            all.add(path);
            return;
        }
        for (int i = 0; i < resources.length; i++) {
            if (used.contains(i)) {
                continue;
            }
            used.add(i);
            permutation(resources, used, path + resources[i], all);
            //还原现场 important !!!
            used.remove(i);
        }
    }

    public static void main(String[] args) {
        String[] resources = {"Mmol", "KoD"};
        HashSet<Integer> used = new HashSet<Integer>();
        HashSet<String> all = new HashSet<String>();

        A904_FullPermutation.permutation(resources, used, "", all);
        PriorityQueue queue = new PriorityQueue<String>();
        all.forEach(i ->{
           queue.add(i);
        });
        while (queue.isEmpty() == false){
            System.out.println(queue.poll());
        }
    }

}
