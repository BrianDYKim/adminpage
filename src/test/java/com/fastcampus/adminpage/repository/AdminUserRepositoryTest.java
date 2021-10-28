package com.fastcampus.adminpage.repository;

import com.fastcampus.adminpage.model.entity.AdminUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class AdminUserRepositoryTest {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Test
    public void create() {
        AdminUser adminUser = AdminUser.builder()
                .account("AdminUser02")
                .password("AdminUser02")
                .status("REGISTERED")
                .role("SUPER")
                .build();

        AdminUser newAdminUser = adminUserRepository.save(adminUser);

        Assert.notNull(newAdminUser, "AdminUser is Null!");

        System.out.println(newAdminUser);
    }

    @Test
    public void read() {

    }
}