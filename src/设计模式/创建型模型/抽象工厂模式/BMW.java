package 设计模式.创建型模型.抽象工厂模式;

public class BMW implements Car {
    private String name;

    public BMW() {
    }

    public BMW(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getCarName() {
        return getName();
    }
}
