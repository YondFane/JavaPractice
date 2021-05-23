package 设计模式.创建型模型.工厂方法模式;

public class LamborghiniFactory {
    public static Car creator() {
        return new Lamborghini("兰博基尼");
    }
}
