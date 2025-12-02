package Section22.QueryMusic;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class Main {
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

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the artist id:");
        //String albumName = "Tapestry";
        //int artistid = Integer.parseInt(scanner.nextLine());
        String query  = "SELECT * FROM music.artists  limit %d".formatted(10);
        try {
            Connection connection = datasourse.getConnection();
            System.out.println("Connect database successfully");
            Statement statement = connection.createStatement();
            var resultSet = statement.executeQuery(query);
            System.out.println(resultSet.getMetaData().getColumnCount());
            for(int i =1;i<=resultSet.getMetaData().getColumnCount();i++){
                System.out.printf("%-15s",resultSet.getMetaData().getColumnName(i).toUpperCase());
            }
            System.out.println();
            while (resultSet.next()){
                for(int i =1;i<=resultSet.getMetaData().getColumnCount();i++){
                    System.out.printf("%-15s",resultSet.getString(i));
                }
                System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
