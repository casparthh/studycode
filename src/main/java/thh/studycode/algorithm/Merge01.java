package thh.studycode.algorithm;

/**
 * 求数组小和，在一个数组中，一个数左边比它小的数的总和，叫数的小和，所有数的小和累加起来叫做数组小和。
 */
public class Merge01 {

    public static void main(String[] args) {
        int[] nums = {3,1,7,0,2};

        int sum = sort(nums, 0, nums.length - 1);

        System.out.println(sum);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + "\t");
        }

    }


    public static int sort(int[] nums, int left, int right) {
        int sum = 0;
        if (nums == null || nums.length <= 1 || left == right) {
            return sum;
        }

        int mid = left + ((right - left) >> 1);
        sum += sort(nums, left, mid);
        sum += sort(nums, mid + 1, right);
        sum += merge(nums, left, right, mid);
        return sum;
    }

    public static int merge(int[] nums, int left, int right, int mid) {
        int lPoint = left;
        int rPoint = mid + 1;
        int sum = 0;
        int point = 0;
        int[] temp = new int[right - left + 1];
        while (lPoint <= mid && rPoint <= right) {
            if (nums[lPoint] < nums[rPoint]) {
                sum += nums[lPoint] * (right - rPoint + 1);
            }
            temp[point++] = nums[lPoint] < nums[rPoint] ? nums[lPoint++] : nums[rPoint++];
        }
        while (lPoint <= mid) {
            temp[point++] = nums[lPoint++];
        }
        while (rPoint <= right) {
            temp[point++] = nums[rPoint++];
        }

        for (int i = 0; i < temp.length; i++) {
            nums[left + i] = temp[i];
        }
        return sum;
    }

}
