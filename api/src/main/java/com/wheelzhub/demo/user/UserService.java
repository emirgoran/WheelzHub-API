package com.wheelzhub.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDto> searchUsers(String username) {
        Specification<User> spec = Specification
                .where(UserSpecifications.hasUsername(username))
                .and(UserSpecifications.hasUsername(username));

        return userRepository.findAll(spec).stream().map(user -> user.toDto()).collect(Collectors.toList());
    }

    public List<UserDto> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(user -> user.toDto())
                .collect(Collectors.toList());
    }

    public UserDto createUser(UserDto userDto) {
        User user = userDto.toDatabaseEntity();

        User savedUser = userRepository.save(user);

        userDto.setId(savedUser.getId());

        return userDto;
    }

    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found."));

        return user.toDto();
    }

    public boolean deleteUserById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}