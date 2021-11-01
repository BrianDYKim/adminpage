package com.fastcampus.adminpage.controller.api;

import com.fastcampus.adminpage.ifs.CrudInterface;
import com.fastcampus.adminpage.model.network.Header;
import com.fastcampus.adminpage.model.network.request.PartnerApiRequest;
import com.fastcampus.adminpage.model.network.response.PartnerApiResponse;
import com.fastcampus.adminpage.service.PartnerApiLogicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/partner")
@RequiredArgsConstructor
@Slf4j
public class PartnerApiController implements CrudInterface<PartnerApiRequest, PartnerApiResponse> {

    private final PartnerApiLogicService partnerApiLogicService;

    @Override
    @PostMapping("")
    public Header<PartnerApiResponse> create(@RequestBody Header<PartnerApiRequest> request) {
        return partnerApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<PartnerApiResponse> read(@PathVariable Long id) {
        log.info("read id : {}", id);
        return partnerApiLogicService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<PartnerApiResponse> update(@RequestBody Header<PartnerApiRequest> request) {
        return partnerApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {
        log.info("delete id : {}", id);
        return partnerApiLogicService.delete(id);
    }
}
