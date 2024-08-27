package com.example.posts.controllers;

import com.example.posts.dtos.LoginDto;
import com.example.posts.dtos.SessionDto;
import com.example.posts.dtos.SignUpDto;
import com.example.posts.dtos.SignedUpDto;
import com.example.posts.entities.Users;
import com.example.posts.services.impl.JwtService;
import com.example.posts.services.SignUpService;
import com.example.posts.services.impl.UserDetailsService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final PasswordEncoder passwordEncoder;

    private final SignUpService signUpService;

    private final UserDetailsService userDetailsService;

    private final JwtService jwtService;

    @PostMapping("/signup")
    public ResponseEntity<SignedUpDto> signUpUser(@RequestBody SignUpDto signUpDto){
        signUpDto.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        return ResponseEntity.ok(signUpService.signUpUser(signUpDto));
    }

    @PostMapping("/login")
    public ResponseEntity<SessionDto> login(@RequestBody LoginDto loginDto, HttpServletResponse response) throws Exception {
        loginDto.setPassword(passwordEncoder.encode(loginDto.getPassword()));
        Users users=userDetailsService.findUserByEmail(loginDto.getEmail());

        Authentication authentication=new UsernamePasswordAuthenticationToken(users.getEmail(),users.getPassword(),users.getAuthorities());
        if(!authentication.isAuthenticated()){
            throw new Exception("User not Authenticated!!!");
        }
        String refreshToken=jwtService.generateRefreshToken(users);
        Cookie cookie=new Cookie("refreshToken",refreshToken);
        cookie.setSecure(false);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
       return new ResponseEntity<>(SessionDto.builder()
               .id(users.getId())
               .accessToken(jwtService.generateAccessToken(users))
               .build(), HttpStatus.ACCEPTED);
    }

    @PostMapping("/refresh")
    public ResponseEntity<SessionDto> refreshSession(@RequestBody SessionDto sessionDto, HttpServletRequest request){
        if(!jwtService.isValid(sessionDto.getAccessToken())){
            Arrays.stream(request.getCookies()).peek(System.out::println);
        }
        return ResponseEntity.ok(sessionDto);
    }
}
