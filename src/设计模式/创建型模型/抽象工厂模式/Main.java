package 设计模式.创建型模型.抽象工厂模式;

public class Main {
    public static void main(String[] args) {
        ICarFactory carFactory = new LamborghiniFactory();
        Car lamborghini = carFactory.creator();
        System.out.println(lamborghini.getCarName());
    }
}
