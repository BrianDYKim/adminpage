package com.fastcampus.adminpage.controller.api;

import com.fastcampus.adminpage.ifs.CrudInterface;
import com.fastcampus.adminpage.model.network.Header;
import com.fastcampus.adminpage.model.network.request.OrderGroupApiRequest;
import com.fastcampus.adminpage.model.network.response.OrderGroupApiResponse;
import com.fastcampus.adminpage.service.OrderGroupApiLogicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order-group")
@RequiredArgsConstructor
@Slf4j
public class OrderGroupApiController implements CrudInterface<OrderGroupApiRequest, OrderGroupApiResponse> {

    private final OrderGroupApiLogicService orderGroupApiLogicService;

    @Override
    @PostMapping("")
    public Header<OrderGroupApiResponse> create(@RequestBody Header<OrderGroupApiRequest> request) {
        return orderGroupApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<OrderGroupApiResponse> read(@PathVariable Long id) {
        log.info("read id : {}", id);
        return orderGroupApiLogicService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<OrderGroupApiResponse> update(@RequestBody Header<OrderGroupApiRequest> request) {
        return orderGroupApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {
        log.info("delete id : {}", id);
        return orderGroupApiLogicService.delete(id);
    }
}
