package com.example.firstcrud.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum RoleType {
    UTULISATEUR(
            Set.of(TypePermission.UTILISATEUR_CREATE_PAYEMENT)
    ),

    ADHERENTS(
            Set.of()
    ),
    VOLONTAIRE(
            Set.of()
    ),
    ADMINE(
            Set.of(TypePermission.ADMINE_CREATE, TypePermission.ADMINE_READ, TypePermission.ADMINE_UPDATE, TypePermission.ADMINE_DELETE)
    );

private final Set<TypePermission> permissions;
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final List<SimpleGrantedAuthority> grantedAuthorities = this.getPermissions().stream().map(
                permission -> new SimpleGrantedAuthority(permission.name())
        ).collect(Collectors.toList());

        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return grantedAuthorities;
    }


}
