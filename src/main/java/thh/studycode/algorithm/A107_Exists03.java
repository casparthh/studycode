package thh.studycode.algorithm;

/**
 * 二分查找局部最小数, 无序数组中，相邻的数不相等，返回任意一个局部最小数
 */
public class A107_Exists03 {
    public static void main(String[] args) {
        int[] nums = {9,8,7,5,7,8,9};
        System.out.println(exists(nums));
        System.out.println("done");
    }

    public static int exists(int[] nums) {
        if (nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return 0;
        }

        int lPoint = 0;
        int rPoint = nums.length - 1;
        int mid = 0;

        if (nums[0] < nums[1]) {
            return 0;
        } else if (nums[rPoint] < nums[rPoint - 1]) {
            return rPoint;
        }

        while (lPoint <= rPoint) {
            mid = lPoint + ((rPoint - lPoint) >> 1);
            if (nums[mid] > nums[mid-1]) {
                rPoint = mid-1;
            }else if (nums[mid] > nums[mid +1]) {
                lPoint = mid +1;
            } else {
                return mid;
            }
        }
        return -1;
    }

}
