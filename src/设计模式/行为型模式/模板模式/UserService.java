package 设计模式.行为型模式.模板模式;

/**
 * 继承UserServiceTemplate模板类
 */
public class UserService extends UserServiceTemplate {
    @Override
    public void createUser(String name) {
        System.out.println(name+"用户创建成功！");
    }

    @Override
    public void createWallet(String name) {
        System.out.println(name+"用户钱包创建成功！");
    }
}
