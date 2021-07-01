package 设计模式.结构型模式.桥接模式;

/**
 * 模拟JDBC驱动
 * Mysql的Driver类
 */
public class MysqlDriver implements Driver{

    private Connection connection = new Connection("Mysql:Connection");

    static {
        //注册驱动
        DriverManager.registerDriver(new MysqlDriver());
    }

    public MysqlDriver(){

    }

    @Override
    public Connection getConnect(String url) {
        connection.setUrl(url);
        return connection;
    }
}
