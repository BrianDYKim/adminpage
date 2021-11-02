package com.fastcampus.adminpage.controller.api;

import com.fastcampus.adminpage.controller.abstracts.AbstractCrudController;
import com.fastcampus.adminpage.model.network.request.OrderDetailApiRequest;
import com.fastcampus.adminpage.model.network.response.OrderDetailApiResponse;
import com.fastcampus.adminpage.service.OrderDetailApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/order-detail")
@RequiredArgsConstructor
public class OrderDetailApiController extends AbstractCrudController<OrderDetailApiRequest, OrderDetailApiResponse> {

    private final OrderDetailApiLogicService orderDetailApiLogicService;

    @PostConstruct
    public void init() {
        this.baseService = orderDetailApiLogicService;
    }
}
