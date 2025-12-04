package Section22.JPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SongQuery {
    public static void main(String[] args) {
        try (var emf = Persistence.createEntityManagerFactory("dev.luan.music");
            var em = emf.createEntityManager()) {
//            var cb = em.getCriteriaBuilder();
//            var query = cb.createQuery(Song.class);
//            var root = query.from(Song.class);
//            query.select(root);
//            var result = em.createQuery(query).getResultList().stream();
            var result4 = songMachedTitle(em,"%River%").map(Song::getAlbum_id).collect(Collectors.toSet());
            var result5 = result4.stream().flatMap(x->{
                return albumMatchId(em,x);
            }).map(Album::getArtist_id).collect(Collectors.toSet());
            System.out.println("=".repeat(50));
            var result6 = result5.stream().flatMap(x->{
                return artistMatchId(em,x);
            }).toList();
            System.out.println("=".repeat(50));
            result6.forEach(x->{
                System.out.println("--"+x.getArtist_name());
                x.getAlbums().stream()
                        .map(Album::getAlbum_name)
                        .collect(Collectors.toSet())
                        .forEach(al->{
                            System.out.println("-".repeat(10) + al);
                        });
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private static Stream<Song> songMachedTitle(EntityManager em,String matching){
        var cb = em.getCriteriaBuilder();
        var query = cb.createQuery(Song.class);
        var root = query.from(Song.class);
        query.select(root)
                .where(cb.like(root.get("song_title"),matching));
        return em.createQuery(query).getResultList().stream();
    }
    private static Stream<Album> albumMatchId(EntityManager em,int id){
        var cb = em.getCriteriaBuilder();
        var query = cb.createQuery(Album.class);
        var root = query.from(Album.class);
        query.select(root)
                .where(cb.equal(root.get("album_id"), id));
        return em.createQuery(query).getResultList().stream();
    }
    private static Stream<Artist> artistMatchId(EntityManager em,int id){
        var cb = em.getCriteriaBuilder();
        var query = cb.createQuery(Artist.class);
        var root = query.from(Artist.class);
        query.select(root)
                .where(cb.equal(root.get("artist_id"), id));
        return em.createQuery(query).getResultList().stream();
    }
}
