package com.example.firstcrud;


import com.example.firstcrud.DTO.AuthentificationDTO;
import com.example.firstcrud.DTO.UserDTO;
import com.example.firstcrud.Repository.PayementRepository;
import com.example.firstcrud.Service.PayementService;
import com.example.firstcrud.Service.RolesService;
import com.example.firstcrud.Service.UserService;
import com.example.firstcrud.Entity.*;
import com.example.firstcrud.Service.ValidationService;
import com.example.firstcrud.securite.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.expression.Maps;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController

@CrossOrigin("*")
public class UserRestController {

    UserService userService;
    PayementRepository payementRepository;
    PasswordEncoder passwordEncoder;
    RolesService rolesService;
    @Autowired
    PayementService payementService;
    ValidationService validationService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtService jwtService;


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
    @PostMapping("/adduser")
    public void AddUser(@RequestParam("file") MultipartFile file,  User user) {
        this.userService.save(user);
    }


    @GetMapping("/payments")
    public List<Payement> getAllpayements(){
       return payementRepository.findAll();
    }

    @GetMapping("/payement/{code}")
    public List<Payement> GetPaymentbycode(@PathVariable long code ){
        return payementRepository.findByUser_code(code);
    }

    @PostMapping(path = "/inscription" )
    public void saveuser(UserDTO userDto) throws IOException {
        Roles roles=new Roles();
        roles.setLibelle(RoleType.UTULISATEUR);
        roles = rolesService.save(roles);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
       Validation validuser= userService.valideUser(userService.save(userService.forImage( userDto,roles)));
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
    public void validUser(@RequestBody int code) {
        Validation validUser = validationService.findbyCode(code);
        if (validUser != null) {
            User user = validUser.getUser();
            user.setValidation(true);
            userService.save(user);
        }
    }

    @PostMapping("/connexion")
        public String connexion(@RequestBody AuthentificationDTO authedto){
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authedto.email(), authedto.password())
        );
        if(authenticate.isAuthenticated()){
           return  this.jwtService.generate(authedto.email());
        }
return null;
    }
    @PostMapping("/deconnexion")
    public  void  deconnexion(){
        jwtService.deconnexion();
    }

    @PostMapping("/addPayment")
    public void addPayment(Integer id, Date datePay, PayementEtat payementEtat, PayementType payementType){
    this.payementService.Payments(id,datePay,payementEtat,payementType);
    }

}
