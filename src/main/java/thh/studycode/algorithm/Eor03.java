package thh.studycode.algorithm;

/**
 * 统计一个数中有多少bit位为1
 * 依次找出最后一位1
 */
public class Eor03 {

    public static void main(String[] args) {
        int num = -12;
        String str = Integer.toBinaryString(num);
        int c = 0;
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i)=='1') c++;
        }
        System.out.println(Integer.toBinaryString(num));
        System.out.println(count(num));
        System.out.println(count(num) == c);
    }

    public static int count(int num) {
        int counter = 0;
        int eor = 0;
        while (num !=0){
            eor = num & (~num +1);
            counter++;
            num ^= eor;
        }
        return counter;
    }
}
