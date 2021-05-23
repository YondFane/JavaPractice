package 设计模式.创建型模型.单例模式;

/**
 * 饿汉式
 */
public class Singleton2 {
    /**
     * 1、一个静态私有的变量
     *
     * 饿汉式，加载类的时候就直接创建实例对象
     */
    private static Singleton2 singleton = new Singleton2();

    /**
     * 2、一个私有的构造方法且不存在任何其他构造方法
     */
    private Singleton2() {
    }

    /**
     * 3、获取实例的公有静态方法
     * @return
     */
    public static Singleton2 getInstance(){
        return singleton;
    }
}
