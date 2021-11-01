package com.fastcampus.adminpage.model.network.request;

import com.fastcampus.adminpage.model.enumClass.OrderDetailStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailApiRequest {

    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderDetailStatus status;

    @JsonProperty("arrival_date")
    private LocalDateTime arrivalDate;

    private int quantity;

    @JsonProperty("total_price")
    private BigDecimal totalPrice;

    @JsonProperty("order_group_id")
    private Long orderGroupId;

    @JsonProperty("item_id")
    private Long itemId;

}
