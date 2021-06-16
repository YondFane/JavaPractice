package 设计模式.创建型模型.原型模式;

import sun.security.provider.Sun;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        SunWuKong sunWuKong = new SunWuKong("孙悟空", new GoldenCudgel());
        SunWuKong sixEarMonkeys = new SunWuKong("六耳猕猴", new GoldenCudgel());

        SunWuKong sunWuKong1 = (SunWuKong) sunWuKong.clone();
        SunWuKong sixEarMonkeys1 = (SunWuKong) sixEarMonkeys.clone();

        System.out.println(sunWuKong.hashCode());
        System.out.println(sunWuKong.toString());
        System.out.println(sunWuKong1.hashCode());
        System.out.println(sunWuKong1.toString());
        System.out.println();
        System.out.println(sixEarMonkeys.hashCode());
        System.out.println(sixEarMonkeys.toString());
        System.out.println(sixEarMonkeys1.hashCode());
        System.out.println(sixEarMonkeys1.toString());
    }
}
