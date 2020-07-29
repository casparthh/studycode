package thh.studycode.algorithm;

public class A301_MergeSort {

    public static void main(String[] args) {
        for (int t = 0; t < 100000; t++) {
            int[] nums = new int[(int) (Math.random() * 100)];
            int[] nums1 = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = (int) (Math.random() * 100);
                nums1[i] = nums[i];
            }

            foreach(nums, 0, nums.length - 1);
            sort(nums1, 0, nums.length - 1);
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != nums1[i]) {
                    System.out.println(false);
                    return;
                }
            }
        }
        System.out.println("\r\n" + true);
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
        if (nums == null || nums.length <= 1 || left == right) return;
        int mid = left + ((right - left) >> 1);
        sort(nums, left, mid);
        sort(nums, mid + 1, right);
        merge(nums, left, right, mid);
    }

    /**
     * 非递归方法实现
     * 乘2时， 先判断，避免在step <<= 1时数字大于int最大值
     *
     * @param nums
     * @param left
     * @param right
     */
    public static void foreach(int[] nums, int left, int right) {
        if (nums == null || nums.length <= 1 || left == right) return;
        int step = 1;
        int size = right - left + 1;
        while (step < size) {
            int lPoint = left;
            while (lPoint <= right) {
                //只有左边，由于左边肯定是有序的，直接跳过
                if (right - lPoint + 1 > step) {

                    //当右边不够step数量时，右边界取right值
                    int rPoint = Math.min(lPoint + (step << 1) - 1, right);

                    //mid 固定为左边界+step-1
                    int mid = lPoint + step-1;

                    merge(nums, lPoint, rPoint, mid);
                }
                lPoint += (step << 1);
            }

            //先判断，避免在step <<= 1时数字大于int最大值
            if (step > size >> 1) {
                return;
            }
            step <<= 1;
        }
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
        int rPoint = mid + 1;
        int lPoint = left;
        int index = 0;
        //依次将最小值放入临时空间，并将指针右移
        while (lPoint <= mid && rPoint <= right) {
            temp[index++] = nums[lPoint] < nums[rPoint] ? nums[lPoint++] : nums[rPoint++];
        }
        while (lPoint <= mid) {
            temp[index++] = nums[lPoint++];
        }
        while (rPoint <= right) {
            temp[index++] = nums[rPoint++];
        }
        //将临时空间中的有序数据再写回数组。
        for (int i = 0; i < temp.length; i++) {
            nums[left + i] = temp[i];
        }
    }

}
