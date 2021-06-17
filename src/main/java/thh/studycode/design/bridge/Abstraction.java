package thh.studycode.design.bridge;

public abstract class Abstraction {

    protected DrawApi drawApi;

    public Abstraction(DrawApi drawApi) {
        this.drawApi = drawApi;
    }

    public abstract void print();

}
