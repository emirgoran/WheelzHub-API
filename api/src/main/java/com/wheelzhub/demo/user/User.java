package com.wheelzhub.demo.user;

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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rent> rents = new ArrayList<>();

    public void addRent(Rent rent) {
        rents.add(rent);
        rent.setUser(this);
    }

    public void removeRent(Rent rent) {
        rents.remove(rent);
        rent.setUser(null);
    }
}
