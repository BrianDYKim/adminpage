package com.fastcampus.adminpage.service;

import com.fastcampus.adminpage.ifs.CrudInterface;
import com.fastcampus.adminpage.model.entity.AdminUser;
import com.fastcampus.adminpage.model.network.Header;
import com.fastcampus.adminpage.model.network.Pagination;
import com.fastcampus.adminpage.model.network.request.AdminUserApiRequest;
import com.fastcampus.adminpage.model.network.response.AdminUserApiResponse;
import com.fastcampus.adminpage.repository.AdminUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminUserApiLogicService implements CrudInterface<AdminUserApiRequest, AdminUserApiResponse> {

    private final AdminUserRepository adminUserRepository;

    @Override
    public Header<AdminUserApiResponse> create(Header<AdminUserApiRequest> request) {
        /*
        1. request body 가져오기
        2. 데이터 생성
        3. db에 데이터 삽입
        4. return response
         */

        AdminUserApiRequest requestBody = request.getData();

        AdminUser adminUser = AdminUser.builder()
                .account(requestBody.getAccount())
                .password(requestBody.getPassword())
                .status(requestBody.getStatus())
                .role(requestBody.getRole())
                .registeredAt(LocalDateTime.now())
                .build();

        AdminUser newAdminUser = adminUserRepository.save(adminUser);

        return response(newAdminUser);
    }

    @Override
    public Header<AdminUserApiResponse> read(Long id) {
        return adminUserRepository.findById(id)
                .map(adminUser -> response(adminUser))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<AdminUserApiResponse> update(Header<AdminUserApiRequest> request) {
        /*
        1. request data 가져오기 -> id 추출
        2. request data의 id를 통해 db의 데이터 가져오기
        3. 데이터 modify -> commit
        4. return response
         */

        AdminUserApiRequest requestBody = request.getData();

        return adminUserRepository.findById(requestBody.getId())
                .map(entityAdminUser -> {
                    entityAdminUser.setAccount(requestBody.getAccount())
                            .setPassword(requestBody.getPassword())
                            .setStatus(requestBody.getStatus())
                            .setRole(requestBody.getRole())
                            .setLastLoginedAt(requestBody.getLastLoginedAt())
                            .setPasswordUpdatedAt(requestBody.getPasswordUpdatedAt())
                            .setLoginFailCount(requestBody.getLoginFailCount())
                            .setUnregisteredAt(requestBody.getUnregisteredAt());

                    return entityAdminUser;
                })
                .map(adminUser -> adminUserRepository.save(adminUser))
                .map(adminUser -> response(adminUser))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        /*
        1. id를 통해서 db의 데이터 가져오기
        2. 데이터를 db에서 삭제
        3. return response with no data
         */

        return adminUserRepository.findById(id)
                .map(adminUser -> {
                    adminUserRepository.delete(adminUser);
                    return Header.OK();
                }).orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<List<AdminUserApiResponse>> search(Pageable pageable) {
        Page<AdminUser> adminUsers = adminUserRepository.findAll(pageable);

        List<AdminUserApiResponse> adminUserApiResponseList = adminUsers.stream()
                .map(adminUser -> responseData(adminUser))
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPages(adminUsers.getTotalPages())
                .totalElements(adminUsers.getTotalElements())
                .currentPage(adminUsers.getNumber())
                .currentElements(adminUsers.getNumberOfElements())
                .build();

        return Header.OK(adminUserApiResponseList, pagination);
    }

    private Header<AdminUserApiResponse> response(AdminUser adminUser) {
        AdminUserApiResponse body = AdminUserApiResponse.builder()
                .id(adminUser.getId())
                .account(adminUser.getAccount())
                .password(adminUser.getPassword())
                .status(adminUser.getStatus())
                .role(adminUser.getRole())
                .lastLoginedAt(adminUser.getLastLoginedAt())
                .passwordUpdatedAt(adminUser.getPasswordUpdatedAt())
                .loginFailCount(adminUser.getLoginFailCount())
                .registeredAt(adminUser.getRegisteredAt())
                .unregisteredAt(adminUser.getUnregisteredAt())
                .build();

        return Header.OK(body);
    }

    private AdminUserApiResponse responseData(AdminUser adminUser) {
        AdminUserApiResponse body = AdminUserApiResponse.builder()
                .id(adminUser.getId())
                .account(adminUser.getAccount())
                .password(adminUser.getPassword())
                .status(adminUser.getStatus())
                .role(adminUser.getRole())
                .lastLoginedAt(adminUser.getLastLoginedAt())
                .passwordUpdatedAt(adminUser.getPasswordUpdatedAt())
                .loginFailCount(adminUser.getLoginFailCount())
                .registeredAt(adminUser.getRegisteredAt())
                .unregisteredAt(adminUser.getUnregisteredAt())
                .build();

        return body;
    }
}
