package com.example.fin_zb.exception.impl;

import com.example.fin_zb.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class NotMatchPasswordException extends AbstractException {

    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }

    @Override
    public String getMessage() {
        return "비밀번호1, 2가 일치하지 않습니다. 일치여부를 확인해주세요";
    }
}
