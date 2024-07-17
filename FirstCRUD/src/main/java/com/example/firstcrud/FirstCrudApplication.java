package com.example.firstcrud;

import com.example.firstcrud.Entity.User;
import com.example.firstcrud.Entity.Roles;
import com.example.firstcrud.Entity.RoleType;
import com.example.firstcrud.Repository.UserRepository;
import com.example.firstcrud.Repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableScheduling
@SpringBootApplication
public class FirstCrudApplication {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RolesRepository rolesRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(FirstCrudApplication.class, args);
    }
}

