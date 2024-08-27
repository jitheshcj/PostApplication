package com.example.posts.services.impl;

import com.example.posts.dtos.PostDto;
import com.example.posts.entities.Post;
import com.example.posts.entities.Users;
import com.example.posts.repositories.PostRepository;
import com.example.posts.services.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    @Override
    public PostDto findPostById(Long id) {
        return modelMapper.map(postRepository.findById(id),PostDto.class);
    }

    @Override
    public List<PostDto> findAllPosts() {
        return postRepository.findAll().stream().map(x->modelMapper.map(x,PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDto savePost(Post post) {
        post.setUsers((Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return modelMapper.map(postRepository.save(post),PostDto.class);
    }
}
