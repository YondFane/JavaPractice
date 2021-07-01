package 设计模式.结构型模式.桥接模式;

/**
 * 桥接模式
 *
 */
public class Test {
    /**
     * Class.forName()调用的使用将Driver注册到DriverManager类中
     * 我们只需要更换Driver类的类路径和数据库连接url即可
     * DriverManager类与XXX.XX.Driver类组合
     * 形成了桥接模式
     * Driver类从DriverManager类中独立起来，实现了解耦，真正的业务逻辑代码有Driver类的“实现”来完成
     * @param args
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws ClassNotFoundException {
        // 1、加载注册驱动程序
        Class.forName("设计模式.结构型模式.桥接模式.MysqlDriver");
        // 2、获取连接
        String url = "jdbc:mysql://localhost:3306/test?user=root&password=123456";
        Connection connection = DriverManager.getConnection(url);

        // Connection{connection='Mysql:Connection', url='jdbc:mysql://localhost:3306/test?user=root&password=123456'}
        System.out.println(connection.toString());

        // 1、加载注册驱动程序
        Class.forName("设计模式.结构型模式.桥接模式.OracleDriver");
        // 2、获取连接
        String url2 = "jdbc:oracle:thin:@localhost:3306/test?user=root&password=123456";
        Connection connection2 = DriverManager.getConnection(url2);

        // Connection{connection='Oracle:Connection', url='jdbc:oracle:thin:@localhost:3306/test?user=root&password=123456'}
        System.out.println(connection2.toString());

    }
}
