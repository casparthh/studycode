package thh.studycode.algorithm;

import java.util.Arrays;

public class Select {

    public static void main(String[] args) {
        int[] nums = new int[(int) (Math.random() * 100)];
        int[] nums1 = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = (int) (Math.random() * 100);
            nums1[i] = nums[i];
            System.out.print(nums[i] + "\t");
        }

        Arrays.sort(nums1);
        select(nums);

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
     * 选择排序
     * 选择排序是给定位置去找数。选择排序是按顺序比较，找最大值或者最小值，每一轮比较都只需要换一次位置；
     * <p>
     * 依次找出最大值，并移到最后面
     * <p>
     * 时间复杂度，O(n^2)
     * 空间复杂度，O(1)
     * 稳定性：不稳
     */
    public static void select(int[] nums) {
        int maxIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            maxIndex = nums.length - 1 - i;
            for (int j = 0; j < nums.length - i; j++) {
                maxIndex = nums[j] > nums[maxIndex] ? j : maxIndex;
            }

            if (maxIndex != nums.length - i - 1) {
                swap(nums, maxIndex, nums.length - i - 1);
            }
        }
    }

    public static void swap(int[] nums, int res, int des) {
        nums[res] = nums[res] ^ nums[des];
        nums[des] = nums[res] ^ nums[des];
        nums[res] = nums[res] ^ nums[des];
    }
}
