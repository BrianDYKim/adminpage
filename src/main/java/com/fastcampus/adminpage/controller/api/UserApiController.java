package com.fastcampus.adminpage.controller.api;

import com.fastcampus.adminpage.controller.abstracts.AbstractCrudController;
import com.fastcampus.adminpage.model.network.request.UserApiRequest;
import com.fastcampus.adminpage.model.network.response.UserApiResponse;
import com.fastcampus.adminpage.service.UserApiLogicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserApiController extends AbstractCrudController<UserApiRequest, UserApiResponse> {

    // Controller와 Service 연결
    private final UserApiLogicService userApiLogicService;

    // Service의 의존성을 주입을 받은 다음에 baseService에 service를 연결해야만 정상 동작한다!
    @PostConstruct
    public void init() {
        this.baseService = userApiLogicService;
    }

}
