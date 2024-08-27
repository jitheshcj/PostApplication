package com.example.posts.services;

import com.example.posts.dtos.SignUpDto;
import com.example.posts.dtos.SignedUpDto;

public interface SignUpService {
    SignedUpDto signUpUser(SignUpDto signUpDto);
}
