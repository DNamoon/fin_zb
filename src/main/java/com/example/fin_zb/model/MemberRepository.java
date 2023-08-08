package com.example.fin_zb.model;

import com.example.fin_zb.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    boolean existsByMemberLoginId(String memberLoginId);
    boolean existsByEmail(String email);
}
