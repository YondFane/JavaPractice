package 设计模式.创建型模型.单例模式;

/**
 * 懒汉式
 */
public class Singleton {
    /**
     * 1、一个静态私有的变量
     */
    private static Singleton singleton;

    /**
     * 2、一个私有的构造方法且不存在任何其他构造方法
     */
    private Singleton() {
    }

    /**
     * 3、获取实例的公有静态方法
     * @return
     */
    public static Singleton getInstance(){
        /**
         * 懒汉式
         * 第一次调用getInstance()方法获取实例时
         * 创建实例对象
         */
        if (singleton == null) {
            //线程安全
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
