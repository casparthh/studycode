package thh.studycode.algorithm;

/**
 * 一个数组中，有一个数出现了奇数次，其它数都出现了偶数次，找出并打印该数。
 */
public class Eor {

    public static void main(String[] args) {
        int[] nums = {9,8,7,6,5,9,5,6,7,8,9};
        System.out.println(find(nums));
    }

    public static int find(int[] nums){
        int eor = 0;
        for (int i = 0; i < nums.length; i++) {
            eor ^= nums[i];
        }
        return eor;

    }
}
