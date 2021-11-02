package com.fastcampus.adminpage.model.network.response;

import com.fastcampus.adminpage.model.enumClass.orderGroup.OrderGroupStatus;
import com.fastcampus.adminpage.model.enumClass.orderGroup.OrderType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderGroupApiResponse {

    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderGroupStatus status;

    @JsonProperty("order_type")
    @Enumerated(EnumType.STRING)
    private OrderType orderType; // 묶음 배송? 개별 배송?

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

    @JsonProperty("item_api_response_list")
    private List<ItemApiResponse> itemApiResponseList;
}
