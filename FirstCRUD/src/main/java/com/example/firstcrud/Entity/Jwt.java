package com.example.firstcrud.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.Date;

@Table
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Jwt {
    @Id
    @GeneratedValue
    private int id;
    private String  valeur;
    private Boolean Expiration;
    private Boolean validation;
    @ManyToOne
    private User user;





}
