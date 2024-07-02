package com.example.firstcrud.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


}
