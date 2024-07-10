package com.example.firstcrud;


import com.example.firstcrud.Repository.PayementRepository;
import com.example.firstcrud.Service.RolesService;
import com.example.firstcrud.Service.UserService;
import com.example.firstcrud.Entity.*;
import com.example.firstcrud.Service.ValidationService;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class UserRestController {

    UserService userService;
    PayementRepository payementRepository;
    PasswordEncoder passwordEncoder;
    RolesService rolesService;
    ValidationService validationService;


    public UserRestController(UserService userService,PayementRepository payementRepository,PasswordEncoder passwordEncoder,RolesService rolesService,ValidationService validationService) {
        this.userService = userService;
        this.payementRepository=payementRepository;
        this.passwordEncoder=passwordEncoder;
        this.rolesService=rolesService;
        this.validationService= validationService;
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
        roles = rolesService.save(roles);
        password=passwordEncoder.encode(password);
       Validation validuser= userService.valideUser(userService.save(userService.forImage(file,code,password,email,lastName,firstName,roles)));
       validationService.save(validuser);
       userService.sendEmail(validuser);
    }
    @PostMapping("/saveRoles")
    public void saveRoles(@RequestBody Roles roles){
        roles = rolesService.save(roles);
    }
    @GetMapping("/show/roles")
    public List<Roles> getAllRoles(){
        List<Roles> ourRoles=rolesService.findAll();
        return ourRoles;
    }
@PutMapping("/validUser")
    public void validUser(@RequestBody int code){
        Validation validUser=validationService.findbyCode(code);
        if(validUser!=null){
            User user=validUser.getUser();
            user.setValidation(true);
            userService.save(user);
        }
}

}
