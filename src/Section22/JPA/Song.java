package Section22.JPA;

import jakarta.persistence.*;

@Entity
@Table(name = "songs")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "song_id")
    private int song_id;
    @Column(name = "track_number")
    private int track_number;
    @Column(name = "song_title")
    private String song_title;
    @Column(name = "album_id")
    private int album_id;

    public Song() {
    }

    public int getTrack_number() {
        return track_number;
    }

    public void setTrack_number(int track_number) {
        this.track_number = track_number;
    }

    public String getSong_title() {
        return song_title;
    }

    public void setSong_title(String song_title) {
        this.song_title = song_title;
    }

    public int getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }

    @Override
    public String toString() {
        return "Song{" +
                "song_id=" + song_id +
                ", track_number=" + track_number +
                ", song_title='" + song_title + '\'' +
                ", album_id=" + album_id +
                '}';
    }
}
