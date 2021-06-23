package Java8特性.lambda表达式;

import java.util.ArrayList;

public class LambdaTest {
    public static void main(String[] args) {
        String global = "GLOBAL";
        // 2、Lambda表达式 -> 左边的使用方式1 : 不带（）括号
        LambdaInterface lambdaInterface1 = s -> {
            // LambdaInterface接口中 test方法的实现
            // s为局部变量
            // 函数内部可以访问外部变量
            System.out.println(global + ":" + s);
        };
        // 2、Lambda表达式 -> 左边使用方式2 : 带（）括号
        LambdaInterface lambdaInterface2 = (s) -> {
            // LambdaInterface接口中 test方法的实现
            // s为局部变量
            System.out.println(s);
        };
        // 2、Lambda表达式 -> 左边使用方式3 : 带（）括号且带声明类型 （类型声明只能是String，LambdaInterface接口test方法参数类型为String，）
        LambdaInterface lambdaInterface3 = (String s) -> {
            // LambdaInterface接口中 test方法的实现
            // s为局部变量
            System.out.println(s);
        };


        // 2、Lambda表达式 -> 左边使用方式4 - 1 : 如果方法参数个数大于等于2，不能使用不带括号的表达式
        LambdaInterface2 lambdaInterface4 = (s1, s2) -> {
            // LambdaInterface2接口中 test方法的实现
            // s1 , s2为局部变量
            System.out.println(s1 + ":" + s2);
            return "RETURN";
        };
        // 2、Lambda表达式 -> 左边使用方式4 - 2 : 如果方法参数个数大于等于2，不能使用不带括号的表达式
        LambdaInterface2 lambdaInterface5 = (String s1, String s2) -> {
            // LambdaInterface2接口中 test方法的实现
            // s1 , s2为局部变量
            System.out.println(s1 + ":" + s2);
            return "RETURN";
        };

        lambdaInterface1.test("123");// GLOBAL:123
        System.out.println(lambdaInterface4.test("S1", "S2"));// S1:S2  RETURN

        // 3、Lambda表达式 -> 右边使用方式1 : 只有一条语句（表达式）
        LambdaInterface lambdaInterface6 = (s) -> System.out.println(s);
        LambdaInterface2 lambdaInterface7 = (s1, s2) -> s1 + s2;

        lambdaInterface6.test("123");// 123
        System.out.println(lambdaInterface7.test("123", "456"));// 123456

        //-------------------常见的@FunctionalInterface注解的接口------------------
        // Comparator接口的sort方法实现
        ArrayList<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(4);
        list.add(1);
        list.add(2);
        list.sort((o1,o2)->{
            return o1 - o2;
        });
        System.out.println(list.toString());// [1, 2, 3, 4]
        // Runnable接口的run方法实现
        Thread thread = new Thread(()->{
            // run方法实现
            System.out.println("RUN方法启动");
            System.out.println(Thread.currentThread().getName());
        });
        thread.setName("TEST-THREAD");
        System.out.println(Thread.currentThread().getName());// main
        // 线程启动
        thread.start();//RUN方法启动 TEST-THREAD

    }


}

/**
 * 1、一个只有单个方法的接口 （默认方法除外）
 * 使用@FunctionalInterface注解可以校验接口是否符合Lambda表达式标准
 *
 * @FunctionalInterface并不是必须的！
 */
@FunctionalInterface
interface LambdaInterface {
    void test(String s);
}

@FunctionalInterface
interface LambdaInterface2 {
    String test(String s1, String s2);
}
