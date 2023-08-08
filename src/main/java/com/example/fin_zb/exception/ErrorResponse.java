package com.example.fin_zb.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {
    private int errorCode;
    private String message;
}
