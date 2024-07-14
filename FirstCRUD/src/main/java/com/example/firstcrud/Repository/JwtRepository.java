package com.example.firstcrud.Repository;

import com.example.firstcrud.Entity.Jwt;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface JwtRepository extends CrudRepository<Jwt, Long> {


    Optional<Jwt> findByValeur(String token);
}
