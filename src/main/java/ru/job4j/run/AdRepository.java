package ru.job4j.run;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.model.Ad;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

public class AdRepository {
    public static void main(String[] args) {
        List<Ad> adsToday = null;
        List<Ad> activeAds = null;
        List<Ad> catBreedKaoMani = null;

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            adsToday = session.createQuery(
                            "select distinct a from Ad a "
                                    + "join fetch a.cat cat "
                                    + "join fetch a.creator cr "
                                    + "join fetch cat.catBreed "
                                    + "left join fetch cat.owners "
                                    + "where a.created between :cStart and :cEnd", Ad.class
                    )
                    .setParameter("cStart", Timestamp.valueOf(LocalDate.now().atStartOfDay()))
                    .setParameter("cEnd", Timestamp.valueOf(LocalDate.now().plusDays(1).atStartOfDay()))
                    .list();

            activeAds = session.createQuery(
                            "select distinct a from Ad a "
                                    + "join fetch a.cat cat "
                                    + "join fetch a.creator cr "
                                    + "join fetch cat.catBreed "
                                    + "left join fetch cat.owners "
                                    + "where a.active = :active", Ad.class
                    )
                    .setParameter("active", true)
                    .list();

            catBreedKaoMani = session.createQuery(
                            "select distinct a from Ad a "
                                    + "join fetch a.cat cat "
                                    + "join fetch a.creator cr "
                                    + "join fetch cat.catBreed "
                                    + "left join fetch cat.owners "
                                    + "where a.cat.catBreed.breedName = :breedName", Ad.class
                    )
                    .setParameter("breedName", "Као-мани")
                    .list();

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        for (Ad ad : adsToday) {
            System.out.println(ad);
        }
        for (Ad ad : activeAds) {
            System.out.println(ad);
        }
        for (Ad ad : catBreedKaoMani) {
            System.out.println(ad);
        }
    }
}
