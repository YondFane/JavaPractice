package 设计模式.创建型模型.抽象工厂模式;

public class LamborghiniFactory implements ICarFactory {
    public Car creator() {
        return new Lamborghini("兰博基尼");
    }
}
