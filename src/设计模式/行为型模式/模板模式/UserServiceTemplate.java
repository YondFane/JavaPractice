package 设计模式.行为型模式.模板模式;

/**
 * 模板类
 *
 * 子类只需要实现模板类的抽象方法
 */
public abstract class UserServiceTemplate {

    /**
     * 模板方法
     *
     * 用户注册需要创建用户和创建用户钱包
     * register()模板方法执行了createUser()和createWallet()
     * @param name
     */
    public void register(String name) {
        createUser(name);
        createWallet(name);
    }

    /**
     * 创建用户
     * @param name
     */
    public abstract void createUser(String name);

    /**
     * 创建钱包
     * @param name
     */
    public abstract void createWallet(String name);
}
