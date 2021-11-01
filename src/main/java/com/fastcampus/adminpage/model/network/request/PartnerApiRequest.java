package com.fastcampus.adminpage.model.network.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartnerApiRequest {

    private Long id;

    private String name;

    private String status;

    private String address;

    @JsonProperty("call_center")
    private String callCenter;

    @JsonProperty("partner_number")
    private String partnerNumber;

    @JsonProperty("business_number")
    private String businessNumber;

    @JsonProperty("ceo_name")
    private String ceoName;

    @JsonProperty("registered_at")
    private LocalDateTime registeredAt;

    @JsonProperty("unregistered_at")
    private LocalDateTime unregisteredAt;

    @JsonProperty("category_id")
    private Long categoryId;
}
