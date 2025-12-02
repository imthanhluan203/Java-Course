package Section22.JDBCChallenges;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class FirstJDBCChallenge {
    public static void main(String[] args) {
        var dataSourse = SetupDataBase("storefront");
        try {
            Connection connection = dataSourse.getConnection();
            System.out.println("Connect to database successfully !!!!");
            Statement statement = connection.createStatement();
            resetAutoIncrement(statement);
            int num_delete = deleteData(statement,1);
            Date fisrtOrder = Date.valueOf("2003-01-13");
            String[] firstOrderDescription = {
                    "Mastering Spring Boot: From Basics to Advanced Concepts",
                    "Learn Python in One Day and Learn It Well",
                    "100 Exercises for Practicing Python"
            };
            addData(statement,fisrtOrder,firstOrderDescription);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static void resetAutoIncrement(Statement statement) throws SQLException {
        // Lưu ý: Lệnh này xóa SẠCH dữ liệu và reset ID về 1
        String truncate_order = "TRUNCATE TABLE `order`";
        String truncate_details = "TRUNCATE TABLE order_details";
        statement.execute("SET FOREIGN_KEY_CHECKS = 0");
        // Nếu bảng cha có khóa ngoại (ON DELETE CASCADE), chỉ cần truncate bảng cha là đủ
        // Nhưng cẩn thận hơn, ta nên TRUNCATE cả 2 bảng.
        statement.executeUpdate(truncate_details); // Bảng con trước
        statement.executeUpdate(truncate_order);   // Bảng cha sau
        statement.execute("SET FOREIGN_KEY_CHECKS = 1");
        System.out.println("Tất cả dữ liệu đã được xóa và Auto Increment đã reset về 1.");
    }
    private static MysqlDataSource SetupDataBase(String databaseName){
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
        datasourse.setDatabaseName(databaseName);
        return datasourse;
    }
    private static void addData(Statement statement, Date orderDate,String[] item_description) throws SQLException {
        String query_order = "INSERT INTO `order`(order_date) VALUES('%s')";
        statement.executeUpdate(query_order.formatted(orderDate),Statement.RETURN_GENERATED_KEYS);
        var order_id = statement.getGeneratedKeys();
        int ordered_id = (order_id!=null && order_id.next())?order_id.getInt(1) : -1;
        String query_order_detail = "INSERT INTO order_details(item_description,order_id) VALUES('%s',%d)";
        for(int i=0;i<item_description.length;i++){
            statement.executeUpdate(query_order_detail.formatted(item_description[i],ordered_id), Statement.RETURN_GENERATED_KEYS);
        }
    }
    private static int deleteData(Statement statement, int id) throws SQLException {
        String query_order = "DELETE FROM `order` WHERE order_id=%d";
        int num_row_delete = statement.executeUpdate(query_order.formatted(id));
        return num_row_delete;
    }
}
