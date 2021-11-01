package com.fastcampus.adminpage.model.enumClass.adminUser;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AdminUserRole {
    SUPER(0, "슈퍼 관리자", "권한이 높은 관리자"),
    TOTAL_ADMIN(1, "총관리자", "시스템 전체를 관리하는 관리자");

    private Integer id;
    private String title;
    private String description;
}
