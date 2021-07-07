package 设计模式.结构型模式.适配器模式.类适配器;


/**
 * 类适配器
 * 继承客户端接口和服务端接口
 * 实现客户端接口和服务端接口方法
 * 类适配器基于继承
 */
public class Adapter implements IClient,IService {

    @Override
    public void send(String message) {
        System.out.println("（适配器）客户端发送消息成功："+message);
    }

    @Override
    public String accept() {
        return "（适配器）服务端接收消息成功！";
    }
}
