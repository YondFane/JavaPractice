package 设计模式.结构型模式.代理模式;

import java.lang.reflect.Proxy;

public class PublicProxy {

    /**
     * 创建代理对象
     * @param proxyObject
     * @return
     */
    public static Object createProxy(Object proxyObject) {
        // 获取Class
        Class<?> aClass = proxyObject.getClass();
        // 获取接口方法
        Class<?>[] interfaces = aClass.getInterfaces();
        // 获取类装载器
        ClassLoader classLoader = aClass.getClassLoader();
        // 创建代理对象
        return Proxy.newProxyInstance(classLoader, interfaces, new DynamicProxyHandler(proxyObject));
    }

}
