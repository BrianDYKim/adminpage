package com.fastcampus.adminpage.controller;

import com.fastcampus.adminpage.model.network.Header;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GetController {

    @GetMapping("/header")
    public Header getHeader() {
        return Header.builder()
                .resultCode("OK")
                .description("OK")
                .build();
    }
}
