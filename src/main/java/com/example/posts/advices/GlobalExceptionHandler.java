package com.example.posts.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.MethodNotAllowedException;


import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodNotAllowedException.class)
    public ApiResponse<ApiError> methodNoAllowedException(MethodNotAllowedException methodNotAllowedException){
        ApiError apiError= ApiError.builder()
                .error(methodNotAllowedException.getLocalizedMessage())
                .subError(List.of(methodNotAllowedException.getLocalizedMessage()))
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build();
        return new ApiResponse<>(apiError);
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<ApiError> exception(Exception exception){
        ApiError apiError= ApiError.builder()
                .error(exception.getLocalizedMessage())
                .subError(List.of(exception.getLocalizedMessage()))
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build();
        return new ApiResponse<>(apiError);
    }
}
