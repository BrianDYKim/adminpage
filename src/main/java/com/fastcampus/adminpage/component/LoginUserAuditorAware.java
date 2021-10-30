package com.fastcampus.adminpage.component;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

// 로그인한 유저를 감시하는 역할을 수행
@Component // Component에서 Auditor 정보를 입력해줌
public class LoginUserAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("AdminServer");
    }
}
