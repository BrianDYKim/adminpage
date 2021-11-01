package com.fastcampus.adminpage.model.enumClass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderDetailStatus {
    REGISTERED(0, "등록", "주문 등록"),
    UNREGISTERED(1, "해지", "주문 해지"),
    WAITING(2, "검수", "주문 검수중"),
    COMPLETE(3, "완료", "배송 완료");

    private Integer id;
    private String title;
    private String description;
}
