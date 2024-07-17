package com.example.firstcrud.Entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

public enum RoleType {
    UTULISATEUR,ADHERENTS,VOLONTAIRE,ADMINE,;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }


}
