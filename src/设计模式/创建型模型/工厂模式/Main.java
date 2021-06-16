package 设计模式.创建型模型.工厂模式;

public class Main {
    public static void main(String[] args) {
        Car bwm = BMWFactory.creator();
        Car porsche = PorscheFactory.creator();
        Car lambogrhini = LamborghiniFactory.creator();
        System.out.println(bwm.getCarName());
        System.out.println(porsche.getCarName());
        System.out.println(lambogrhini.getCarName());
    }
}
