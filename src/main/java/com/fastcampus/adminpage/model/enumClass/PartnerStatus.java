package com.fastcampus.adminpage.model.enumClass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PartnerStatus {
    REGISTERED(0, "등록", "파트너 등록상태"),
    UNREGISTERED(1, "해지", "파트너 계약해지");

    private Integer id;
    private String title;
    private String description;
}
