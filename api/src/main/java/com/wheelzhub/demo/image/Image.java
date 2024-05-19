package com.wheelzhub.demo.image;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wheelzhub.demo.user.User;
import com.wheelzhub.demo.vehicle.Vehicle;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;

    @Column(columnDefinition = "mediumblob")
    private byte[] data;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    @JsonIgnore
    private Vehicle vehicle;
}

