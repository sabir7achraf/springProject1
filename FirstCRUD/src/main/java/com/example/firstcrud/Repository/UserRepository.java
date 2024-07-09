package com.example.firstcrud.Repository;

import com.example.firstcrud.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

}
