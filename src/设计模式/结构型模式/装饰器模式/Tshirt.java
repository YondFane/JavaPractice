package 设计模式.结构型模式.装饰器模式;

/**
 * 短袖
 * 装饰扩展类
 */
public class Tshirt extends Naked {
    private People naked;
    public Tshirt(People naked) {
        this.naked = naked;
    }

    /**
     * 加穿一件短袖
     * @return
     */
    @Override
    public String dress() {
        return naked.dress() + "+" + "短袖";
    }
}
