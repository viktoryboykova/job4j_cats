package ru.job4j.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "cats")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int age;

    private String coatColor;

    private String eyesColor;

    @ManyToOne
    @JoinColumn(name = "cat_breed_id", foreignKey = @ForeignKey(name = "CAT_BREED_ID_FK"))
    private CatBreed catBreed;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "cat_owner", joinColumns = {
            @JoinColumn(name = "owner_id", nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "cat_id", nullable = false, updatable = false)})
    private Set<Owner> owners = new HashSet<>();

    public Cat(String name, int age, String coatColor, String eyesColor, CatBreed catBreed) {
        this.name = name;
        this.age = age;
        this.coatColor = coatColor;
        this.eyesColor = eyesColor;
        this.catBreed = catBreed;
    }

    public Cat() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public CatBreed getCatBreed() {
        return catBreed;
    }

    public void setCatBreed(CatBreed catBreed) {
        this.catBreed = catBreed;
    }

    public Set<Owner> getOwners() {
        return owners;
    }

    public void setOwners(Set<Owner> owners) {
        this.owners = owners;
    }

    public String getCoatColor() {
        return coatColor;
    }

    public void setCoatColor(String coatColor) {
        this.coatColor = coatColor;
    }

    public String getEyesColor() {
        return eyesColor;
    }

    public void setEyesColor(String eyesColor) {
        this.eyesColor = eyesColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cat cat = (Cat) o;
        return id == cat.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", coatColor='" + coatColor + '\'' +
                ", eyesColor='" + eyesColor + '\'' +
                ", catBreed=" + catBreed +
                ", owners=" + owners +
                '}';
    }
}
