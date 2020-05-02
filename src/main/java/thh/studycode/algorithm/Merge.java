package thh.studycode.algorithm;

import java.util.Arrays;

public class Merge {

    public static void main(String[] args) {
        int[] nums = new int[(int)(Math.random()*100)];
        int[] nums1 = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = (int) (Math.random() * 100);
            nums1[i] = nums[i];
            System.out.print(nums[i] + "\t");
        }


        Arrays.sort(nums1);
        sort(nums, 0, nums.length-1);

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
     * 归并排序
     * <p>
     *
     * <p>
     * 时间复杂度，O(nlogn)
     * 空间复杂度，O(1)
     * 稳定性：稳
     */
    public static void sort(int[] nums, int left, int right) {
        if (left == right) return;
        int mid = left + (right - left) / 2;
        //左侧
        sort(nums, left, mid);
        //右侧
        sort(nums, mid + 1, right);

        merge(nums, left, right,mid + 1);
    }

    public static void merge(int[] nums, int left, int right, int mid) {
        int[] temp = new int[right - left + 1];
        int mPoint = mid;

        int point = 0;
        while (mPoint > left && right >= mid) {
            temp[point++] = nums[left] < nums[mid] ? nums[left++] : nums[mid++];
        }
        while (mPoint > left) {
            temp[point++] = nums[left++];
        }
        while (right >= mid) {
            temp[point++] = nums[mid++];
        }
        for (int i = 0; i < temp.length; i++) {
            nums[right - temp.length + 1 + i] = temp[i];
        }
    }

}
