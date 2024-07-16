package com.example.firstcrud.Repository;

import com.example.firstcrud.Entity.Jwt;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.stream.Stream;

public interface JwtRepository extends CrudRepository<Jwt, Long> {


    Optional<Jwt> findByValeur(String token);

    @Query("FROM Jwt j where j.expiration=:expiration and j.validation=:validation and j.user.email =:email ")
    Optional<Jwt> findUserValidationToken(String email, boolean validation, boolean expiration);

    @Query("FROM Jwt j WHERE j.user.email = :email")
    Stream<Jwt> findUser(String email);


    void deleteAllByExpirationAndValidation(boolean expiration, boolean validation);
}
