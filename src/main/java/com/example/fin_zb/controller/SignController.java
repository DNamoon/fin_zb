package com.example.fin_zb.controller;

import com.example.fin_zb.model.dto.MemberDto;
import com.example.fin_zb.model.dto.SignResponseDto;
import com.example.fin_zb.model.entity.Member;
import com.example.fin_zb.service.impl.SignServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/sign")
@RestController
public class SignController {

    private final SignServiceImpl signServiceImpl;

    @PostMapping
    public ResponseEntity<SignResponseDto> signUp(MemberDto memberDto) {
        //서버에서 회원가입 진행
        SignResponseDto signResponseDto = signServiceImpl.signUpMember(memberDto);
        return ResponseEntity.ok(signResponseDto);
    }

}


