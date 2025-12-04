package Section22.JPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        try (var sessionFactory = Persistence.createEntityManagerFactory(
                "dev.luan.music");
             EntityManager entityManager = sessionFactory.createEntityManager();) {
            var transaction = entityManager.getTransaction();
            transaction.begin();
            var artist = entityManager.find(Artist.class,137);
            System.out.println(artist);
            artist.removeDuplicates();
            System.out.println(artist);
            //var artist = new Artist(224,"NguyenThanhLuan");
            //entityManager.merge(artist);
            //entityManager.persist(new Artist("LuanNguyen"));
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
