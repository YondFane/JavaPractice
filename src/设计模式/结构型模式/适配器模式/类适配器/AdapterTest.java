package 设计模式.结构型模式.适配器模式.类适配器;

public class AdapterTest {
    public static void main(String[] args) {
        /**
         * 未使用类配器前
         */
        IClient client = new Client();
        IService service = new Service();
        client.send("hello");
        String result = service.accept();
        System.out.println(result);

        /**
         * 因为Adapter适配器继承了IClient和IService接口
         * 可以将其转换为适用于被封装服务对象的调用
         */
        // 转换为客户端
        IClient clientAdapter = new Adapter();
        // 转换为服务端
        IService serviceAdapter = new Adapter();
        // Adapter适配器调用客户端接口方法
        clientAdapter.send("hello2");
        // Adapter适配器调用服务端接口方法
        String result2 = service.accept();
        System.out.println(result2);

    }
}
