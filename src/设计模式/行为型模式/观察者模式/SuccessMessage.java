package 设计模式.行为型模式.观察者模式;

public class SuccessMessage implements Message{

    @Override
    public void send() {
        System.out.println("订阅成功！");
    }
}
