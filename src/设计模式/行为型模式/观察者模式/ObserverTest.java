package 设计模式.行为型模式.观察者模式;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 观察者模式
 */
public class ObserverTest {
    public static void main(String[] args) {
        // 同步阻塞
        Subject subject = new ConcreteSubject();
        Observer observerA = new ConcreteObserver();
        Observer observerB = new ConcreteObserver();
        subject.regisgerObserver(observerA);
        subject.regisgerObserver(observerB);
        Message successMessage = new SuccessMessage();
        subject.notifyObservers(successMessage);
        // 异步阻塞 使用线程池进行执行通知
        Executor executorService = Executors.newFixedThreadPool(5);
        Subject concurrentSubject = new ConcreteSubject(executorService);
        concurrentSubject.regisgerObserver(observerA);
        concurrentSubject.regisgerObserver(observerB);
        concurrentSubject.notifyObservers(successMessage);
    }
}
