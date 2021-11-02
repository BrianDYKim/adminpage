package com.fastcampus.adminpage.controller.api;

import com.fastcampus.adminpage.controller.abstracts.AbstractCrudController;
import com.fastcampus.adminpage.model.network.request.PartnerApiRequest;
import com.fastcampus.adminpage.model.network.response.PartnerApiResponse;
import com.fastcampus.adminpage.service.PartnerApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/partner")
@RequiredArgsConstructor
public class PartnerApiController extends AbstractCrudController<PartnerApiRequest, PartnerApiResponse> {

    private final PartnerApiLogicService partnerApiLogicService;

    @PostConstruct
    public void init() {
        this.baseService = partnerApiLogicService;
    }

}
