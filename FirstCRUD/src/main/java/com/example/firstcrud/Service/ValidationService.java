package com.example.firstcrud.Service;

import com.example.firstcrud.Entity.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ValidationService {
    @Autowired
    ValidationService validationService;

        public Validation  findbyCode(int code){
        Validation validUser = validationService.findbyCode(code);
        if(validUser.getExpiration().isBefore(Instant.now())){
            validUser.getUser().setValidation(true);
    }
        return validUser;
    }

}
