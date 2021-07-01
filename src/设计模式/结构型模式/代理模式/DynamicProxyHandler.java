package 设计模式.结构型模式.代理模式;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理
 * 继承InvocationHandler接口
 * 实现invoke方法
 */
public class DynamicProxyHandler implements InvocationHandler {

    private Object proxyObject;

    public DynamicProxyHandler(Object targetObject) {
        this.proxyObject = targetObject;
    }


    /**
     * 实现invoke方法
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName() + "代理方法执行");
        long start = System.currentTimeMillis();
        // 方法调用
        Object result = method.invoke(proxyObject, args);
        long end = System.currentTimeMillis();
        System.out.println("执行时间：" + (end - start));
        return result;
    }
}
