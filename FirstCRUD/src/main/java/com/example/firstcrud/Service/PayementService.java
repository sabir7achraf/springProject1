package com.example.firstcrud.Service;

import com.example.firstcrud.Entity.Payement;
import com.example.firstcrud.Entity.PayementEtat;
import com.example.firstcrud.Entity.PayementType;
import com.example.firstcrud.Repository.PayementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PayementService {

    @Autowired
    private PayementRepository repo;

    public List<Payement> findAll() {
        return repo.findAll();
    }
    public void Payments(Integer id, Date datePay, PayementEtat payementEtat, PayementType payementType) {
       Payement pay= Payement.builder()
                .payementEtat(payementEtat)
                .payementType(payementType)
                .datepayement(datePay)
                .build();
       repo.save(pay);

    }




}
