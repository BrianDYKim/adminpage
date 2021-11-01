package com.fastcampus.adminpage.model.network.request;

import com.fastcampus.adminpage.model.enumClass.adminUser.AdminUserRole;
import com.fastcampus.adminpage.model.enumClass.adminUser.AdminUserStatus;
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
public class AdminUserApiRequest {

    private Long id;

    private String account;

    private String password;

    @Enumerated(EnumType.STRING)
    private AdminUserStatus status;

    @Enumerated(EnumType.STRING)
    private AdminUserRole role;

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
