package com.example.firstcrud.Repository;

import com.example.firstcrud.Entity.Validation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ValidationRepository extends JpaRepository<Validation, Integer> {
    public Validation findByCode(int code);
}
