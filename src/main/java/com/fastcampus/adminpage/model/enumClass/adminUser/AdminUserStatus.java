package com.fastcampus.adminpage.model.enumClass.adminUser;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AdminUserStatus {

    REGISTERED(0, "등록", "관리자 등록상태"),
    UNREGISTERED(1, "해지", "관리자 등록해지");

    private Integer id;
    private String title;
    private String description;
}
