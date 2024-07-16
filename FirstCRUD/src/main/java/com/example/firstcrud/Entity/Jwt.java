package com.example.firstcrud.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Table
@Entity
@Setter
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
