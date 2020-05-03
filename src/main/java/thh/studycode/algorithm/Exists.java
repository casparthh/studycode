package thh.studycode.algorithm;

import org.apache.commons.lang.math.RandomUtils;

/**
 * 二分查找一个数是否存在
 */
public class Exists {
    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            int[] nums = new int[RandomUtils.nextInt(1000)+1];
            for (int j = 0; j < nums.length; j++) {
                nums[j] = j;
            }
            int n = RandomUtils.nextInt(nums.length);
            if(exists(nums, n, 0, nums.length - 1) == -1
                    || exists(nums, 0, 0, nums.length - 1) == -1
                    || exists(nums, nums.length -1, 0, nums.length - 1) == -1
                    || exists(nums, n) == -1
                    || exists(nums, 0) ==-1
                    || exists(nums, nums.length-1) ==-1){
                System.out.println("失败");
            }

        }

        System.out.println("done");
        int[] nums = {1,1,2,2,2,3,3,3,3,4,4,4,4,4};
        System.out.println(exists(nums, 1));
        System.out.println(exists(nums, 1, 0, nums.length));





    }

    public static int exists(int[] nums, int n, int left, int right) {
        if (left == right && n != left) return -1;
        int mid = left + ((right - left) >> 1);
        if (n > nums[mid]) {
            //右边
            return exists(nums, n, mid + 1, right);
        } else if (n < nums[mid]) {
            //左边
            return exists(nums, n, left, mid);
        } else if (n == nums[mid]) {
            return 1;
        }
        return -1;
    }

    public static int exists(int[] nums, int n) {
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = left + ((right - left) >> 1);
            if (nums[mid] < n) {
                //n在右边，left 指针右移
                left = mid + 1;

            } else if (nums[mid] > n) {
                //n在左边，right 指针左移
                right = mid;
            }else if(nums[mid] == n){
                return 1;
            }
        }
        return -1;
    }

}
