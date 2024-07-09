package com.example.lms.Shared;

import lombok.*;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class GlobalApiResponse<T> {
    private String message;
    private T data;
    private Integer statusCode;
}

