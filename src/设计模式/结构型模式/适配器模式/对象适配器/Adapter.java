package 设计模式.结构型模式.适配器模式.对象适配器;

/**
 * 对象适配器
 * 继承IClient客户端接口实现method方法
 * 对象适配器基于组合而非继承
 */
public class Adapter implements IClient {

    /**
     * 组合
     */
    private Service service;

    public Adapter(Service service) {
        this.service = service;
    }

    @Override
    public void method(String data) {
        data = "强化：" + data;
        /**
         * 调用Service的specialMethod方法
         */
        service.specialMethod(data);
    }
}
