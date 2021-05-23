package 设计模式.创建型模型.工厂方法模式.简单工厂模式;

public class Main {
    public static void main(String[] args) throws Exception {
        Car bwm = CarFactory.creator("宝马");
        System.out.println(bwm.getCarName());
        Car porsche = CarFactory.creator("保时捷");
        System.out.println(porsche.getCarName());
    }
}
