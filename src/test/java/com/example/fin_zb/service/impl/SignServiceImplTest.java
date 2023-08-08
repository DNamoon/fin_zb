package com.example.fin_zb.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.example.fin_zb.model.dto.MemberDto;
import com.example.fin_zb.model.dto.SignResponseDto;
import com.example.fin_zb.model.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class SignServiceImplTest {

    @Autowired
    private SignServiceImpl signServiceImpl;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("비밀번호 암호화 테스트")
    void passwordEncoderTest() {
        //given
        MemberDto dto = new MemberDto();
        dto.setMemberLoginId("member6");
        dto.setName("kim");
        dto.setEmail("kim00@naver.com");
        dto.setPhone("010-0000-1111");
        dto.setPassword("1234");

        //when
        String encode = passwordEncoder.encode(dto.getPassword());

        //then

        if (passwordEncoder.matches(dto.getPassword(), encode)) {
            System.out.println("비밀번호가 일치합니다.");
        }

        assertNotEquals("1234",encode);

    }

    @Test
    @DisplayName("비밀번호 1,2 불일치 확인")
    void matchPasswordTest() {
        //given
        MemberDto dto = new MemberDto();
        dto.setMemberLoginId("member134");
        dto.setName("홍길동");
        dto.setPhone("010-1234-1234");
        dto.setPassword("1234");
        dto.setPassword2("123");

        //when
        boolean result = signServiceImpl.matchPasswords(dto);

        //then
        assertFalse(result);

    }

    @Test
    void signUpIfNotMatchPassword() {
        //given
        MemberDto dto = new MemberDto();
        dto.setMemberLoginId("member56");
        dto.setName("홍길동");
        dto.setPhone("010-1234-1234");
        dto.setPassword("1234");
        dto.setPassword2("123");

        //when
        signServiceImpl.signUpMember(dto);
        //then
        System.out.println("에러발생으로 여기까지 넘어오지 않을 것");
    }

    @Test
    @DisplayName("비밀번호 일치 확인")
    void matchPasswordTestSuccess() {
        //given
        MemberDto dto = new MemberDto();
        dto.setMemberLoginId("member2");
        dto.setName("홍길동");
        dto.setPhone("010-1234-1234");
        dto.setPassword("1234");
        dto.setPassword2("1234");

        //when
        boolean result = signServiceImpl.matchPasswords(dto);

        //then
        assertTrue(result);

    }

    @Test
    @DisplayName("비밀번호 1,2 일치 시 회원가입 성공")
    void signUpTestMatchPasswordSuccess() {
        //given
        MemberDto dto = new MemberDto();
        dto.setMemberLoginId("member33");
        dto.setName("홍길동");
        dto.setEmail("kim123@daum.net");
        dto.setPhone("010-2345-1235");
        dto.setPassword("1234");
        dto.setPassword2("1234");
        dto.setCheckTerms(true);

        //when
        SignResponseDto signResponseDto = signServiceImpl.signUpMember(dto);

        //then
        assertEquals("홍길동", signResponseDto.getSignResponseDtoName());
        assertEquals("member33", signResponseDto.getSignResponseDtoMemberLoginId());
    }


    @Test
    @DisplayName("비밀번호 1,2 불일치 시 회원가입 실패")
    void signUpTestMatchPasswordFail() {
        //given
        MemberDto dto = new MemberDto();
        dto.setMemberLoginId("member335");
        dto.setName("홍길동");
        dto.setEmail("kim12356@daum.net");
        dto.setPhone("010-2345-1235");
        dto.setPassword("1234");
        dto.setPassword2("123");
        dto.setCheckTerms(true);

        //when
        SignResponseDto signResponseDto = signServiceImpl.signUpMember(dto);

        //then
        System.out.println("에러 발생으로 여기까지 안 올 것");
    }

    @Test
    @DisplayName("아이디 중복 아닐시 회원가입 성공")
    void NotDuplicatedLoginId() {
        //given
        MemberDto dto = new MemberDto();
        dto.setMemberLoginId("member12345");
        dto.setName("홍길동");
        dto.setEmail("hong11@daum.net");
        dto.setPhone("010-2345-1235");
        dto.setPassword("1234");
        dto.setPassword2("1234");
        dto.setCheckTerms(true);

        MemberDto dto2 = new MemberDto();
        dto2.setMemberLoginId("member54321");
        dto2.setName("홍");
        dto2.setEmail("hh23@daum.net");
        dto2.setPhone("010-2345-1235");
        dto2.setPassword("1234");
        dto2.setPassword2("1234");
        dto2.setCheckTerms(true);

        //when
        SignResponseDto savedMember1 = signServiceImpl.signUpMember(dto);
        SignResponseDto savedMember2 = signServiceImpl.signUpMember(dto2);

        //then
        //회원가입 진행 완료
        assertEquals("홍길동", savedMember1.getSignResponseDtoName());
        assertEquals("홍", savedMember2.getSignResponseDtoName());
    }

    @Test
    @DisplayName("아이디 중복으로 회원가입 실패")
    void duplicatedLoginIdFailed() {
        //given
        MemberDto dto = new MemberDto();
        dto.setMemberLoginId("member123456");
        dto.setName("홍길동");
        dto.setEmail("hong114@daum.net");
        dto.setPhone("010-2345-1235");
        dto.setPassword("1234");
        dto.setPassword2("1234");
        dto.setCheckTerms(true);

        SignResponseDto savedMember1 = signServiceImpl.signUpMember(dto);
        System.out.println("정상 회원가입");

        //when
        MemberDto dto2 = new MemberDto();
        dto2.setMemberLoginId("member123456");
        dto2.setName("홍");
        dto2.setEmail("hh234@daum.net");
        dto2.setPhone("010-2345-1235");
        dto2.setPassword("1234");
        dto2.setPassword2("1234");
        dto2.setCheckTerms(true);

        SignResponseDto savedMember2 = signServiceImpl.signUpMember(dto2);

        //then
        System.out.println("로그인 아이디 중복으로 여기까지 진행 불가");

    }

    @Test
    @DisplayName("이메일 중복 아닐 시 회원가입 성공")
    void NotDuplicatedEmail() {
        //given
        MemberDto dto = new MemberDto();
        dto.setMemberLoginId("member123456");
        dto.setName("홍길동");
        dto.setEmail("hong1134@daum.net");
        dto.setPhone("010-2345-1235");
        dto.setPassword("1234");
        dto.setPassword2("1234");
        dto.setCheckTerms(true);

        MemberDto dto2 = new MemberDto();
        dto2.setMemberLoginId("member654321");
        dto2.setName("홍");
        dto2.setEmail("hh235@daum.net");
        dto2.setPhone("010-2345-1235");
        dto2.setPassword("1234");
        dto2.setPassword2("1234");
        dto2.setCheckTerms(true);

        //when
        SignResponseDto savedMember1 = signServiceImpl.signUpMember(dto);
        SignResponseDto savedMember2 = signServiceImpl.signUpMember(dto2);

        //then
        //회원가입 진행 완료
        assertEquals("hong1134@daum.net", savedMember1.getSignResponseDtoEmail());
        assertEquals("hh235@daum.net", savedMember2.getSignResponseDtoEmail());
    }

    @Test
    @DisplayName("이메일 중복으로 회원가입 실패")
    void duplicatedEmailFailed() {
        //given
        MemberDto dto = new MemberDto();
        dto.setMemberLoginId("member12");
        dto.setName("홍길동");
        dto.setEmail("park@daum.net");
        dto.setPhone("010-2345-1235");
        dto.setPassword("1234");
        dto.setPassword2("1234");
        dto.setCheckTerms(true);

        SignResponseDto savedMember1 = signServiceImpl.signUpMember(dto);
        System.out.println("member1은 회원가입 성공");

        //when
        MemberDto dto2 = new MemberDto();
        dto2.setMemberLoginId("member21");
        dto2.setName("홍");
        dto2.setEmail("park@daum.net");
        dto2.setPhone("010-2345-1235");
        dto2.setPassword("1234");
        dto2.setPassword2("1234");
        dto2.setCheckTerms(true);

        SignResponseDto savedMember2 = signServiceImpl.signUpMember(dto2);

        //then
        //회원가입 진행 완료
        System.out.println("이 부분은 실행 x");

    }






}