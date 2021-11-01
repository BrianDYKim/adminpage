package com.fastcampus.adminpage.model.network.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderGroupApiResponse {

    private Long id;

    private String status;

    @JsonProperty("order_type")
    private String orderType; // 묶음 배송? 개별 배송?

    @JsonProperty("rev_address")
    private String revAddress;

    @JsonProperty("rev_name")
    private String revName;

    @JsonProperty("payment_type")
    private String paymentType;

    @JsonProperty("total_price")
    private BigDecimal totalPrice;

    @JsonProperty("total_quantity")
    private Integer totalQuantity;

    @JsonProperty("order_at")
    private LocalDateTime orderAt;

    @JsonProperty("arrival_date")
    private LocalDateTime arrivalDate;

    @JsonProperty("user_id")
    private Long userId;
}
