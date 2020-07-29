package thh.studycode.algorithm;

import java.util.HashSet;
import java.util.Random;

/**
 * 贪心算法的解题套路实战
 * <p>
 * 给定一个字符串str,只能'X'和'.'两种字符构成。
 * 'X'表示墙，不能放灯，也不需要点亮
 * '.'表示居民点，可以放灯，需要点亮
 * 如果灯放在i的位置，可以让i-1,i和i+1三个位置被点亮
 * 返回如果点亮str中所需要点亮的位置，至少需要几盏灯
 */
public class A1002_Greedy_MinLights {


    public static int getMinLightsByGreedy(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int lights = 0;
        for (int i = 0; i < chars.length; i++) {
            char letter = chars[i];
            if (letter != 'X') {
                if ((chars.length > i + 1) && chars[i + 1] != 'X') {
                    i += 2;
                }
                lights++;
            }
        }
        return lights;
    }

    public static int getMinLights(char[] str, int index, HashSet<Integer> lights) {
        if (str.length == index) {
            for (int i = 0; i < str.length; i++) {
                char letter = str[i];
                if (letter != 'X') {
                    if (lights.contains(i) == false && lights.contains(i - 1) == false && lights.contains(i + 1) == false) {
                        return Integer.MAX_VALUE;
                    }
                }
            }
            return lights.size();
        }

        int no = getMinLights(str, index+1, lights);
        int yes = Integer.MAX_VALUE;
        if(str[index] == '.'){
            lights.add(index);
            yes = getMinLights(str, index+1, lights);
            lights.remove(index);
        }
        return Math.min(no, yes);
    }


    public static void main(String[] args) {
        int times = 1;
        for (int i = 0; i < times; i++) {
            int len = new Random().nextInt(20) + 2;
            String str = "";
            for (int j = 0; j < len; j++) {
                if ((new Random().nextInt(10)) > 7) {
                    str += "X";
                } else {
                    str += ".";
                }
            }
            System.out.println("str: " + str);
            System.out.println("lights: " + getMinLightsByGreedy(str));
            System.out.println("lights: " + getMinLights(str.toCharArray(),0, new HashSet<Integer>()));
        }
    }


}
