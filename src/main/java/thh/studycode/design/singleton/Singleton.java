package thh.studycode.design.singleton;

public class Singleton {

    public final static Singleton INSTANTCE01 = new Singleton();

    private static Singleton INSTANTCE;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (INSTANTCE == null) {
            synchronized (Singleton.class) {
                if (INSTANTCE == null) {
                    INSTANTCE = new Singleton();
                }
            }
        }
        return INSTANTCE;
    }
}
