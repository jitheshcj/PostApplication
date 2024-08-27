package com.example.posts.dtos;

import com.example.posts.enums.Roles;
import lombok.Data;

import java.util.Set;

@Data
public class SignUpDto {

    private String name;
    private String password;
    private String email;
    private Set<Roles> roles;
}
