package Section22.JPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CriteriaBuilderMain {
    public static void main(String[] args) {
        try (var efm = Persistence.createEntityManagerFactory("dev.luan.music");
             var em = efm.createEntityManager()) {
            var cb =em.getCriteriaBuilder();
            CriteriaQuery<Artist> query = cb.createQuery(Artist.class);
            Root<Artist> artists = query.from(Artist.class);
            query.select(artists);
             var result = getArtistBuider(em,"Bl%");
             var map = result.limit(10)
                     .collect(Collectors.toMap(Artist::getArtist_name,
                                             a->a.getAlbums().size()
                                            ));
             map.forEach((k,v)->System.out.println(k+":"+v));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static Stream<Artist> getArtistBuider(EntityManager em,String matched){
        var cb = em.getCriteriaBuilder();
        var query = cb.createQuery(Artist.class);
        var artists = query.from(Artist.class);
        query.select(artists);
        query.where(cb.like(artists.get("artist_name"),matched));
        query.orderBy(cb.asc(artists.get("artist_name")));
        return em.createQuery(query).getResultStream();
    }
}
