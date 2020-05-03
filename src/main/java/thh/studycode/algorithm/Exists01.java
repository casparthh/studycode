package thh.studycode.algorithm;

/**
 * 二分查找 >= 一个数最左侧的位置
 */
public class Exists01 {
    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 4, 8, 8, 8, 8};
        //0 index 0
        //1 index 0
        //2 index 2
        //3 index 5
        //4 index 9
        //5 index 14
        //6 index 14
        //7 index 14
        //8 index 14
        //9 index -1
        //10 index -1
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
        int index = -1; //关链点，记录最后一次找到的位置
        while (lPoint <= rPoint) {
            mid = lPoint + ((rPoint - lPoint) >> 1);
            if (nums[mid] >= n) {
                //n在左边，right 指针左移. //（这里减1，多移一位，因为已经在该位置找到值。继续查找左部分是否还有）
                rPoint = mid - 1;
                index = mid;
            } else if (nums[mid] < n) {
                //n在右边，left 指针右移
                lPoint = mid + 1;
            }
        }
        return index;
    }

}
