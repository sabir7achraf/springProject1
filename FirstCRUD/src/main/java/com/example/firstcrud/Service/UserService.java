package com.example.firstcrud.Service;

import com.example.firstcrud.Entity.Roles;
import com.example.firstcrud.Entity.User;
import com.example.firstcrud.Repository.UserRepository;
import com.example.firstcrud.Entity.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.UUID;



@Service
public class UserService {

    @Autowired private UserRepository repo;
    @Autowired JavaMailSender mailSender;

    public List<User> findAll() {
        return (List<User>) repo.findAll();
    }
    public User save(User user) {
        return repo.save(user);
    }
    
    public User findById(int id) {
        return repo.findById(id).get();
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
    public User forImage(MultipartFile file, Long code,String password,String email,String lastName,String firstName, Roles roles) throws IOException {
        Path folderPath = Paths.get(System.getProperty("user.home"),"fst-spring","userimage");
        if(!Files.exists(folderPath)){
            Files.createDirectories(folderPath);
        }
        String fileName= UUID.randomUUID().toString();
        Path filePath=Paths.get(System.getProperty("user.home"),"fst-spring","userimage",fileName+".jpg");
        Files.copy(file.getInputStream(),filePath);

        return User.builder().image(fileName)
                .code(code).email(email)
                .firstName(firstName).lastName(lastName)
                .password(password)
                .roles(roles)
                .build();
    }
    public Validation valideUser(User user) {
        Validation validation = new Validation();
       Instant creation = Instant.now();
       Instant expiration = creation.plus(10, ChronoUnit.MINUTES);
       validation.setExpiration(expiration);
       validation.setUser(user);
       validation.setCreation(creation);
        Random random=new Random();
        int randomInteger=random.nextInt(999999);
        validation.setCode(randomInteger);
        return validation;
    }

    public  void sendEmail(Validation validation){
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom("sabirachraf032@gmail.com");
    message.setTo(validation.getUser().getEmail());
     String text=String.format("Bonjour %s ,<br/> votre code d'activation est %d <br/> MERCI POUR VOTRE INSCRIPTION ",validation.getUser().getFirstName(),validation.getCode());
        message.setText(text);
        mailSender.send(message);
    }




}
