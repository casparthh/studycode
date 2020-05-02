package thh.studycode.algorithm;

import java.util.Arrays;

public class Bubble {

    public static void main(String[] args) {
        int[] nums = new int[(int)(Math.random()*100)];
        int[] nums1 = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = (int)(Math.random()*100);
            nums1[i] = nums[i];
            System.out.print(nums[i]+"\t");
        }


        Arrays.sort(nums1);
        bubble(nums);

        System.out.println("");
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + "\t");
            if(nums[i] != nums1[i]){
                System.out.println(false);
                return;
            }
        }
        System.out.println(true);
    }

    /**
     * 冒泡排序
     *冒泡排序是通过数去找位置，比较相邻位置的两个数，每一轮比较后，位置不对都需要换位置
     *
     * 依次对比相邻两个数，将大的换到后面。
     *
     * 时间复杂度，O(n*n)
     * 空间复杂度，O(1)
     * 稳定性：稳
     */
    public static void bubble(int[] nums){
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j < nums.length-i; j++) {
                if(nums[j-1]> nums[j]) {
                    swap(nums, j-1, j);
                }
            }
        }


    }

    public static void swap(int[] nums, int res, int des){
        nums[res] = nums[res] ^ nums[des];
        nums[des] = nums[res] ^ nums[des];
        nums[res] = nums[res] ^ nums[des];
    }

}
