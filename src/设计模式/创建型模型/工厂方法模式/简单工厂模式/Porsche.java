package 设计模式.创建型模型.工厂方法模式.简单工厂模式;

public class Porsche implements Car {
    private String name;

    public Porsche() {
    }

    public Porsche(String name) {
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
