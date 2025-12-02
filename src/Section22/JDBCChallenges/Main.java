package Section22.JDBCChallenges;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Main {
    private static String USE_SCHEMA = "USE storefront";
    public static void main(String[] args) {
        Properties props = new Properties();
        try {
            props.load(Files.newInputStream(Path.of("src/Section22/QueryMusic/music.properties"),
                    StandardOpenOption.READ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        var datasourse = new MysqlDataSource();
        datasourse.setServerName(props.getProperty("serverName"));
        datasourse.setPort(Integer.parseInt(props.getProperty("port")));
        datasourse.setUser(props.getProperty("user"));
        datasourse.setPassword(props.getProperty("password"));
        datasourse.setDatabaseName(props.getProperty("databaseName"));
        try {
            Connection connection = datasourse.getConnection();
            if(checkSchema(connection) == false){
                System.out.println("storefront schema does not exist");
                setUpSchema(connection);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static boolean checkSchema(Connection conn){
        try {
            Statement statement = conn.createStatement();
            statement.execute(USE_SCHEMA);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    private static void setUpSchema(Connection conn) throws SQLException{
        String createSchema = "CREATE SCHEMA storefront";
        String createOrder = """
                CREATE TABLE storefront.order(
                order_id int NOT NULL AUTO_INCREMENT,
                order_date DATETIME NOT NULL,
                PRIMARY KEY (order_id)
                )
                """;
        String createOrderDetails = """
                CREATE TABLE storefront.order_details(
                order_detail_id int NOT NULL AUTO_INCREMENT,
                item_description text,
                order_id int DEFAULT NULL,
                PRIMARY KEY (order_detail_id),
                KEY FK_ORDERID (order_id),
                CONSTRAINT FK_ORDERID FOREIGN KEY(order_id)
                REFERENCES storefront.order(order_id) ON DELETE CASCADE
                )
                """;
        Statement statement = conn.createStatement();
        System.out.println("Creating storefront Database");
        statement.execute(createSchema);
        if(checkSchema(conn)){
            statement.execute(createOrder);
            System.out.println("Successful create order");
            statement.execute(createOrderDetails);
            System.out.println("Successful create order detail");

        }
    }
}
