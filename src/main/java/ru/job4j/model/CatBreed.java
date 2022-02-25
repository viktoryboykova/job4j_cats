package ru.job4j.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cat_breeds")
public class CatBreed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String breedName;
    private String coatColor;
    private String eyesColor;

    public CatBreed(String breedName, String coatColor, String eyesColor) {
        this.breedName = breedName;
        this.coatColor = coatColor;
        this.eyesColor = eyesColor;
    }

    public CatBreed() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
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
        CatBreed catBreed = (CatBreed) o;
        return id == catBreed.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "CatBreed{" +
                "id=" + id +
                ", breedName='" + breedName + '\'' +
                ", coatColor='" + coatColor + '\'' +
                ", eyesColor='" + eyesColor + '\'' +
                '}';
    }
}
