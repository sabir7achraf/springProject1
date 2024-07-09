package com.example.firstcrud.Service;

import com.example.firstcrud.Entity.Payement;
import com.example.firstcrud.Repository.PayementRepository;
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
