package com.wheelzhub.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Returning the DTOs directly instead of wrapping them in ResponseEntity for code simplicity.
// Not the best practice since, in addition to the DTO, the ResponseEntity returns HTTP status and (potentially) custom headers.

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {
        System.out.println("UserDto = " + userDto);
        User savedUser = userService.createUser(userDto).toDatabaseEntity();
        System.out.println("savedUser = " + savedUser.toDto());
        return savedUser.toDto();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (userService.deleteUserById(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search")
    public List<UserDto> searchUsers(
            @RequestParam(required = false) String username
    ) {
        return userService.searchUsers(username);
    }
}