package 设计模式.行为型模式.职责链模式;

/**
 * 处理器A
 */
public class HandleA extends Handle {
    @Override
    protected boolean doHandle() {
        // .....执行处理A逻辑
        System.out.println("处理器A执行");
        return true;
    }
}
