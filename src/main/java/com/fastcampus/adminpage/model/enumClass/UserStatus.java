package com.fastcampus.adminpage.model.enumClass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {

    REGISTERED(0, "등록상태", "사용자 등록상태"),
    UNREGISTERED(1, "해지", "사용자 해지상태");

    private Integer id;
    private String title;
    private String description;
}
