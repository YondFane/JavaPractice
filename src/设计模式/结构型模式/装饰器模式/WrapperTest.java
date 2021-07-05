package 设计模式.结构型模式.装饰器模式;

/**
 * 装饰器模式主要解决继承关系过于复杂的问题，通过组合来替代继承。
 * 它主要的作用是给原始类添加增强功能。这也是判断是否该用装饰器模式的一个重要的依据。
 * 除此之外，装饰器模式还有一个特点，那就是可以对原始类嵌套使用多个装饰器。
 * 为了满足这个应用场景，在设计的时候，装饰器类需要跟原始类继承相同的抽象类或者接口。
 */
public class WrapperTest {
    public static void main(String[] args) {
        // 沐浴完后状态
        Naked naked = new Naked();
        System.out.println(naked.dress());// 裸体
        // 沐浴完后穿上睡衣睡觉
        Pajamas pajamas = new Pajamas(naked);
        System.out.println(pajamas.dress());// 裸体+睡衣
        // 早上起床穿上短袖
        Tshirt tshirt = new Tshirt(pajamas);
        System.out.println(tshirt.dress());// 裸体+睡衣+短袖
        // 穿上短袖后还是感觉有点冷加穿了一件毛衣
        Sweater sweater = new Sweater(tshirt);
        System.out.println(sweater.dress());// 裸体+睡衣+短袖+毛衣
        // 出门前发现外面下雪又穿了件夹克套在外面御寒
        Jacket jacket = new Jacket(sweater);
        System.out.println(jacket.dress());// 裸体+睡衣+短袖+毛衣+夹克


    }
}
