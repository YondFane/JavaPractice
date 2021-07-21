package 设计模式.行为型模式.职责链模式;

/**
 * 处理链
 * 使用链表方式储存处理器
 */
public class HandleChain {

    private Handle head;
    private Handle tail;

    /**
     * 添加处理器
     * @param handle
     */
    public void addHandle(Handle handle) {
        if (head == null) {
            head = handle;
            tail = handle;
        } else {
            tail.setSuccessor(handle);
            tail = handle;
        }
    }

    /**
     * 执行处理链上的处理器
     */
    public void handle() {
        if (head != null) {
            head.handle();
        }
    }
}
