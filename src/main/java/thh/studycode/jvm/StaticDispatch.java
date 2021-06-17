package thh.studycode.jvm;

/**
 * 方法静态分派演示
 */
public class StaticDispatch {

    static abstract class Human {

    }

    static class Man extends Human {

    }

    static class Woman extends Human {

    }

    public void sayHello(Human guy) {
        System.out.println("Hello, guy!");
    }

    public void sayHello(Man guy) {
        System.out.println("Hello, gentleman");
    }

    public void sayHello(Woman guy) {
        System.out.println("Hello, lady");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Man man1 = new Man();
        Human woman = new Woman();
        Woman woman1 = new Woman();
        StaticDispatch dispatch = new StaticDispatch();
        dispatch.sayHello(man);
        dispatch.sayHello(woman);

        dispatch.sayHello(man1);
        dispatch.sayHello(woman1);
    }
}
