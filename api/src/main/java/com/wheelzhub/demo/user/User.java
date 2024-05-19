package com.wheelzhub.demo.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wheelzhub.demo.image.Image;
import com.wheelzhub.demo.rent.Rent;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "image_id")
    @JsonIgnore
    private Image profilePhoto;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Rent> rents = new ArrayList<>();

    public void addRent(Rent rent) {
        rents.add(rent);
        rent.setUser(this);
    }

    public void removeRent(Rent rent) {
        rents.remove(rent);
        rent.setUser(null);
    }

    public void addProfilePhoto(Image image) {
        this.profilePhoto = image;
        image.setUser(this);
    }

    public void removeProfilePhoto() {
        this.profilePhoto = null;
        this.profilePhoto.setVehicle(null);
    }
}
