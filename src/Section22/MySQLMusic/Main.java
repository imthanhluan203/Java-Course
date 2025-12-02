package Section22.MySQLMusic;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    private final static String CONN_STRING = "jdbc:mysql://localhost:3306/music";
    private final static String USER_NAME = "imthanhluan";
    private final static String PASSWORD = "13012003";
    public static void main(String[] args) {
        var dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setPort(3306);
        dataSource.setDatabaseName("music");
        try {
            Connection connection = dataSource.getConnection(USER_NAME,PASSWORD);
            System.out.println("Success!! Connection made to music database");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
