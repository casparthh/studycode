package thh.studycode.algorithm;

/**
 * 二分查找小于等于一个数最右侧的位置
 */
public class A106_Exists02 {
    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 4, 8, 8, 8, 8};
        //0 index -1
        //1 index 1
        //2 index 4
        //3 index 8
        //4 index 13
        //5 index 13
        //6 index 13
        //7 index 13
        //8 index 17
        //9 index 17
        //10 index 17
        System.out.println(exists(nums, 0));
        System.out.println(exists(nums, 1));
        System.out.println(exists(nums, 2));
        System.out.println(exists(nums, 3));
        System.out.println(exists(nums, 4));
        System.out.println(exists(nums, 5));
        System.out.println(exists(nums, 6));
        System.out.println(exists(nums, 7));
        System.out.println(exists(nums, 8));
        System.out.println(exists(nums, 9));
        System.out.println(exists(nums, 10));
        System.out.println("done");
    }

    public static int exists(int[] nums, int n) {
        int lPoint = 0;
        int rPoint = nums.length - 1;
        int mid = 0;
        int index = -1;
        while (lPoint <= rPoint) {
            mid = lPoint + ((rPoint - lPoint) >> 1);
            if (nums[mid] <= n) {
                lPoint = mid + 1;
                index = mid;
            } else if (nums[mid] > n) {
                rPoint = mid - 1;
            }
        }

        return index;
    }

}
