package 设计模式.行为型模式.观察者模式;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * 一个具体订阅主题
 */
public class ConcreteSubject implements Subject{
    // 观察者集合
    private List<Observer> observers = new ArrayList<>();

    private Executor executor;

    public ConcreteSubject() {}

    public ConcreteSubject(Executor executor) {
        this.executor = executor;
    }

    /**
     * 订阅
     * @param observer
     */
    @Override
    public void regisgerObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * 取消订阅
     * @param observer
     */
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    /**
     * 通知观察者
     * @param message
     */
    @Override
    public void notifyObservers(Message message) {
        for(Observer observer : observers) {
            if (executor != null) {
                /**
                 * 异步阻塞
                 */
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("线程执行开始：");
                        observer.update(message);
                        System.out.println("线程执行结束：");
                    }
                });
            } else {
                /**
                 * 同步阻塞
                 */
                observer.update(message);
            }
        }
    }
}
