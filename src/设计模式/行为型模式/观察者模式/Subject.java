package 设计模式.行为型模式.观察者模式;

/**
 * 订阅主题
 * 被观察者
 */
public interface Subject {

    /**
     * 订阅
     * @param observer
     */
    public void regisgerObserver(Observer observer);

    /**
     * 取消订阅
     * @param observer
     */
    public void removeObserver(Observer observer);

    /**
     * 通知观察者
     * @param message
     */
    public void notifyObservers(Message message);
}
