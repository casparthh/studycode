package thh.studycode.algorithm;

public class A209_GetMaxRecursion {

    public  static int getMax(int[] arr, int left, int right){
        if(left == right){
            return arr[left];
        }

        int mid = left + ((right-left)>>1);
        return Math.max(getMax(arr, left, mid), getMax(arr, mid+1, right));
    }

    public static void main(String[] args) {
        int[] arr = {1000,221,4,5,2,4,2,13,57,8,8,1,4,13};
        System.out.println(A209_GetMaxRecursion.getMax(arr,0, arr.length-1));
    }


    /*
    Master 公式
    型如：
    T(N) = a * T(N/b) + O(N^d) (其中的a、b、d都是常数)的递归函数，可以直接这过Master 公式来确定时间复杂度
    如果 log(a,b) < d, 复杂度为 O(N^d)
    如果 log(a,b) > d, 复杂度为 O(N^log(b,a))
    如果 log(a,b) == d, 复杂度为 O(N^d*logN)
     */

}
