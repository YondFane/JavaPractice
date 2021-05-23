package 设计模式.创建型模型.单例模式;

/**
 * 懒汉式
 * 使用静态内部类实现
 *
 * 类在加载、验证、准备、解析、初始化的过程中。
 * 初始化阶段时执行clinit方法，包含类的静态变量赋初始值和执行静态代码块中的代码，
 * 但不会立即加载内部类，内部类在使用时才会被加载。
 * 初始化过程中，一次只有一个线程可以执行clinit方法，保证了单例的线程安全。
 *
 */
public class Singleton3 {
    private static class SingletonManager {
        public static Singleton3 singleton = new Singleton3();
    }
    private Singleton3(){
    }
    public static Singleton3 getInstance() {
        return SingletonManager.singleton;
    }
}
