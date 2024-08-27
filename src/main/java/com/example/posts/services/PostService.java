package com.example.posts.services;

import com.example.posts.dtos.PostDto;
import com.example.posts.entities.Post;

import java.util.List;

public interface PostService {
    PostDto findPostById(Long id);

    List<PostDto> findAllPosts();

    PostDto savePost(Post map);
}
