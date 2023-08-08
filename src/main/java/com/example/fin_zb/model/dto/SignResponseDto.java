package com.example.fin_zb.model.dto;

import lombok.Builder;
import lombok.Getter;
/** 회원가입 성공시 클라이언트에게 돌려준 DTO */
@Getter
@Builder
public class SignResponseDto {

    private String signResponseDtoMemberLoginId;
    private String signResponseDtoName;
    private String signResponseDtoEmail;

}
