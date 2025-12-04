package Section22.JPA;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "albums")
public class Album implements Comparable<Album> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_id")
    private int album_id;
    @Column(name = "album_name")
    private String album_name;
    @Column(name = "artist_id")
    private int artist_id;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "album_id")
    private List<Song> songs = new ArrayList<>();
    public Album() {
    }

    public Album(String album_name) {
        this.album_name = album_name;
    }

    public Album(String album_name, int artist_id) {
        this.album_name = album_name;
        this.artist_id = artist_id;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }

    public int getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(int artist_id) {
        this.artist_id = artist_id;
    }

    @Override
    public String toString() {
        return "Album{" +
                "album_id=" + album_id +
                ", album_name='" + album_name + '\'' +
                ", artist_id=" + artist_id +
                ", songs=" + songs +
                '}';
    }

    @Override
    public int compareTo(Album o) {
        return this.album_name.compareTo(o.album_name);
    }
}
