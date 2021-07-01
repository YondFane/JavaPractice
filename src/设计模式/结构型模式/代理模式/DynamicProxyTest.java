package 设计模式.结构型模式.代理模式;

public class DynamicProxyTest {

    public static void main(String[] args) {
        IUserService userService = new UserServiceImpl();
        // 创建代理对象
        IUserService userServiceProxy = (IUserService) PublicProxy.createProxy(userService);

        userService.setUserName("Jack");
        userService.getUserName();
        userServiceProxy.setUserName("Tom");
        userServiceProxy.getUserName();
    }
}
