package com.fastcampus.adminpage.service;

import com.fastcampus.adminpage.ifs.CrudInterface;
import com.fastcampus.adminpage.model.entity.OrderGroup;
import com.fastcampus.adminpage.model.entity.User;
import com.fastcampus.adminpage.model.enumClass.UserStatus;
import com.fastcampus.adminpage.model.network.Header;
import com.fastcampus.adminpage.model.network.Pagination;
import com.fastcampus.adminpage.model.network.request.UserApiRequest;
import com.fastcampus.adminpage.model.network.response.ItemApiResponse;
import com.fastcampus.adminpage.model.network.response.OrderGroupApiResponse;
import com.fastcampus.adminpage.model.network.response.UserApiResponse;
import com.fastcampus.adminpage.model.network.response.UserOrderInfoApiResponse;
import com.fastcampus.adminpage.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// Service 로직을 담당
@Service
@RequiredArgsConstructor
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {

    private final UserRepository userRepository;

    private final OrderGroupApiLogicService orderGroupApiLogicService;
    private final ItemApiLogicService itemApiLogicService;

    // 1. request data 가져오기
    // 2. user 생성
    // 3. 생성된 데이터 -> UserApiResponse return
    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

        // 1. request data 가져오기
        UserApiRequest requestData = request.getData();

        // 2. user 생성
        User user = User.builder()
                .account(requestData.getAccount())
                .password(requestData.getPassword())
                .status(UserStatus.REGISTERED)
                .phoneNumber(requestData.getPhoneNumber())
                .email(requestData.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();

        User newUser = userRepository.save(user);

        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {

        // 1. userRepository를 이용해 데이터 가져오기
        // 2. response return
        return userRepository.findById(id)
                .map(user -> response(user))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
        // 1. data
        UserApiRequest requestData = request.getData();

        // 2. id -> user 데이터 찾기
        Optional<User> optionalUser = userRepository.findById(requestData.getId());

        return optionalUser.map(user -> {
            user.setAccount(requestData.getAccount())
                    .setPassword(requestData.getPassword())
                    .setStatus(requestData.getStatus())
                    .setPhoneNumber(requestData.getPhoneNumber())
                    .setEmail(requestData.getEmail())
                    .setRegisteredAt(requestData.getRegisteredAt())
                    .setUnregisteredAt(requestData.getUnregisteredAt());

            return user;
        })
                .map(user -> userRepository.save(user))
                .map(user -> response(user))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        // 1. id -> repository -> user
        Optional<User> optionalUser = userRepository.findById(id);

        // 2. repository -> delete
        return optionalUser.map(user -> {
            userRepository.delete(user);

            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<List<UserApiResponse>> search(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable); // 페이지에 유저를 담기

        List<UserApiResponse> userApiResponseList = users.stream()
                .map(user -> responseData(user))
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPages(users.getTotalPages())
                .totalElements(users.getTotalElements())
                .currentPage(users.getNumber())
                .currentElements(users.getNumberOfElements())
                .build();

        return Header.OK(userApiResponseList, pagination);
    }

    // Response 부분 분리 -> 네 메소드에서 동시에 사용하는 기능이기 때문
    private Header<UserApiResponse> response(User user) {
        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();

        // Header + data return
        return Header.OK(userApiResponse);
    }

    // body만을 반환하는 메소드
    private UserApiResponse responseData(User user) {
        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();

        // data return
        return userApiResponse;
    }

    public Header<UserOrderInfoApiResponse> orderInfo(Long id) {

        // 사용자
        User user = userRepository.getById(id);
        UserApiResponse userApiResponse = responseData(user);

        // orderGroup
        List<OrderGroup> orderGroupList = user.getOrderGroupList();
        List<OrderGroupApiResponse> orderGroupApiResponseList = orderGroupList.stream()
                .map(orderGroup -> {
                    OrderGroupApiResponse orderGroupApiResponse = orderGroupApiLogicService.responseData(orderGroup);

                    // item api response
                    List<ItemApiResponse> itemApiResponseList = orderGroup.getOrderDetailList().stream()
                            .map(orderDetail -> orderDetail.getItem())
                            .map(item -> itemApiLogicService.responseData(item))
                            .collect(Collectors.toList());

                    orderGroupApiResponse.setItemApiResponseList(itemApiResponseList);
                    return orderGroupApiResponse;
                })
                .collect(Collectors.toList());

        userApiResponse.setOrderGroupApiResponseList(orderGroupApiResponseList);

        UserOrderInfoApiResponse userOrderInfoApiResponse = UserOrderInfoApiResponse.builder()
                .userApiResponse(userApiResponse)
                .build();

        return Header.OK(userOrderInfoApiResponse);
    }
}
