package com.example.posts.services.impl;

import com.example.posts.dtos.SignUpDto;
import com.example.posts.dtos.SignedUpDto;
import com.example.posts.entities.Users;
import com.example.posts.repositories.UserRepository;
import com.example.posts.services.SignUpService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    @Override
    public SignedUpDto signUpUser(SignUpDto signUpDto) {
     return modelMapper.map( userRepository.save(modelMapper.map(signUpDto, Users.class)),SignedUpDto.class);
    }
}
