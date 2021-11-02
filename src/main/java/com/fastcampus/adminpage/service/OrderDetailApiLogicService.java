package com.fastcampus.adminpage.service;

import com.fastcampus.adminpage.ifs.CrudInterface;
import com.fastcampus.adminpage.model.entity.OrderDetail;
import com.fastcampus.adminpage.model.network.Header;
import com.fastcampus.adminpage.model.network.Pagination;
import com.fastcampus.adminpage.model.network.request.OrderDetailApiRequest;
import com.fastcampus.adminpage.model.network.response.OrderDetailApiResponse;
import com.fastcampus.adminpage.repository.ItemRepository;
import com.fastcampus.adminpage.repository.OrderDetailRepository;
import com.fastcampus.adminpage.repository.OrderGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderDetailApiLogicService implements CrudInterface<OrderDetailApiRequest, OrderDetailApiResponse> {

    /** Dependency Injection of repositories */
    private final OrderGroupRepository orderGroupRepository;
    private final ItemRepository itemRepository;
    private final OrderDetailRepository orderDetailRepository;

    @Override
    public Header<OrderDetailApiResponse> create(Header<OrderDetailApiRequest> request) {
        /*
        1. request body 가져오기
        2. 데이터 생성
        3. 데이터 db에 밀어넣기
        4. return response
         */

        OrderDetailApiRequest requestBody = request.getData();

        OrderDetail orderDetail = OrderDetail.builder()
                .status(requestBody.getStatus())
                .arrivalDate(requestBody.getArrivalDate())
                .quantity(requestBody.getQuantity())
                .totalPrice(requestBody.getTotalPrice())
                .orderGroup(orderGroupRepository.getById(requestBody.getOrderGroupId()))
                .item(itemRepository.getById(requestBody.getItemId()))
                .build();

        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);

        return response(newOrderDetail);
    }

    @Override
    public Header<OrderDetailApiResponse> read(Long id) {
        return orderDetailRepository.findById(id)
                .map(orderDetail -> response(orderDetail))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<OrderDetailApiResponse> update(Header<OrderDetailApiRequest> request) {
        /*
        1. request body 끌어오기
        2. request body의 id를 통해서 db의 데이터를 가져오기
        3. data modify -> update
        4. return response
         */

        OrderDetailApiRequest requestBody = request.getData();

        return orderDetailRepository.findById(requestBody.getId())
                .map(entityOrderDetail -> {
                    entityOrderDetail.setStatus(requestBody.getStatus())
                            .setArrivalDate(requestBody.getArrivalDate())
                            .setQuantity(requestBody.getQuantity())
                            .setTotalPrice(requestBody.getTotalPrice())
                            .setOrderGroup(orderGroupRepository.getById(requestBody.getOrderGroupId()))
                            .setItem(itemRepository.getById(requestBody.getItemId()));

                    return entityOrderDetail;
                })
                .map(orderDetail -> orderDetailRepository.save(orderDetail))
                .map(orderDetail -> response(orderDetail))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        /*
        1. id를 통해서 데이터 가져오기
        2. db의 데이터 삭제
        3. return response with no data
         */

        return orderDetailRepository.findById(id)
                .map(orderDetail -> {
                    orderDetailRepository.delete(orderDetail);
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<List<OrderDetailApiResponse>> search(Pageable pageable) {
        // 페이지 가져오기
        Page<OrderDetail> orderDetails = orderDetailRepository.findAll(pageable);

        // response list 구성
        List<OrderDetailApiResponse> orderDetailApiResponseList = orderDetails.stream()
                .map(orderDetail -> responseData(orderDetail))
                .collect(Collectors.toList());

        // 페이지 정보 구성
        Pagination pagination = Pagination.builder()
                .totalPages(orderDetails.getTotalPages())
                .totalElements(orderDetails.getTotalElements())
                .currentPage(orderDetails.getNumber())
                .currentElements(orderDetails.getNumberOfElements())
                .build();

        return Header.OK(orderDetailApiResponseList, pagination);
    }

    private Header<OrderDetailApiResponse> response(OrderDetail orderDetail) {
        OrderDetailApiResponse body = OrderDetailApiResponse.builder()
                .id(orderDetail.getId())
                .status(orderDetail.getStatus())
                .arrivalDate(orderDetail.getArrivalDate())
                .quantity(orderDetail.getQuantity())
                .totalPrice(orderDetail.getTotalPrice())
                .orderGroupId(orderDetail.getOrderGroup().getId())
                .itemId(orderDetail.getItem().getId())
                .build();

        return Header.OK(body);
    }

    private OrderDetailApiResponse responseData(OrderDetail orderDetail) {
        OrderDetailApiResponse body = OrderDetailApiResponse.builder()
                .id(orderDetail.getId())
                .status(orderDetail.getStatus())
                .arrivalDate(orderDetail.getArrivalDate())
                .quantity(orderDetail.getQuantity())
                .totalPrice(orderDetail.getTotalPrice())
                .orderGroupId(orderDetail.getOrderGroup().getId())
                .itemId(orderDetail.getItem().getId())
                .build();

        return body;
    }
}
