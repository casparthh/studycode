package thh.studycode.algorithm;


/**
 * 荷兰国旗问题：给定一个整数数组，给定一个值K，这个值在原数组中一定存在，要求把数组中小于K的元素放到数组的左边，大于K的元素放到数组的右边，等于K的元素放到数组的中间，
 * 最终返回一个整数数组，其中只有两个值，分别是等于K的数组部分的左右两个下标值
 */
public class Quick01 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 4, 7, 3,5,4,5,8,5,1, 1, 3};
        int[] res = partition(nums, 5);
        System.out.println(res[0] +"\t"+res[1]);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + "\t");
        }
    }

    public static int[] partition(int[] nums, int n) {
        if (nums == null || nums.length < 1 || (nums.length == 1 && nums[0] != n)) {
            return new int[]{-1,-1};
        }
        if(nums.length == 1){
            return new int[]{0,0};
        }
        int lPoint = -1;
        int rPoint = nums.length;
        int point = 0;
        while (point < rPoint) {
            if (nums[point] < n) {
                swap(nums, ++lPoint, point++);
            } else if (nums[point] > n) {
                swap(nums, point, --rPoint);
            } else {
                point++;
            }
        }
        return new int[]{lPoint+1, rPoint-1};
    }

    public static void swap(int[] nums, int res, int des) {
//        nums[res] = nums[res] ^ nums[des];
//        nums[des] = nums[res] ^ nums[des];
//        nums[res] = nums[res] ^ nums[des];
        int temp = nums[res];
        nums[res] = nums[des];
        nums[des] = temp;
    }
}
