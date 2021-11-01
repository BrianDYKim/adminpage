package com.fastcampus.adminpage.service;

import com.fastcampus.adminpage.ifs.CrudInterface;
import com.fastcampus.adminpage.model.entity.OrderGroup;
import com.fastcampus.adminpage.model.network.Header;
import com.fastcampus.adminpage.model.network.request.OrderGroupApiRequest;
import com.fastcampus.adminpage.model.network.response.OrderGroupApiResponse;
import com.fastcampus.adminpage.repository.OrderGroupRepository;
import com.fastcampus.adminpage.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderGroupApiLogicService implements CrudInterface<OrderGroupApiRequest, OrderGroupApiResponse> {

    private final OrderGroupRepository orderGroupRepository;
    private final UserRepository userRepository;

    @Override
    public Header<OrderGroupApiResponse> create(Header<OrderGroupApiRequest> request) {

        // 요청은 데이터베이스에 명시된대로
        // 객체를 생성할 때는 연관관계 때문에 객체로
        OrderGroupApiRequest body = request.getData();

        OrderGroup orderGroup = OrderGroup.builder()
                .status(body.getStatus())
                .orderType(body.getOrderType())
                .revAddress(body.getRevAddress())
                .revName(body.getRevName())
                .paymentType(body.getPaymentType())
                .totalPrice(body.getTotalPrice())
                .totalQuantity(body.getTotalQuantity())
                .orderAt(LocalDateTime.now())
                .user(userRepository.getById(body.getUserId()))
                .build();

        OrderGroup newOrderGroup = orderGroupRepository.save(orderGroup);

        return response(newOrderGroup);
    }

    @Override
    public Header<OrderGroupApiResponse> read(Long id) {
        return orderGroupRepository.findById(id)
                .map(orderGroup -> response(orderGroup))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<OrderGroupApiResponse> update(Header<OrderGroupApiRequest> request) {
        /*
        1. 데이터 가져오기
        2. 데이터 수정하기
        3. 데이터 modify -> commit
        4. return response
         */

        OrderGroupApiRequest requestBody = request.getData();

        return orderGroupRepository.findById(requestBody.getId())
                .map(entityOrderGroup -> {
                    entityOrderGroup.setStatus(requestBody.getStatus())
                            .setOrderType(requestBody.getOrderType())
                            .setRevAddress(requestBody.getRevAddress())
                            .setRevName(requestBody.getRevName())
                            .setPaymentType(requestBody.getPaymentType())
                            .setTotalPrice(requestBody.getTotalPrice())
                            .setTotalQuantity(requestBody.getTotalQuantity())
                            .setOrderAt(requestBody.getOrderAt());

                    return entityOrderGroup;
                }).map(entityOrderGroup -> orderGroupRepository.save(entityOrderGroup))
                .map(orderGroup -> response(orderGroup))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        return orderGroupRepository.findById(id)
                .map(orderGroup -> {
                    orderGroupRepository.delete(orderGroup);
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    private Header<OrderGroupApiResponse> response(OrderGroup orderGroup) {
        OrderGroupApiResponse body = OrderGroupApiResponse.builder()
                .id(orderGroup.getId())
                .status(orderGroup.getStatus())
                .orderType(orderGroup.getOrderType())
                .revAddress(orderGroup.getRevAddress())
                .revName(orderGroup.getRevName())
                .paymentType(orderGroup.getPaymentType())
                .totalPrice(orderGroup.getTotalPrice())
                .totalQuantity(orderGroup.getTotalQuantity())
                .orderAt(orderGroup.getOrderAt())
                .arrivalDate(orderGroup.getArrivalDate())
                .userId(orderGroup.getUser().getId())
                .build();

        return Header.OK(body);
    }
}
