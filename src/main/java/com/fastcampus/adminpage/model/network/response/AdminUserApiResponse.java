package com.fastcampus.adminpage.model.network.response;

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
public class AdminUserApiResponse {

    private Long id;

    private String account;

    private String password;

    private String status;

    private String role;

    @JsonProperty("last_logined_at")
    private LocalDateTime lastLoginedAt;

    @JsonProperty("password_updated_at")
    private LocalDateTime passwordUpdatedAt;

    @JsonProperty("login_fail_count")
    private int loginFailCount;

    @JsonProperty("registered_at")
    private LocalDateTime registeredAt;

    @JsonProperty("unregistered_at")
    private LocalDateTime unregisteredAt;
}
