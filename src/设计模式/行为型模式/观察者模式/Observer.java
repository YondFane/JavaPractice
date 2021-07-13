package 设计模式.行为型模式.观察者模式;

/**
 * 观察者
 */
public interface Observer {

    /**
     * 更新
     * @param message
     */
    public void update(Message message);
}
