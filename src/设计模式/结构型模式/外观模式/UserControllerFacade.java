package 设计模式.结构型模式.外观模式;

public class UserControllerFacade implements IUserControllerFacade{

    /* @Autowired */
    private IUserController userController = new UserController();
    /* @Autowired */
    private IWalletController walletController = new WalletController();

    /**
     * 用户注册
     * 将创建用户和创建钱包整合成一个请求进行处理
     * 提高性能，解决分布式事务问题
     */
    @Override
    public void register() {
        userController.createUser();
        walletController.createWallet();
    }
}
