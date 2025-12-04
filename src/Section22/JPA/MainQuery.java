package Section22.JPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Tuple;

import java.util.List;
import java.util.stream.Stream;

public class MainQuery {
    public static void main(String[] args) {
        List<Artist> artists = null;
        try (var emf = Persistence.createEntityManagerFactory
                ("dev.luan.music");
        var em = emf.createEntityManager()) {
            var transaction = em.getTransaction();
            transaction.begin();
            artists = getArtistJPQL(em,"%Greatest Hits%");
            artists.forEach(System.out::println);
//            var artistsName = getArtistNames(em,"%Greatest Hits%");
//            artistsName.map(x -> new Artist(x.get(0,Integer.class),
//                    x.get(1,String.class))).forEach(System.out::println);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static List<Artist> getArtistJPQL(EntityManager em,String matchedVal){
        String jpql = "SELECT a FROM Artist a JOIN albums album " +
                "WHERE album.album_name like ?1 or album.album_name like ?2";
        var query = em.createQuery(jpql,Artist.class);
        query.setParameter(1,matchedVal);
        query.setParameter(2,"%Best of%");
        return query.getResultList();
    }
    private static Stream<Tuple> getArtistNames(EntityManager em, String matchedVal){
        String jpql = "SELECT a.artist_id, a.artist_name FROM Artist a where a.artist_name like ?1";
        var query = em.createQuery(jpql,Tuple.class);
        query.setParameter(1,matchedVal);
        return query.getResultStream();
    }
}
