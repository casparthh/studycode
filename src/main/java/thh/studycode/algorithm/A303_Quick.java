package thh.studycode.algorithm;


/**
 * 给定一个数组，和一个整数，把小于等于num的数放在数组的左边，大于num的数放在数组右边。
 * 额外空间复杂度O(1), 时间复杂度O(n)
 */
public class A303_Quick {

    public static void main(String[] args) {
        int[] nums = {1, 2, 4, 7, 3, 1, 8, 9, 4, 2, 5, 7, 3};
        partition(nums, 3);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + "\t");
        }
    }


    public static void partition(int[] nums, int n) {
        int lPoint = 0;
        int rPoint = nums.length - 1;
        while (lPoint < rPoint) {
            if (nums[lPoint] <= n) {
                lPoint++;
            }
            if (nums[lPoint] > n && lPoint != rPoint) {
                swap(nums, lPoint, rPoint--);
            }
        }
    }

    public static void swap(int[] nums, int res, int des) {
        nums[res] = nums[res] ^ nums[des];
        nums[des] = nums[res] ^ nums[des];
        nums[res] = nums[res] ^ nums[des];
    }
}
