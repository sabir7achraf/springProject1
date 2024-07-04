package com.example.firstcrud.payement;

import com.example.firstcrud.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table
@Getter @Setter @AllArgsConstructor @ToString @Builder @NoArgsConstructor
public class Payement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer payementId;

    @Enumerated(EnumType.STRING)
    private PayementType payementType;

    @Enumerated(EnumType.STRING)
    private PayementEtat payementEtat;

    private Date datepayement;

     private String file;

    @ManyToOne
    private User user;


}
