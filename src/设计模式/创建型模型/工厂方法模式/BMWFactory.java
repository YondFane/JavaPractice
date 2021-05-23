package 设计模式.创建型模型.工厂方法模式;

public class BMWFactory {

    public static Car creator() {
        return new BMW("宝马");
    }
}
