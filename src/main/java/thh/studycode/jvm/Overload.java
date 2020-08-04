package thh.studycode.jvm;

import java.io.Serializable;

/**
 * 重载方法匹配优先级演示
 */
public class Overload {


    public static void sayHello(char arg) {
        System.out.println("hello char");
    }

    /**
     * 如果去掉前面的方法 sayHello(char arg)，这里会发生自动转换
     *
     * @param arg
     */
    public static void sayHello(int arg) {
        System.out.println("hello int");
    }

    /**
     * 如果去掉前面的方法，这里会发生两次自动转换，匹配到了long的重载
     *
     * @param arg
     */
    public static void sayHello(long arg) {
        System.out.println("hello logn");
    }

    /**
     * 如果去掉前面的方法，这里会自动装箱，匹配到了参数类型为character的重载
     *
     * @param arg
     */
    public static void sayHello(Character arg) {
        System.out.println("hello character");
    }


    public static void sayHello(Object arg) {
        System.out.println("hello object");
    }

    public static void sayHello(char... arg) {
        System.out.println("hello char...");
    }

    public static void sayHello(Serializable arg) {
        System.out.println("hello Serializable");
    }

    public static void main(String[] args) {
        sayHello('a');
    }
}
