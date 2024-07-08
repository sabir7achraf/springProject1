package com.example.firstcrud;

import com.example.firstcrud.user.*;
import com.example.firstcrud.payement.*;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.io.IOException;
import java.util.List;

@RestController
public class UserRestController {

    UserService userService;
    PayementRepository payementRepository;
    PasswordEncoder passwordEncoder;

    public UserRestController(UserService userService,PayementRepository payementRepository,PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.payementRepository=payementRepository;
        this.passwordEncoder=passwordEncoder;
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

    @PostMapping(path = "/user" ,consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public void saveuser(@RequestParam MultipartFile file, Long code,String password,String email,String lastName,String firstName) throws IOException {
        Roles roles=new Roles();
        roles.setRoleType(RoleType.UTULISATEUR);
        password=passwordEncoder.encode(password);
       Validation validuser= userService.valideUser(userService.save(userService.forImage(file,code,password,email,lastName,firstName,roles)));
       userService.sendEmail(validuser);
    }
}
