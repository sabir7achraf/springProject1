package com.example.firstcrud.Repository;

import com.example.firstcrud.Entity.Jwt;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface JwtRepository extends CrudRepository<Jwt, Long> {


    Optional<Jwt> findByValeur(String token);
    @Query("FROM Jwt j where j.Expiration=:Expiration and j.validation and j.user.email =:email ")
    Optional<Jwt> findUserValidationToken(String email, boolean validation, boolean Expiration);
}
