package com.example.fin_zb.model.entity;

import com.example.fin_zb.model.dto.MemberDto;
import com.example.fin_zb.model.dto.SignResponseDto;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Entity
@Getter
@Builder
@Slf4j
@RequiredArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer memberId;

    @Column(nullable = false, unique = true)
    private String memberLoginId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)  //약관 동의해야지만 가입가능
    private boolean checkTerms;

    //소프트 딜리트 사용 위해
//    private boolean idDeleted = Boolean.FALSE;
    @Column(nullable = false)
    private boolean idDeleted;

    // null 가능
    private LocalDateTime memberDeletedAt;

    //이거 언제 써야 하나 고민 -> 회원가입 완료 후 Dto 돌려주기 위한 메서드
    public SignResponseDto toSignResponseDto() {

        log.info("### 회원가입 성공 -> Dto 돌려주는 메서드 동작");
        return SignResponseDto.builder()
            .signResponseDtoMemberLoginId(memberLoginId)
            .signResponseDtoName(name)
            .signResponseDtoEmail(email)
            .build();
    }
}
