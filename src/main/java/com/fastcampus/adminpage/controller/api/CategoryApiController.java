package com.fastcampus.adminpage.controller.api;

import com.fastcampus.adminpage.controller.abstracts.AbstractCrudController;
import com.fastcampus.adminpage.model.network.request.CategoryApiRequest;
import com.fastcampus.adminpage.model.network.response.CategoryApiResponse;
import com.fastcampus.adminpage.service.CategoryApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryApiController extends AbstractCrudController<CategoryApiRequest, CategoryApiResponse> {

    private final CategoryApiLogicService categoryApiLogicService;

    @PostConstruct
    public void init() {
        this.baseService = categoryApiLogicService;
    }
}
