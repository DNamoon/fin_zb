package com.example.fin_zb.model.dto;

import com.example.fin_zb.model.entity.Member;
import java.time.LocalDateTime;
import javax.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/** 클라이언트에서 이 DTO를 통해 회원가입 요청 */
@Getter
@Setter
public class MemberDto {

    private String memberLoginId;

    private String password;

    private String password2;

    private String email;

    private String name;

    private String phone;

    //boolean은 getter로 가져오는게 아닌 isCheckTerms() 메서드가 있네.
    private boolean checkTerms;

    //소프트 딜리트 위해서 Boolean.FALSE 기본값으로
    private boolean idDeleted = Boolean.FALSE;
//    private boolean idDeleted;

    private LocalDateTime memberDeletedAt;

    public Member toMemberEntity(String password) {
        return Member.builder()
            .memberLoginId(memberLoginId)
            .password(password)
            .email(email)
            .name(name)
            .phone(phone)
            .checkTerms(checkTerms)
            .idDeleted(idDeleted)
            .memberDeletedAt(memberDeletedAt)
            .build();
    }



}
