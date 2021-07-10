package 设计模式.结构型模式.外观模式;

/**
 * 实现IWalletController接口方法
 */
public class WalletController implements IWalletController{
    @Override
    public void createWallet() {
        System.out.println("用户钱包创建成功！");
    }
}
