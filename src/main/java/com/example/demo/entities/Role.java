package com.example.demo.entities;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    ADMIN(Set.of(Permission.USERS_WRITE, Permission.USERS_READ, Permission.USERS_DELETE, Permission.USERS_CREATE, Permission.ADMIN)),
    USER(Set.of(Permission.USERS_READ, Permission.USERS_CREATE)),
    OPERATOR(Set.of(Permission.USERS_WRITE, Permission.USERS_READ, Permission.USERS_DELETE, Permission.USERS_CREATE));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
