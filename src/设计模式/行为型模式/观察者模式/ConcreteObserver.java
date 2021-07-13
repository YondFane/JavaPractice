package 设计模式.行为型模式.观察者模式;

/**
 * 一个具体的观察者
 */
public class ConcreteObserver implements Observer {
    @Override
    public void update(Message message) {
        message.send();
    }
}
