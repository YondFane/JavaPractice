package 设计模式.行为型模式.职责链模式;

/**
 * 处理器抽象类
 */
public abstract class Handle {
    private Handle successor = null;

    /**
     * 设置下一个处理器
     * @param handle
     */
    public void setSuccessor(Handle handle) {
        this.successor = handle;
    }

    /**
     * 处理方法
     */
    public void handle() {
        boolean success = false;
        // 执行处理逻辑
        success = doHandle();
        if (success && successor != null) {
            successor.handle();
        }
    }

    /**
     * 子类实现的处理方法
     */
    protected abstract boolean doHandle();
}
