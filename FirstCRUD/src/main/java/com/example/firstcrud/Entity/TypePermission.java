package com.example.firstcrud.Entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TypePermission {

    ADMINE_CREATE,
    ADMINE_READ,
    ADMINE_UPDATE,
    ADMINE_DELETE,

    MANAGER_CREATE,
    MANAGER_READ,
    MANAGER_UPDATE,
    MANAGER_DELETE_USER,

    UTILISATEUR_CREATE_PAYEMENT;

    @Getter
    private String permission ;


}