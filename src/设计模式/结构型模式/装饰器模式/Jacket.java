package 设计模式.结构型模式.装饰器模式;

/**
 * 夹克
 * 装饰扩展类
 */
public class Jacket extends Naked{
    private People naked;
    public Jacket(People naked) {
        this.naked = naked;
    }

    /**
     * 加穿一件夹克
     * @return
     */
    @Override
    public String dress() {
        return naked.dress() + "+" + "夹克";
    }
}
