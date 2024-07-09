package com.example.firstcrud.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesService {
    @Autowired
    RolesRepository repo;
    public Roles save(Roles roles){
      return  repo.save(roles);
    }
public List<Roles> findAll(){
        return  repo.findAll();
}

}
