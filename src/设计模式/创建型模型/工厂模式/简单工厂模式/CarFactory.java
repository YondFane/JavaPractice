package 设计模式.创建型模型.工厂模式.简单工厂模式;

public class CarFactory {
    public static Car creator(String carName) throws Exception {
        switch (carName){
            case "宝马":return new BMW("宝马");
            case "保时捷":return new Porsche("保时捷");
            case "兰博基尼":return new Lamborghini("兰博基尼");
        }
        throw new Exception("异常");
    }
}
