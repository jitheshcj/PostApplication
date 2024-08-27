package com.example.posts.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessionDto {
    private Long id;
    private String accessToken;
//    private String refreshToken;
}
