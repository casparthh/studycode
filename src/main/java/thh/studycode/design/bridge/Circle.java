package thh.studycode.design.bridge;

public class Circle extends Abstraction {

    public Circle(DrawApi drawApi) {
        super(drawApi);
    }

    @Override
    public void print() {
        drawApi.draw();
    }
}
