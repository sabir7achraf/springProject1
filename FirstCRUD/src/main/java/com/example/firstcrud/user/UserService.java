package com.example.firstcrud.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;



@Service
public class UserService {

    @Autowired private UserRepository repo;

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
    public User forImage(MultipartFile file, Long code,String password,String email,String lastName,String firstName) throws IOException {
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
                .build();
    }




}
