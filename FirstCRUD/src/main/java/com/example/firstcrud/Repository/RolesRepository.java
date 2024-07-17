package com.example.firstcrud.Repository;

import com.example.firstcrud.Entity.RoleType;
import com.example.firstcrud.Entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles, Integer> {
   Optional<Roles> findByLibelle(RoleType role);
}
