package ru.job4j.store;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.model.*;

import java.util.List;
import java.util.function.Function;

public class HbmStore implements Store {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();


    @Override
    public Cat save(Cat cat) {
        if (cat.getId() == 0) {
            return createCat(cat);
        } else {
            return updateCat(cat);
        }
    }

    private Cat createCat(Cat cat) {
        return this.function(
                session -> {
                    session.save(cat);
                    return cat;
                }
        );
    }

    private Cat updateCat(Cat cat) {
        return this.function(
                session -> {
                    session.update(cat);
                    return cat;
                }
        );
    }

    @Override
    public Cat findCatById(int id) {
        return this.function(
                session -> session.get(Cat.class, id)
        );
    }

    @Override
    public List<Cat> findAllCats() {
        return this.function(
                session -> session.createQuery(
                        "select distinct c from Cat c join fetch c.owners"
                ).list()

        );
    }

    @Override
    public Ad save(Ad ad) {
        if (ad.getId() == 0) {
            return createAd(ad);
        } else {
            return updateAd(ad);
        }
    }

    private Ad createAd(Ad ad) {
        return this.function(
                session -> {
                    session.save(ad);
                    return ad;
                }
        );
    }

    private Ad updateAd(Ad ad) {
        return this.function(
                session -> {
                    session.update(ad);
                    return ad;
                }
        );
    }

    @Override
    public Ad findAdById(int id) {
        return this.function(
                session -> session.get(Ad.class, id)
        );
    }

    @Override
    public List<Ad> findAllAds() {
        return this.function(
                session -> session.createQuery("from ru.job4j.model.Ad").list()
        );
    }

    @Override
    public boolean deleteAd(Ad ad) {
        return this.function(
                session -> {
                    session.delete(ad);
                    return true;
                }
        );
    }

    @Override
    public CatBreed save(CatBreed catBreed) {
        if (catBreed.getId() == 0) {
            return createCatBreed(catBreed);
        } else {
            return updateCatBreed(catBreed);
        }
    }

    private CatBreed createCatBreed(CatBreed catBreed) {
        return this.function(
                session -> {
                    session.save(catBreed);
                    return catBreed;
                }
        );
    }

    private CatBreed updateCatBreed(CatBreed catBreed) {
        return this.function(
                session -> {
                    session.update(catBreed);
                    return catBreed;
                }
        );
    }

    @Override
    public CatBreed findCatBreedById(int id) {
        return this.function(
                session -> session.get(CatBreed.class, id)
        );
    }

    @Override
    public List<CatBreed> findAllCatBreeds() {
        return this.function(
                session -> session.createQuery("from ru.job4j.model.CatBreed").list()
        );
    }

    @Override
    public Owner save(Owner owner) {
        if (owner.getId() == 0) {
            return createOwner(owner);
        } else {
            return updateOwner(owner);
        }
    }

    private Owner createOwner(Owner owner) {
        return this.function(
                session -> {
                    session.save(owner);
                    return owner;
                }
        );
    }

    private Owner updateOwner(Owner owner) {
        return this.function(
                session -> {
                    session.update(owner);
                    return owner;
                }
        );
    }

    @Override
    public Owner findOwnerBreedById(int id) {
        return this.function(
                session -> session.get(Owner.class, id)
        );
    }

    @Override
    public List<Owner> findAllOwners() {
        return this.function(
                session -> session.createQuery("from ru.job4j.model.Owner").list()
        );
    }

    @Override
    public User save(User user) {
        if (user.getId() == 0) {
            return createUser(user);
        } else {
            return updateUser(user);
        }
    }

    private User createUser(User user) {
        return this.function(
                session -> {
                    session.save(user);
                    return user;
                }
        );
    }

    private User updateUser(User user) {
        return this.function(
                session -> {
                    session.update(user);
                    return user;
                }
        );
    }

    @Override
    public User findUserByEmail(String email) {
        return this.function(
                session -> {
                    List<User> result = session.createQuery("from ru.job4j.model.User where email =:email ")
                            .setParameter("email", email)
                            .list();
                    if (result.isEmpty()) {
                        return  null;
                    }
                    return result.get(0);
                }
        );
    }

    @Override
    public List<User> findAllUsers() {
        return this.function(
                session -> session.createQuery("from ru.job4j.model.User").list()
        );
    }

    @Override
    public boolean deleteUser(User user) {
        return this.function(
                session -> {
                    session.delete(user);
                    return true;
                }
        );
    }

    private <T> T function(final Function<Session, T> command) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            T rsl = command.apply(session);
            session.getTransaction().commit();
            return rsl;
        } catch (HibernateException e) {
            throw e;
        }
    }
}
