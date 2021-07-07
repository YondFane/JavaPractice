package 设计模式.结构型模式.适配器模式.类适配器;

/**
 * 客户端接口
 */
public interface IClient {
    /**
     * 发送方法
     * @param message
     */
    public void send(String message);
}
