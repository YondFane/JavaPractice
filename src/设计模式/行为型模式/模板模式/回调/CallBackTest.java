package 设计模式.行为型模式.模板模式.回调;

public class CallBackTest {
    public static void main(String[] args) {
        UserService userService = new UserService();
        // 注册用户
        userService.register(new ICallBack() {
            /**
             * 回调方法
             */
            @Override
            public void call() {
                System.out.println("用户注册完成执行回调方法");
            }
        });
    }
}
