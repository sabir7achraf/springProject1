package com.example.firstcrud.Service;

import com.example.firstcrud.Entity.Validation;
import com.example.firstcrud.Repository.ValidationRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ValidationService {
    @Autowired
    ValidationRepository validationRepo;

        public Validation  findbyCode(int code){
        Validation validUser = validationRepo.findByCode(code);
        if(validUser.getExpiration().isAfter(Instant.now())){
            return validUser;
    }
        else throw new ValidationException("code Expiré");
    }
    public void save(Validation validation){
            validationRepo.save(validation);
    }
}
