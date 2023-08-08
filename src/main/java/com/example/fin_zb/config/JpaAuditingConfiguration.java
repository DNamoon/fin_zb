package com.example.fin_zb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
/** JPA Auditing 적용 - 누가, 언제 데이터를 변경했는지 확인하기 위해*/
@Configuration
@EnableJpaAuditing
public class JpaAuditingConfiguration {

}
