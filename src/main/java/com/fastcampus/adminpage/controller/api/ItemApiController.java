package com.fastcampus.adminpage.controller.api;

import com.fastcampus.adminpage.controller.abstracts.AbstractCrudController;
import com.fastcampus.adminpage.model.network.request.ItemApiRequest;
import com.fastcampus.adminpage.model.network.response.ItemApiResponse;
import com.fastcampus.adminpage.service.ItemApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/item")
@RequiredArgsConstructor
public class ItemApiController extends AbstractCrudController<ItemApiRequest, ItemApiResponse> {

    private final ItemApiLogicService itemApiLogicService;

    @PostConstruct
    public void init() {
        this.baseService = itemApiLogicService;
    }
}
