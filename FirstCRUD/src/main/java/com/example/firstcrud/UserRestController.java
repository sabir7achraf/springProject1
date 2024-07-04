package com.example.firstcrud;

import com.example.firstcrud.user.User;
import com.example.firstcrud.user.UserService;
import com.example.firstcrud.payement.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserRestController {

    UserService userService;
    PayementRepository payementRepository;
    public UserRestController(UserService userService,PayementRepository payementRepository) {
        this.userService = userService;
        this.payementRepository=payementRepository;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
       List<User> users = userService.findAll();
       return users;
    }

    @GetMapping("/payments")
    public List<Payement> getAllpayements(){
       return payementRepository.findAll();
    }

    @GetMapping("/payement/{code}")
    public List<Payement> GetPaymentbycode(@PathVariable long code ){
        return payementRepository.findByUser_code(code);
    }



}
