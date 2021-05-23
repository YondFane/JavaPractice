package 设计模式.创建型模型.工厂方法模式;

public class Lamborghini implements Car {
    private String name;

    public Lamborghini() {
    }

    public Lamborghini(String name) {
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
