package 设计模式.行为型模式.职责链模式;

/**
 * 处理器B
 */
public class HandleB extends Handle {
    @Override
    protected boolean doHandle() {
        // .....执行处理B逻辑
        System.out.println("处理器B执行");
        return true;
    }
}
