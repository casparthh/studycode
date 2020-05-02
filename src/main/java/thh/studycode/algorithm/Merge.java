package thh.studycode.algorithm;

import java.util.Arrays;

public class Merge {

    public static void main(String[] args) {
        int[] nums = new int[(int) (Math.random() * 100)];
        int[] nums1 = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = (int) (Math.random() * 100);
            nums1[i] = nums[i];
            System.out.print(nums[i] + "\t");
        }


        Arrays.sort(nums1);
        sort(nums, 0, nums.length - 1);

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
     * 归并排序（MERGE-SORT）是利用归并的思想实现的排序方法，该算法采用经典的分治（divide-and-conquer）策略（分治法将问题分(divide)成一些小的问题然后递归求解，而治(conquer)的阶段则将分的阶段得到的各答案”修补”在一起，即分而治之)。
     * 分解：将列表越分越小，直至分成一个元素。
     * 终止条件：一个元素是有序的。
     * 合并：将两个有序列表归并，列表越来越大。
     * <p>
     * 将数组进行左右递归切分排序，并将左右排好序后的内容进行合并。
     * <p>
     * 时间复杂度，O(nlogn)
     * 空间复杂度，O(n)
     * 稳定性：稳
     */
    public static void sort(int[] nums, int left, int right) {
        if (left == right) return;
        int mid = left + (right - left) / 2;
        //左侧
        sort(nums, left, mid);
        //右侧
        sort(nums, mid + 1, right);

        merge(nums, left, right, mid + 1);
    }

    /**
     * 合并过程，假调左右两边的数据是分别有序的。将左右两边的数据按大小合并。
     *
     * @param nums
     * @param left
     * @param right
     * @param mid
     */
    public static void merge(int[] nums, int left, int right, int mid) {
        //申请临时空间，存放合并后排序部分的内容。
        int[] temp = new int[right - left + 1];
        int mPoint = mid;

        int point = 0;
        //依次将最小值放入临时空间，并将指针右移
        while (mPoint > left && right >= mid) {
            temp[point++] = nums[left] < nums[mid] ? nums[left++] : nums[mid++];
        }
        while (mPoint > left) {
            temp[point++] = nums[left++];
        }
        while (right >= mid) {
            temp[point++] = nums[mid++];
        }
        //将临时空间中的有序数据再写回数组。
        for (int i = 0; i < temp.length; i++) {
            nums[right - temp.length + 1 + i] = temp[i];
        }
    }

}
