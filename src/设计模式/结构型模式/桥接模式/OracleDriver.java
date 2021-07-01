package 设计模式.结构型模式.桥接模式;

/**
 * 模拟JDBC驱动
 * Oracle的Driver类
 */
public class OracleDriver implements Driver{
    private Connection connection = new Connection("Oracle:Connection");

    static {
        // 注册驱动
        DriverManager.registerDriver(new OracleDriver());
    }

    public OracleDriver(){

    }

    @Override
    public Connection getConnect(String url) {
        connection.setUrl(url);
        return connection;
    }
}
