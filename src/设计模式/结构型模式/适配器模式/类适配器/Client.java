package 设计模式.结构型模式.适配器模式.类适配器;

/**
 * 客户端
 */
public class Client implements IClient {
    @Override
    public void send(String message) {
        System.out.println("客户端发送消息成功："+message);
    }
}
