package thh.studycode.algorithm;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;

import java.util.*;

/**
 * 贪心算法
 *
 * 1)最自然智慧的算法
 * 2)用一种局部最功利的标准，总是做出在当前看来是最好的选择
 * 3)难点在于证明局部最功利的标准可以得到全局最优解
 * 4)对于贪心算法的学习主要以增加阅历和经验为主
 */
public class A905_Greedy_StringAppend {


    public static Comparator<String> comparator = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            return (o1 + o2).compareTo(o2 + o1);
        }
    };

    /**
     * 贪心算法找出数组中所有字符串相加最小的组合。
     *
     * 给定一个由字符串组成的数组resources,
     * 必须把所有的字符串拼接起来
     * 返回所有可能的拼接结果中，字典序最小的结果
     *
     * @param resources
     * @return
     */
    public static String findLowestByGreedy(String[] resources) {
        if (resources == null || resources.length == 0) {
            return null;
        }
        Arrays.sort(resources, comparator);

        String path = "";
        for (int i = 0; i < resources.length; i++) {
            path += resources[i];
        }
        return path;
    }

    /**
     * 全排列算法找出数组中所有字符串相加最小的组合。
     *
     * @param resources
     * @return
     */
    public static String findLowestByFullPermutation(String[] resources) {
        if (resources == null || resources.length == 0) {
            return null;
        }
        Set<Integer> used = new HashSet<Integer>();
        String path = "";
        Set<String> all = new HashSet<String>();
        findLowestByFullPermutation(resources, used, path, all);
        PriorityQueue<String> queue = new PriorityQueue<String>();
        all.forEach(i->queue.add(i));
        return queue.poll();
    }

    public static void findLowestByFullPermutation(String[] resources, Set<Integer> used, String path, Set<String> all) {
        if (resources.length == used.size()) {
            all.add(path);
            return;
        }
        for (int i = 0; i < resources.length; i++) {
            if (used.contains(i)) {
                continue;
            }
            used.add(i);
            findLowestByFullPermutation(resources, used, path+resources[i], all);
            used.remove(i);
        }
    }

    public static void main(String[] args) {
        int times = 10000;
        for (int i = 0; i < times; i++) {
            int len = RandomUtils.nextInt(6)+1;
            String[] arr = new String[len];
            for (int j = 0; j < len; j++) {
                String str = RandomStringUtils.randomAlphanumeric(RandomUtils.nextInt(6)+1);
                arr[j] = str;
            }
            String permutation = findLowestByFullPermutation(arr);
            String greedy = findLowestByGreedy(arr);
            if(permutation == null && greedy == null){
                continue;
            }
            if ((permutation != null && greedy == null) || (permutation == null && greedy != null) || permutation.equals(greedy) == false) {
                System.out.println("Oops!");
            }
        }
        System.out.println("VERY GOOD!");
    }

}
