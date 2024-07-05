package com.example.firstcrud.payement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PayementService {

    @Autowired
    private PayementRepository repo;

    public List<Payement> findAll() {
        return repo.findAll();
    }


}
