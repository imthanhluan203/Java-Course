package Section22.PreparedStatement;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static String SERVER_NAME = "localhost";
    private static int PORT = 3306;
    private static String ARTIST_INSERT = "INSERT INTO  music.artists (artist_name) VALUES (?)";
    private static String ALBUM_INSERT = "INSERT INTO  music.albums (artist_id,album_name) VALUES (?,?)";
    private static String SONG_INSERT = "INSERT INTO  music.songs (album_id,track_number,song_title) VALUES (?,?,?)";

    public static void main(String[] args) {
        var dataSourse = new MysqlDataSource();
        dataSourse.setServerName(SERVER_NAME);
        dataSourse.setPort(3306);
        try {
            var connection = dataSourse.getConnection("imthanhluan","13012003");
            System.out.println("Connect database successfully");
            addData(connection);

            String sql = "SELECT * FROM music.albumview WHERE artist_name  = ?";
            var ps = connection.prepareStatement(sql);
            ps.setString(1,"Bob Dylan");
            var resultSet = ps.executeQuery();
            var metadata = resultSet.getMetaData();
            for(int i = 1 ; i <=metadata.getColumnCount();i++){
                System.out.printf("%-15s",metadata.getColumnName(i));
            }
            System.out.println();
            while(resultSet.next()){
                for(int i = 1 ; i <=metadata.getColumnCount();i++){
                    System.out.printf("%-15s",resultSet.getString(i));
                }
                System.out.println();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static int addArtist(PreparedStatement ps, Connection conn,String artistName) throws SQLException {
        int artistId =-1;
        ps.setString(1,artistName);
        int insertedCount = ps.executeUpdate();
        if(insertedCount > 0){
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if(generatedKeys.next()){
                artistId = generatedKeys.getInt(1);
                System.out.println("Auto-incremented ID: " + artistId);
            }
        }
        return artistId;
    }
    private static int addAlbum(PreparedStatement ps, Connection conn,
                                int artistId, String albumName) throws SQLException {
        int albumId =-1;
        ps.setInt(1,artistId);
        ps.setString(2,albumName);
        int insertedCount = ps.executeUpdate();
        if(insertedCount > 0){
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if(generatedKeys.next()){
                albumId = generatedKeys.getInt(1);
                System.out.println("Auto-incremented ID: " + albumId);
            }
        }
        return albumId;
    }
    private static void addSong(PreparedStatement ps, Connection conn,
                                int albumId, int trackNumber, String song_title) throws SQLException {
        int songId =-1;
        ps.setInt(1,albumId);
        ps.setInt(2,trackNumber);
        ps.setString(3,song_title);
        ps.addBatch();
    }
    private static void addData(Connection conn) throws SQLException {
        List<String> records = null;
        try {
            records = Files.readAllLines(Path.of("src/Section22/PreparedStatement/NewAlbums.csv"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String lastArtist = null;
        String lastAlbum = null;
        int artistId = -1;
        int albumId = -1;
        try {
            conn.setAutoCommit(false);
            var psArtist = conn.prepareStatement(ARTIST_INSERT, Statement.RETURN_GENERATED_KEYS);
            var psAlbum = conn.prepareStatement(ALBUM_INSERT, Statement.RETURN_GENERATED_KEYS);
            var psSong = conn.prepareStatement(SONG_INSERT, Statement.RETURN_GENERATED_KEYS);
            for(String record : records){
                String[] columns = record.split(",");
                if(lastArtist == null || !lastArtist.equals(columns[0])){
                    lastArtist = columns[0];
                    artistId = addArtist(psArtist,conn,lastArtist);

                }
                if(lastAlbum == null || !lastAlbum.equals(columns[1])){
                    lastAlbum = columns[1];
                    albumId = addAlbum(psAlbum,conn,artistId,lastAlbum);
                }
                addSong(psSong,conn,albumId,Integer.parseInt(columns[2]),columns[3]);
            }
            var  results = psSong.executeBatch();
            System.out.println("Number record added:" + Arrays.stream(results).sum());
            conn.commit();
            conn.setAutoCommit(true);

        } catch (SQLException e) {
            conn.rollback();
            throw new RuntimeException(e);
        }
    }
}
