package com.fastcampus.adminpage.model.network.response;

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
public class UserApiResponse {

    private Long id;

    private String account;

    private String password; // 암호화해서 들어가기 때문에 따로 관리하는게 맞음!

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
