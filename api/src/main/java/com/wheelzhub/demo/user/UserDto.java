package com.wheelzhub.demo.user;

import com.wheelzhub.demo.Dto;
import lombok.Data;

@Data
public class UserDto implements Dto {
    private Long id;
    private String username;

    @Override
    public User toDatabaseEntity() {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        return new User();
    }
}
