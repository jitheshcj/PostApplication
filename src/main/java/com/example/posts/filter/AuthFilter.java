package com.example.posts.filter;

import com.example.posts.entities.Users;
import com.example.posts.exceptions.ResourceNotFoundException;
import com.example.posts.services.impl.JwtService;
import com.example.posts.services.impl.UserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;;
import lombok.SneakyThrows;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationToken=request.getHeader("Authorization");
        if(authorizationToken!=null && authorizationToken.contains("Bearer")){
            String token=authorizationToken.split(" " )[1];
            if(token!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                String id=jwtService.getIdFromToken(token);
                Users users=userDetailsService.findUserById(Long.parseLong(id)).orElseThrow(()-> new ResourceNotFoundException("User id not Found"));
                Authentication authentication= new UsernamePasswordAuthenticationToken(users,null,users.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(
                     authentication
                );

            }
        }


        filterChain.doFilter(request,response);
    }
}
