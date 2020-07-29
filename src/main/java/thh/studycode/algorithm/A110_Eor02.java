package thh.studycode.algorithm;

/**
 * 一个数组中，有两个数出现了奇数次，其它数都出现了偶数次，找出并打印这两个数。
 */
public class A110_Eor02 {

    public static void main(String[] args) {
        int[] nums = {9,8,7,6,5,1,5,6,7,8,9,0};
        find(nums);
    }

    public static void find(int[] nums) {
        //假设这两个数分别是a,b，先找出a^b的值
        int eor = 0;
        for (int i = 0; i < nums.length; i++) {
            eor ^= nums[i];
        }

        //找出a^b最右一位1，说明 a 和 b其中有一个数该bit位为1，另一个该bit位为0；
        int n = eor & (~eor + 1);

        int eor1 =0;
        for (int i = 0; i < nums.length; i++) {
            //遍历数组，并按该数对应的bit位是否为1，进行区分。
            if ((nums[i] & n) != n) {
                eor1 ^= nums[i];
            }
        }
        System.out.println(eor1);
        System.out.println(eor1 ^ eor);
    }
}
