package com.fastcampus.adminpage.controller.api;

import com.fastcampus.adminpage.ifs.CrudInterface;
import com.fastcampus.adminpage.model.network.Header;
import com.fastcampus.adminpage.model.network.request.ItemApiRequest;
import com.fastcampus.adminpage.model.network.response.ItemApiResponse;
import com.fastcampus.adminpage.service.ItemApiLogicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/item")
@RequiredArgsConstructor
@Slf4j
public class ItemApiController implements CrudInterface<ItemApiRequest, ItemApiResponse> {

    private final ItemApiLogicService itemApiLogicService;

    @Override
    @PostMapping("") // api/item
    public Header<ItemApiResponse> create(@RequestBody Header<ItemApiRequest> request) {
        return itemApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}") // api/item/1 ... 1000
    public Header<ItemApiResponse> read(@PathVariable Long id) {
        log.info("read id : {}", id);
        return itemApiLogicService.read(id);
    }

    @Override
    @PutMapping("") // api/item
    public Header<ItemApiResponse> update(@RequestBody Header<ItemApiRequest> request) {
        return itemApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}") // api/item
    public Header delete(@PathVariable Long id) {
        log.info("delete id : {}", id);

        return itemApiLogicService.delete(id);
    }
}
