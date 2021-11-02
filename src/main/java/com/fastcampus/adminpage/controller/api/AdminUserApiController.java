package com.fastcampus.adminpage.controller.api;

import com.fastcampus.adminpage.controller.abstracts.AbstractCrudController;
import com.fastcampus.adminpage.model.network.request.AdminUserApiRequest;
import com.fastcampus.adminpage.model.network.response.AdminUserApiResponse;
import com.fastcampus.adminpage.service.AdminUserApiLogicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/admin-user")
@RequiredArgsConstructor
@Slf4j
public class AdminUserApiController extends AbstractCrudController<AdminUserApiRequest, AdminUserApiResponse> {

    private final AdminUserApiLogicService adminUserApiLogicService;

    @PostConstruct
    public void init() {
        this.baseService = adminUserApiLogicService;
    }
}
