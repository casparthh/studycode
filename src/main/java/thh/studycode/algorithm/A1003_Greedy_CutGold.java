package thh.studycode.algorithm;

import java.util.PriorityQueue;

/**
 * 贪心算法的解题套路实战
 * <p>
 * -块金条切成两半,是需要花费和长度数值一样的铜板的。
 * 比如长度为20的金条,不管怎么切,都要花费20个铜板。-群人想整分整块金条,怎么分最省铜板?
 * 例如给定数组{10,20,30},代表一共三个人,整块金条长度为60,金条要分成10,20, 30三 个部分。
 * 如果先把长度60的金条分成10和50,花费60;再把长度50的金条分成20和30,花费50;- 共花费110铜板。
 * 但如果先把长度60的金条分成30和30,花费60;再把长度30金条分成10和20,花 费30;-共花费90铜板。
 * 输入一个数组,返回分割的最小代价。
 */
public class A1003_Greedy_CutGold {



    public static int cutGold1(int[] nums){

        return 0;
    }

    /**
     * 生成小跟堆，然后弹2个合并成一个，再放进去，直到最后一个，不用了。
     * @param nums
     * @return
     */
    public static int cutGold(int[] nums) {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        for (int i = 0; i < nums.length; i++) {
            queue.add(nums[i]);
        }
        int total = 0;
        while (queue.isEmpty() == false) {
            int n1 = queue.poll();
            if (queue.isEmpty()) {
                return total;
            }
            int n2 = queue.poll();
            total += (n1 + n2);
            queue.add(n1+n2);
        }
        return total;
    }

    public static void main(String[] args) {
        int[] nums = {9,8,7,4,9,12,16};
        int[] nums1 = {1,3,4,6,9};
        System.out.println(cutGold(nums));
        System.out.println(cutGold(nums1));
    }


}
