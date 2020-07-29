package thh.studycode.algorithm;

import java.util.Arrays;

/**
 * 不基于比较的排序
 * 桶排序 - 计数排序
 * 将相同的数放放相同的桶/队列。再依次倒出来。
 */
public class A503_CountSort {

    public static void main(String[] args) {
        int[] nums = new int[(int) (Math.random() * 100)];
        int[] nums1 = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = (int) (Math.random() * 100);
            nums1[i] = nums[i];
            System.out.print(nums[i] + "\t");
        }


        Arrays.sort(nums1);
        count(nums);

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
     * 计数排序
     * <p>
     * 将相同的数放放相同的桶/队列。再依次倒出来。
     * <p>
     * 时间复杂度，O(n)
     * 空间复杂度，O(n)
     * 稳定性：稳
     */
    public static void count(int[] nums) {
        int[] bucket = new int[100];
        for (int i = 0; i < nums.length; i++) {
            bucket[nums[i]] += 1;
        }
        int index = 0;
        for (int i = 0; i < bucket.length; i++) {
            for (int j = 0; j < bucket[i]; j++) {
                nums[index++] = i;
            }
        }
    }

    public static void swap(int[] nums, int res, int des) {
        nums[res] = nums[res] ^ nums[des];
        nums[des] = nums[res] ^ nums[des];
        nums[res] = nums[res] ^ nums[des];
    }
}
