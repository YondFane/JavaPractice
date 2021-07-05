package 设计模式.结构型模式.装饰器模式;

/**
 * 毛衣
 * 装饰扩展类
 */
public class Sweater extends Naked {
    private People naked;
    public Sweater(People naked) {
        this.naked = naked;
    }

    /**
     * 加穿一件毛衣
     * @return
     */
    @Override
    public String dress() {
        return naked.dress() + "+" + "毛衣";
    }
}
