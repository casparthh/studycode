package thh.studycode.algorithm;

import java.util.Arrays;

/**
 * 不基于比较的排序
 */
public class A504_RadixSort {

    public static void main(String[] args) {
        int[] nums = new int[10];
        int[] nums1 = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = (int) (Math.random() * 100);
            nums1[i] = nums[i];
            System.out.print(nums[i] + "\t");
        }


        Arrays.sort(nums1);
        radix(nums);

        System.out.println("");
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + "\t");
            if (nums[i] != nums1[i]) {
                System.out.println(false);
                return;
            }
        }
        System.out.println(true);
    }

    /**
     * 桶排序 - 基数排序
     * <p>
     * 先排序个位数，再依次排序十位，百位。。。
     * <p>
     * 时间复杂度，O(n*n)
     * 空间复杂度，O(1)
     * 稳定性：稳
     */
    public static void radix(int[] nums) {
        //todo
    }

    public static void swap(int[] nums, int res, int des) {
        nums[res] = nums[res] ^ nums[des];
        nums[des] = nums[res] ^ nums[des];
        nums[res] = nums[res] ^ nums[des];
    }
}
