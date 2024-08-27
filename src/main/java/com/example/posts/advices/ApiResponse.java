package com.example.posts.advices;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApiResponse <T>{
    private ApiError error;
    private T data;
    private LocalDateTime timestamp;

    {
        timestamp=LocalDateTime.now();
    }

    public ApiResponse(T data){
        this.data=data;
    }
    public ApiResponse(ApiError error){
        this.error=error;
    }
}
