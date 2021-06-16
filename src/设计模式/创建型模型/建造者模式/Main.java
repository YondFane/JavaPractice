package 设计模式.创建型模型.建造者模式;

import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        ConstructorArg constructorArg = new ConstructorArg.Builder()
                .setArg("arg参数")
                .setIsRef(false)
                .setType("".getClass())
                .build();
        System.out.println(constructorArg.toString());
    }
}
