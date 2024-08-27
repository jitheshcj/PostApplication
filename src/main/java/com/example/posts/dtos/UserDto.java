package com.example.posts.dtos;

import com.example.posts.enums.Roles;
import lombok.Data;

import java.util.Set;

@Data
public class UserDto {
    private String name;
    private Long id;

    private String email;

    private Set<Roles> roles;
}
