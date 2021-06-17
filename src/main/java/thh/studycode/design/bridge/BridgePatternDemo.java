package thh.studycode.design.bridge;

public class BridgePatternDemo {

    public static void main(String[] args) {
        RedCircle red = new RedCircle();
        GreenCircle green = new GreenCircle();
        Circle circle = new Circle(red);
        circle.print();

        Circle circle1 = new Circle(green);
        circle1.print();
    }
}
