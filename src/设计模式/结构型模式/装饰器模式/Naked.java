package 设计模式.结构型模式.装饰器模式;

/**
 * 裸体类
 * 人没穿衣服时
 * 装饰基类
 */
public class Naked implements People{
    @Override
    public String dress() {
        return "裸体";
    }
}
