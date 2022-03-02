package ru.job4j.store;

import ru.job4j.model.*;

import java.util.List;

public interface Store {
    Cat save(Cat cat);
    Cat findCatById(int id);
    List<Cat> findAllCats();

    Ad save(Ad ad);
    Ad findAdById(int id);
    List<Ad> findAllAds();
    boolean deleteAd(Ad ad);

    CatBreed save(CatBreed catBreed);
    CatBreed findCatBreedById(int id);
    List<CatBreed> findAllCatBreeds();

    Owner save(Owner owner);
    Owner findOwnerBreedById(int id);
    List<Owner> findAllOwners();

    User save(User user);
    User findUserByEmail(String email);
    List<User> findAllUsers();
    boolean deleteUser(User user);
}
