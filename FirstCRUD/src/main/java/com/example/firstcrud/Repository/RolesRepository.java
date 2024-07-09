package com.example.firstcrud.Repository;

import com.example.firstcrud.Entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles, Integer> {
}
