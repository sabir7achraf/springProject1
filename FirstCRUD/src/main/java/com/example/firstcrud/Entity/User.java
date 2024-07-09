package com.example.firstcrud.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter @Setter @ToString @NoArgsConstructor
@AllArgsConstructor
@Builder

public class User   {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false,length = 20)
    private String firstName;

    @Column(unique = true, nullable = false,length = 20)
    private String lastName;

    @Column(unique = true, nullable = false,length = 150)
    private String email;
    @Column(unique = true, nullable = false)
    private String password;

    private String image;
    private  Boolean validation;

    private Long code;

    @OneToOne
    private Roles roles ;

}

