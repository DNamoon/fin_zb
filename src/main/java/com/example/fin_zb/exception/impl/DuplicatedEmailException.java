package com.example.fin_zb.exception.impl;

import com.example.fin_zb.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class DuplicatedEmailException extends AbstractException {

    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }

    @Override
    public String getMessage() {
        return "이미 가입한 이메일입니다. 다른 이메일로 회원가입을 진행해 주세요.";
    }
}
