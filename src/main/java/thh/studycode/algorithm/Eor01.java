package thh.studycode.algorithm;

/**
 * 把一个int类型的数，提取出最右侧的1 （N & (~N+1)）
 */
public class Eor01 {

    public static void main(String[] args) {
        int num = 11;
        System.out.println(find(num));

    }

    public static int find(int num){
        num = num &(~num + 1);
        return num;
    }
}
