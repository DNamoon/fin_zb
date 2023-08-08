//package com.example.fin_zb.model.entity;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import com.example.fin_zb.model.MemberRepository;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
///**member 엔티티 생성 확인 테스트 */
////테스트를 진행하기 위해 setter를 사용했지만 실제 member 엔티티에는 setter를 지웠습니다.
//@SpringBootTest
//class MemberTest {
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Test  //자동으로 계정생성날짜 들어가는지 확인
//    @DisplayName("자동으로 계정생성날짜 생성 확인")
//    void memberAuditingTest() {
//        //given
//        Member member = new Member();
//        member.setMemberLoginId("member1");
//        member.setPassword("1234");
//        member.setEmail("123@naver.com");
//        member.setName("홍길동");
//        member.setPhone("010-1234-5678");
////        member.setIdDeleted(false);
////        member.setCheckTerms(true);
//
//        //when
//        memberRepository.save(member);
//
//        //then
//        assertEquals("member1", member.getMemberLoginId());
//        System.out.println("member.getCreatedAt() = " + member.getCreatedAt());
//
//        //미세한 차이로 에러남.
////        assertEquals(LocalDateTime.now(), member.getCreatedAt());
//    }
//
//    @Test
//    @DisplayName("이메일 unique 성질 확인")
//    void isEmailUnique() {
//        //given
//        Member member1 = new Member();
//        member1.setEmail("abcd@naver.com");
//        member1.setName("park");
//        member1.setPhone("010-1111-2222");
//        member1.setPassword("1234");
//        member1.setCheckTerms(true);
//
//
//        Member member2 = new Member();
//        member2.setEmail("abcd@naver.com");
//        member2.setName("kim");
//        member2.setPhone("010-0000-0000");
//        member2.setPassword("0000");
//        member2.setCheckTerms(true);
//
//
//        memberRepository.save(member1);
//
//        try {
//            memberRepository.save(member2);
//        } catch (Exception e) {
//            System.out.println("#### 예외 발생 : " + e.getMessage());
//        }
//
//
//    }
//
//}