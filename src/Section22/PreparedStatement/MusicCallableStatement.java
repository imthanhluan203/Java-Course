package Section22.PreparedStatement;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;
import java.util.stream.Collectors;

public class MusicCallableStatement {
    private static final int ARTIST_COLUMN = 0;
    private static final int ALBUM_COLUMN = 1;
    private static final int SONG_COLUMN = 3;
    private static String SERVER_NAME = "localhost";
    private static int PORT = 3306;
    public static void main(String[] args) {
        Map<String, Map<String,String>> albums = null;
        try {
            var lines = Files.lines(Path.of("src/Section22/PreparedStatement/NewAlbums.csv"));
            albums = lines.map(s -> s.split(","))
                    .collect(Collectors.groupingBy(s->s[ARTIST_COLUMN],
                            Collectors.groupingBy(s->s[ALBUM_COLUMN],
                                    Collectors.mapping(s->s[SONG_COLUMN],
                                            Collectors.joining(
                                                    "\",\"",
                                                    "[\"",
                                                    "\"]"
                                            )))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        albums.forEach((k,v)->{
            System.out.println(k);
            v.forEach((k1,v1)->{
                System.out.println("-".repeat(10) + k1 +":" + v1);
            });
        });
        var dataSourse = new MysqlDataSource();
        dataSourse.setServerName(SERVER_NAME);
        dataSourse.setPort(3306);
        try {
            var connection = dataSourse.getConnection("imthanhluan","13012003");
            System.out.println("Connect database successfully");
            var cs = connection.prepareCall(
                    "CALL music.addAlbumInOutCounts(?,?,?,?)"
            );
            albums.forEach((artist, albumMap)->{
                albumMap.forEach((album,song)->{
                    try {
                        cs.setString(1,artist);
                        cs.setString(2,album);
                        cs.setString(3,song);
                        //cs.setInt(4,10);
                        cs.registerOutParameter(4, Types.INTEGER);
                        cs.execute();
                        System.out.printf("%d songs were added for %s%n",cs.getInt(4),album);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
            });
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
}
