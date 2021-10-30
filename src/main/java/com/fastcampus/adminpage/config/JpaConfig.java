package com.fastcampus.adminpage.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing // Configuration 단계에서 JPA Auditing을 활성화
public class JpaConfig {

}
