package com.fastcampus.adminpage.controller.api;

import com.fastcampus.adminpage.ifs.CrudInterface;
import com.fastcampus.adminpage.model.network.Header;
import com.fastcampus.adminpage.model.network.request.UserApiRequest;
import com.fastcampus.adminpage.model.network.response.UserApiResponse;
import com.fastcampus.adminpage.service.UserApiLogicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserApiController implements CrudInterface<UserApiRequest, UserApiResponse> {

    // Controller와 Service 연결
    private final UserApiLogicService userApiLogicService;

    @Override
    @PostMapping("") // api/user
    public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> userApiRequest) {
        log.info("{}", userApiRequest);
        return userApiLogicService.create(userApiRequest);
    }

    @Override
    @GetMapping("{id}") // read의 경우 id를 PathVariable의 형식으로 받아와야함. api/user/{id}
    public Header read(@PathVariable Long id) {
        return null;
    }

    @Override
    @PutMapping("") // api/user
    public Header<UserApiResponse> update(@RequestBody Header<UserApiRequest> userApiRequest) {
        return null;
    }

    @Override
    @DeleteMapping("{id}") // api/user/{id}
    public Header delete(@PathVariable Long id) {
        return null;
    }
}
