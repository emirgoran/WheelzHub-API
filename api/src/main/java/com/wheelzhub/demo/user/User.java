package com.wheelzhub.demo.user;

import com.wheelzhub.demo.DatabaseEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User implements DatabaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    @Override
    public UserDto toDto() {
        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setUsername(username);
        userDto.setPassword(password);
        return userDto;
    }
}
