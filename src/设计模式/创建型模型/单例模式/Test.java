package 设计模式.创建型模型.单例模式;

public class Test {
    public static void main(String[] args) {
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
        System.out.println(singleton1);
        System.out.println(singleton2);
        if(singleton1 == singleton2) {
            System.out.println("singleton1:"+singleton1.hashCode());
            System.out.println("singleton2:"+singleton2.hashCode());
        }
    }
}
