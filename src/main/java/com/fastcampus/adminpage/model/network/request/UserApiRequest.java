package com.fastcampus.adminpage.model.network.request;

import com.fastcampus.adminpage.model.enumClass.UserStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserApiRequest {
    // createdAt 등등은 통신 과정에 사용하지 않기 때문에 포함시키지 않음.

    private Long id;

    private String account;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    private String email;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("registered_at")
    private LocalDateTime registeredAt;

    @JsonProperty("unregistered_at")
    private LocalDateTime unregisteredAt;
}
