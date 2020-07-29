package thh.studycode.algorithm;


import org.apache.commons.lang.math.RandomUtils;

import java.util.Arrays;

/**
 * 随机快排
 * 时间复杂度 O(N*logN)
 * 额外空间复杂度O(logN)
 */
public class A305_QuickSort {

    public static void main(String[] args) {
        for (int t = 0; t < 100000; t++) {
            int[] nums = new int[(int) (Math.random() * 100)];
            int[] nums1 = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = (int) (Math.random() * 100);
                nums1[i] = nums[i];
            }

            Arrays.sort(nums1);
            quick(nums, 0, nums.length - 1);
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != nums1[i]) {
                    System.out.println(false);
                    return;
                }
            }
        }
        System.out.println("\r\n" + true);

    }

    public static void quick(int[] nums, int left, int right) {
        if (nums == null || nums.length <= 1 || left >= right) {
            return;
        }
        int lPoint = left - 1;
        int rPoint = right + 1;
        int point = left;
        int n = nums[RandomUtils.nextInt(right - left) + left];

        while (point < rPoint) {
            if (nums[point] < n) {
                swap(nums, ++lPoint, point++);
            } else if (nums[point] > n) {
                swap(nums, point, --rPoint);
            } else {
                point++;
            }
        }
        quick(nums, left, lPoint);
        quick(nums, rPoint, right);
    }


    public static void swap(int[] nums, int res, int des) {
        int temp = nums[res];
        nums[res] = nums[des];
        nums[des] = temp;
    }
}
