package com.example.fin_zb.service.impl;

import com.example.fin_zb.exception.impl.NotCheckTermsException;
import com.example.fin_zb.exception.impl.DuplicatedEmailException;
import com.example.fin_zb.exception.impl.DuplicatedLoginIdException;
import com.example.fin_zb.exception.impl.NotMatchPasswordException;
import com.example.fin_zb.model.MemberRepository;
import com.example.fin_zb.model.dto.MemberDto;
import com.example.fin_zb.model.dto.SignResponseDto;
import com.example.fin_zb.model.entity.Member;
import com.example.fin_zb.service.SignService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SignServiceImpl implements SignService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public SignResponseDto signUpMember(MemberDto memberDto) {
       boolean existsLoginId =
           memberRepository.existsByMemberLoginId(memberDto.getMemberLoginId());
        if (existsLoginId) {
//            throw new RuntimeException("이미 사용중인 아이디입니다.");
            throw new DuplicatedLoginIdException();
        }

        boolean existsEmail = memberRepository.existsByEmail(memberDto.getEmail());
        if (existsEmail) {
            throw new DuplicatedEmailException();
        }

        if (!matchPasswords(memberDto)) {
            throw new NotMatchPasswordException();
        }

        //비밀번호 암호화
        String encodePassword = passwordEncoder.encode(memberDto.getPassword());

        if (!checkTerms(memberDto)) {
            throw new NotCheckTermsException();
        }


        Member savedMember = memberRepository.save(memberDto.toMemberEntity(encodePassword));
        SignResponseDto signResponseDto = savedMember.toSignResponseDto();

        return signResponseDto;
    }

    @Override
    public void signInMember() {

    }

    @Override  //비밀번호1, 2일치 확인 로직
    public boolean matchPasswords(MemberDto memberDto) {
//        if (!memberDto.getPassword().equals(memberDto.getPassword2())) {
//            log.info("### 비밀번호 1,2 불일치!!! -> exception 발생");
//            throw new RuntimeException("2개의 비밀번호가 일치하지 않습니다.");
//        }

        return memberDto.getPassword().equals(memberDto.getPassword2());
    }

    @Override //약관동의 확인 메서드  - 이거 굳이 메서드로 빼야 하나?
    public boolean checkTerms(MemberDto memberDto) {
        return memberDto.isCheckTerms();
    }
}
