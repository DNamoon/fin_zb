package com.example.fin_zb.service;

import com.example.fin_zb.model.dto.MemberDto;
import com.example.fin_zb.model.dto.SignResponseDto;
import com.example.fin_zb.model.entity.Member;

public interface SignService {

    //회원가입 메서드
    SignResponseDto signUpMember(MemberDto memberDto);

    //로그인 메서드
    void signInMember();

    //비밀번호1, 비밀번호2 일치 확인
    boolean matchPasswords(MemberDto memberDto);

    //약관동의 체크했는지 확인
    boolean checkTerms(MemberDto memberDto);


}
