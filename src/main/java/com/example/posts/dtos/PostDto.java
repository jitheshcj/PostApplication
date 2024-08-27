package com.example.posts.dtos;

import com.example.posts.entities.Users;
import lombok.Data;

@Data
public class PostDto {
    private Long id;

    private String title;
    private String description;

    private UserDto users;
}
