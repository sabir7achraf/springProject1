package com.example.firstcrud.Entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table
@Builder
@Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private RoleType libelle;
}
