package com.example.firstcrud.user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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

