package 设计模式.结构型模式.外观模式;

/**
 * 实现IUserController接口方法
 */
public class UserController implements IUserController{
    @Override
    public void createUser() {
        System.out.println("用户创建成功！");
    }
}
