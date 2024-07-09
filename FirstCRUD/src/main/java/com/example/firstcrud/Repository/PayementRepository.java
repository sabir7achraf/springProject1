package com.example.firstcrud.Repository;

import com.example.firstcrud.Entity.Payement;
import com.example.firstcrud.Entity.PayementEtat;
import com.example.firstcrud.Entity.PayementType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface PayementRepository extends JpaRepository<Payement, Long> {
    List<Payement> findByUser_code(long code);
    List<Payement> findBypayementType(PayementType payementtype);
    List<Payement> findBypayementEtat(PayementEtat payementEtat);
    List<Payement> findBydatepayement(Date date);
}

