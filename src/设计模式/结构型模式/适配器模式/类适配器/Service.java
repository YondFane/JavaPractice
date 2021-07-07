package 设计模式.结构型模式.适配器模式.类适配器;

/**
 * 服务端
 */
public class Service implements IService {
    @Override
    public String accept() {
        return "服务端接收消息成功！";
    }
}
