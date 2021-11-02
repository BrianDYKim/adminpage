package com.fastcampus.adminpage.model.enumClass.orderGroup;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderGroupStatus {

    REGISTERED(0, "등록", "주문 묶음 등록"),
    UNREGISTERED(1, "해지", "주문 묶음 해지"),
    WAITING(2, "검수", "주문 묶음 검수중"),
    COMPLETE(3, "배송완료", "주문 묶음 배송완료"),
    ORDERING(4, "주문중", "주문 진행중"),
    CONFIRM(5, "주문 수락", "주문이 정상 수락되었습니다");

    private Integer id;
    private String title;
    private String description;
}
