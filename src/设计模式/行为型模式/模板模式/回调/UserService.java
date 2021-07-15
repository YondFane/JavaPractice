package 设计模式.行为型模式.模板模式.回调;

/**
 * 用户业务Service
 */
public  class UserService {

    /**
     * 模板方法
     * 用户注册
     * @param callBack
     */
    public void register(ICallBack callBack) {
        System.out.println("用户注册成功！");
        callBack.call();
    }
}
