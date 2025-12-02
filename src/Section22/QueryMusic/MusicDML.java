package Section22.QueryMusic;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Properties;

public class MusicDML {
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
            System.out.println("Connect to database successfully");
            Statement statement = connection.createStatement();
            deleteArtistAlbum(connection,statement,"Image Dragon","My favorite Album");
            executeSelect(statement,"music.albumview","album_name","My favorite Album");
            executeSelect(statement,"music.albums","album_name","My favorite Album");
//            String tableName = "music.artists";
//            String columnName = "artist_name";
//            String columnValue = "DINO";
//            deleteRecord(statement,)
  //           insertSong(statement,"My favorite Album", "Image Dragon");
//            if(!result){
//                System.out.println("Fetch data failure");
//                insertRecord(statement,tableName,new String[]{columnName},new String[]{columnValue});
//            }else {
//                System.out.println("DO some thing");
//                //deleteRecord(statement,tableName,columnName,columnValue);
//                updateRecord(statement,tableName,columnName,columnValue.toLowerCase(),columnName,columnValue);
//            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private  static  boolean printRecord(ResultSet resultSet) throws SQLException {
        boolean foundData = false;
        for(int i =1;i<=resultSet.getMetaData().getColumnCount();i++){
            System.out.printf("%-15s",resultSet.getMetaData().getColumnName(i).toUpperCase());
        }
        System.out.println();
        while (resultSet.next()){
            for(int i =1;i<=resultSet.getMetaData().getColumnCount();i++){
                System.out.printf("%-15s",resultSet.getString(i));
            }
            System.out.println();
            foundData =true;
        }
        return  foundData;
    }
    private static boolean executeSelect(Statement statement,String table,
                                         String columnName,String columnValue) throws SQLException {
        String query = "SELECT * FROM %s WHERE %s ='%s'".formatted(table,columnName,columnValue);
        System.out.println(query);
        var rs = statement.executeQuery(query);
        if(rs != null){
            return printRecord(rs);
        }
        return false;
    }
    private static boolean insertRecord(Statement statement,String table,
                                         String[] columnName,String[] columnValue) throws SQLException {
        String query = "INSERT INTO %s(%s) VALUES('%s')"
                .formatted(table,
                        String.join(",",columnName),
                        String.join("','",columnValue));
        System.out.println(query);
        boolean insertResult =statement.execute(query);
        int recordsInserted = statement.getUpdateCount();
        if(recordsInserted > 0){
            executeSelect(statement,table,
                    String.join(",",columnName)
                    ,String.join("','",columnValue));
        }
        return recordsInserted > 0;
    }
    private static boolean deleteRecord(Statement statement,String table,
                                         String columnName,String columnValue) throws SQLException {
        String query = "DELETE FROM %s WHERE %s = '%s'"
                .formatted(table,
                        columnName,
                        columnValue);
        System.out.println(query);
        boolean deleteResult =statement.execute(query);
        int recordsDelete = statement.getUpdateCount();
        if(recordsDelete > 0){
            executeSelect(statement,table,
                    columnName
                    ,columnValue);
        }
        return recordsDelete > 0;
    }
    private static boolean updateRecord(Statement statement,String table,
                                        String columnMatch,String valueMatch,
                                        String columnName,String columnValue) throws SQLException {
        String query = "UPDATE %s SET %s = '%s' WHERE %s = '%s'"
                .formatted(table,
                        columnMatch,
                        valueMatch,
                        columnName,
                        columnValue);
        System.out.println(query);
        boolean updateResult =statement.execute(query);
        int recordsUpdate = statement.getUpdateCount();
        if(recordsUpdate > 0){
            executeSelect(statement,table,
                    columnName
                    ,columnValue);
        }
        return recordsUpdate > 0;
    }
    private static void insertSong(Statement statement,String albumName,String artistName) throws SQLException {
        String query = "INSERT INTO music.artists(artist_name) VALUES(%s)".formatted(statement.enquoteLiteral(artistName));
        System.out.println(query);
        statement.execute(query,Statement.RETURN_GENERATED_KEYS);
        var result_Artist = statement.getGeneratedKeys();
        int artist_id = (result_Artist != null && result_Artist.next()) ? result_Artist.getInt(1):-1;
        query = "INSERT INTO music.albums(album_name,artist_id) VALUES(%s,%d)".formatted(statement.enquoteLiteral(albumName),artist_id);
        statement.execute(query,Statement.RETURN_GENERATED_KEYS);
        var result_Album = statement.getGeneratedKeys();
        int album_id = (result_Album != null && result_Album.next()) ? result_Album.getInt(1):-1;

        String[] song_title = {
                "Imagine Dragons - Believer (Official Music Video)",
                "Imagine Dragons - Thunder",
                "Imagine Dragons - Demons (Official Music Video)\n"
        };
        query = "INSERT INTO music.songs(track_number,song_title,album_id) VALUES(%d,%s,%d)";
        for(int i=0;i<song_title.length;i++){
            String query_temp = query.formatted(i+1,statement.enquoteLiteral(song_title[i]),album_id);
            System.out.println(query_temp);
            statement.execute(query_temp);
        }
        executeSelect(statement,"music.albumview","album_name",albumName);
    }
    private static void deleteArtistAlbum(Connection connection,Statement statement,
                                          String artistName, String albumName) throws SQLException {
        try{
            System.out.println("Current commit status:" + connection.getAutoCommit());
            connection.setAutoCommit(false);
            String deleteSongs = """
                DELETE FROM music.songs WHERE  album_id IN 
                (SELECT album_id FROM music.albums WHERE album_name='%s')
                """.formatted(albumName);
//            int deletedSongs = statement.executeUpdate(deleteSongs);
//            System.out.printf("Deleted %d rows from musics.songs%n".formatted(deletedSongs));
            String deleteAlbums = "DELETE FROM music.albums WHERE album_name='%s'".formatted(albumName);
//            int deletedAlbums = statement.executeUpdate(deleteAlbums);
//            System.out.printf("Delete %d rows from music.albums%n",deletedAlbums);
            String deleteArtist = "DELETE FROM music.artists WHERE artist_name='%s'".formatted(artistName);
//            int deletedArtist = statement.executeUpdate(deleteArtist);
//            System.out.printf("Delete %d rows from music.artists%n",deletedArtist);
            statement.addBatch(deleteSongs);
            statement.addBatch(deleteAlbums);
            statement.addBatch(deleteArtist);
            var results = statement.executeBatch();
            System.out.println(Arrays.toString(results));
            connection.commit();
        }catch (SQLException e){
            e.printStackTrace();
            connection.rollback();
        }finally {
            connection.setAutoCommit(true);
        }
    }
}
