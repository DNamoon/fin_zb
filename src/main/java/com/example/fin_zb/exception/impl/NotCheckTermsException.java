package com.example.fin_zb.exception.impl;

import com.example.fin_zb.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class NotCheckTermsException extends AbstractException {

    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }

    @Override
    public String getMessage() {
        return "약관동의를 해야 회원가입을 진행할 수 있습니다.";
    }
}
