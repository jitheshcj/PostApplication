package com.example.posts.dtos;

import com.example.posts.enums.Roles;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Data
@Getter
@Setter
public class SignedUpDto {
    private Long id;
    private String name;
    private String email;
    private Set<Roles> roles;
    private String password;
}
