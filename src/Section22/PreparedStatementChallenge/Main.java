package Section22.PreparedStatementChallenge;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.List;

public class Main {
    private static String SERVER_NAME = "localhost";
    private static String ORDER_INSERT = "INSERT INTO storefront.order (order_date) VALUES(?)";
    private static String ORDER_DETAIL_INSERT = "INSERT INTO storefront.order_details (item_description,order_id,quantity) VALUES(?,?,?)";
    private static int PORT = 3306;
    public static void main(String[] args) {
        var dataSourse = new MysqlDataSource();
        dataSourse.setServerName(SERVER_NAME);
        dataSourse.setPort(3306);
        dataSourse.setDatabaseName("storefront");
        try {
            var connection = dataSourse.getConnection("imthanhluan","13012003");
            System.out.println("Connect database successfully");
            resetAutoIncrement(connection.createStatement());
            addData(connection);
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
    private static void addData(Connection connection) throws SQLException {
        List<String> records = null;
        try {
            records = Files.readAllLines(Path.of("src/Section22/PreparedStatementChallenge/Orders.csv"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps_orderDetails = connection.prepareStatement(ORDER_DETAIL_INSERT,Statement.RETURN_GENERATED_KEYS);
            PreparedStatement ps_order = connection.prepareStatement(ORDER_INSERT,Statement.RETURN_GENERATED_KEYS);
            int orderId = -1;
            for(String record : records){
                String[] data = record.split(",");
                if(data.length == 2){
                    orderId = addOrder(ps_order,java.sql.Timestamp.valueOf(data[1]));
                }else {
                    addOrderDetail(ps_orderDetails,data[2],orderId,Integer.parseInt(data[1]));
                }
            }
            connection.commit();
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        }
    }
    private static int addOrder(PreparedStatement ps,Timestamp orderDate) throws SQLException {
        int orderId = -1;
        ps.setTimestamp(1,orderDate);
        var rowUpdate = ps.executeUpdate();
        if(rowUpdate > 0){
            var resultSet = ps.getGeneratedKeys();
            if(resultSet.next()){
                orderId = resultSet.getInt(1);
                System.out.printf("Order with Id=%d added%n",orderId);
            }
        }
        return orderId;
    }
    private static int addOrderDetail(PreparedStatement ps,String item_description,int order_id,int quantity) throws SQLException {
        int orderDetail = -1;
        ps.setString(1,item_description);
        ps.setInt(2,order_id);
        ps.setInt(3,quantity);
        var rowUpdate = ps.executeUpdate();
        if(rowUpdate > 0){
            var resultSet = ps.getGeneratedKeys();
            if(resultSet.next()){
                orderDetail = resultSet.getInt(1);
                System.out.printf("OrderDetail with Id=%d added%n",orderDetail);
            }
        }
        return orderDetail;
    }
}
