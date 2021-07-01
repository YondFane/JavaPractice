package 设计模式.结构型模式.桥接模式;

/**
 * 模拟JDBC驱动
 * 数据库连接类
 */
public class Connection {

    private String connection;

    private String url;

    public Connection(String connection) {
        this.connection = connection;
    }

    public String getConnection() {
        return connection;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Connection{" +
                "connection='" + connection + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
