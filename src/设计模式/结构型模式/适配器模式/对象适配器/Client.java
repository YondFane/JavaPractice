package 设计模式.结构型模式.适配器模式.对象适配器;

/**
 * 客户端
 */
public class Client implements IClient {
    @Override
    public void method(String data) {
        System.out.println("客户端方法");
    }
}
