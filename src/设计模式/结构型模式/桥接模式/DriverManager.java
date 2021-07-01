package 设计模式.结构型模式.桥接模式;

/**
 * 模拟JDBC驱动
 * Driver管理类
 * 注册Driver类，获取连接
 */
public class DriverManager {
    public static Driver driver = null;

    /**
     * 注册驱动
     * @param driver
     */
    public static void registerDriver(Driver driver) {
        DriverManager.driver = driver;
    }

    /**
     * 获取连接
     * @return
     */
    public static Connection getConnection(String url) {
        return driver.getConnect(url);
    }
}
