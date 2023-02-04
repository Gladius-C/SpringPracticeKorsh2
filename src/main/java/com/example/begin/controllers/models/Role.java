package com.example.begin.controllers.models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,ADMIN,HISTORIAN;
    @Override
    public String getAuthority(){
        return name();
    }

}
