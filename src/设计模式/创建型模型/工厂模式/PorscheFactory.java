package 设计模式.创建型模型.工厂模式;

public class PorscheFactory {
    public static Car creator() {
        return new Porsche("保时捷");
    }
}
