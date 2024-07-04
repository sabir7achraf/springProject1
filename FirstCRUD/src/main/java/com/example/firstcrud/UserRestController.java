package com.example.firstcrud;

import com.example.firstcrud.user.User;
import com.example.firstcrud.user.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserRestController {

    UserService userService;
    public UserRestController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/users")
    public List<User> getAllUsers() {
       List<User> users = userService.findAll();
       return users;
    }

}
