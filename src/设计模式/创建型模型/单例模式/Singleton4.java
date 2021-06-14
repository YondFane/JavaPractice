package 设计模式.创建型模型.单例模式;

/**
 * 基于枚举类型的单例实现
 * 通过 Java 枚举类型本身的特性，保证了实例创建的线程安全性和实例的唯一性。
 */
public enum Singleton4 {
    INSTANCE;
    private Demo demo = new Demo();
}

/**
 * DEMO
 */
class Demo {
    public Demo() {
    }
}

