package 设计模式.结构型模式.装饰器模式;

/**
 * 睡衣类
 * 装饰扩展类
 */
public class Pajamas extends Naked {
    private People naked;

    public Pajamas(People naked) {
        this.naked = naked;
    }

    /**
     * 多穿一件睡衣
     *
     * @return
     */
    @Override
    public String dress() {
        return naked.dress() + "+" + "睡衣";
    }
}
