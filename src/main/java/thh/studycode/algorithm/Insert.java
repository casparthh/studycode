package thh.studycode.algorithm;

import java.util.Arrays;

public class Insert {

    public static void main(String[] args) {
        int[] nums = new int[10];
        int[] nums1 = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = (int) (Math.random() * 100);
            nums1[i] = nums[i];
            System.out.print(nums[i] + "\t");
        }


        Arrays.sort(nums1);
        insert(nums);

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
     * 插入排序
     * <p>
     * 类似于抓牌，将新抓到的数字，放到排序中合适位置
     * <p>
     * 时间复杂度，O(n*n)
     * 空间复杂度，O(1)
     * 稳定性：稳
     */
    public static void insert(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            for (int j = i; j > 0; j--) {
                if (nums[j] < nums[j - 1]) {
                    swap(nums, j, j - 1);
                } else {
                    //如果当前元素大于等于上一个元素了，跳出内循环。
                    break;
                }
            }
        }

    }

    public static void swap(int[] nums, int res, int des) {
        nums[res] = nums[res] ^ nums[des];
        nums[des] = nums[res] ^ nums[des];
        nums[res] = nums[res] ^ nums[des];
    }

}
