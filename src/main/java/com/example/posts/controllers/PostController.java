package com.example.posts.controllers;

import com.example.posts.dtos.PostDto;
import com.example.posts.dtos.SessionDto;
import com.example.posts.entities.Post;
import com.example.posts.services.PostService;
import com.example.posts.services.impl.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    private final ModelMapper modelMapper;

    private final JwtService jwtService;

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long id){
        return ResponseEntity.ok(postService.findPostById(id));
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(){
        return ResponseEntity.ok(postService.findAllPosts());
    }

    @PostMapping
    @Secured({"ROLE_USER"})
    public ResponseEntity<PostDto> savePost(@RequestBody PostDto postDto){
        PostDto savedPostDto=postService.savePost(modelMapper.map(postDto, Post.class));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedPostDto.getId()).toUri();

        return ResponseEntity.created(location).body(savedPostDto);
    }


}
