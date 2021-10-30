package com.fastcampus.adminpage.model.network.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserApiRequest {
    // createdAt 등등은 통신 과정에 사용하지 않기 때문에 포함시키지 않음.

    private Long id;

    private String account;

    private String password;

    private String status;

    private String email;

    @JsonProperty("phone_number")
    private String phoneNumber;
}
