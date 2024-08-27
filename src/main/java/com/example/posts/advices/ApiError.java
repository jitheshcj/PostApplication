package com.example.posts.advices;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Builder
@Getter
@Setter
public class ApiError {
    private String error;
    private HttpStatus httpStatus;
    private List<String> subError;
}
