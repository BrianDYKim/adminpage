package com.fastcampus.adminpage.service;

import com.fastcampus.adminpage.ifs.CrudInterface;
import com.fastcampus.adminpage.model.entity.Item;
import com.fastcampus.adminpage.model.network.Header;
import com.fastcampus.adminpage.model.network.request.ItemApiRequest;
import com.fastcampus.adminpage.model.network.response.ItemApiResponse;
import com.fastcampus.adminpage.repository.ItemRepository;
import com.fastcampus.adminpage.repository.PartnerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemApiLogicService implements CrudInterface<ItemApiRequest, ItemApiResponse> {

    private final ItemRepository itemRepository;
    private final PartnerRepository partnerRepository;

    @Override
    public Header<ItemApiResponse> create(Header<ItemApiRequest> request) {

        // 1. request data "안전하게" 가져오기
        ItemApiRequest requestData = request.getData();

        // 2. item 생성
        Item item = Item.builder()
                .status(requestData.getStatus())
                .name(requestData.getName())
                .title(requestData.getTitle())
                .content(requestData.getContent())
                .price(requestData.getPrice())
                .brandName(requestData.getBrandName())
                .registeredAt(LocalDateTime.now())
                .partner(partnerRepository.getById(requestData.getPartnerId()))
                .build();

        Item newItem = itemRepository.save(item);

        return response(newItem);
    }

    @Override
    public Header<ItemApiResponse> read(Long id) {
        return itemRepository.findById(id)
                .map(this::response)
                .orElseGet(() -> Header.ERROR("데아터 없음"));
    }

    @Override
    public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {
        /*
        1. 데이터 가져오기
        2. 데이터 수정하기
        3. 데이터 저장하기
        4. return response
         */

        ItemApiRequest requestData = request.getData();
        Optional<Item> targetItem = itemRepository.findById(requestData.getId());

        return targetItem.map(item -> {
            item.setStatus(requestData.getStatus())
                    .setName(requestData.getName())
                    .setTitle(requestData.getTitle())
                    .setContent(requestData.getContent())
                    .setPrice(requestData.getPrice())
                    .setBrandName(requestData.getBrandName())
                    .setRegisteredAt(requestData.getRegisteredAt())
                    .setUnregisteredAt(requestData.getUnregisteredAt());

            return item;
        }).map(item -> itemRepository.save(item))
                .map(item -> response(item))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        /*
        1. 데이터 불러오기
        2. delete 수행
        3. data 부분이 없는 Header 반환
         */

        // map에는 앞에 return을 달아서 줄줄이 소세지로 이어야함.
        return itemRepository.findById(id).map(item -> {
            itemRepository.delete(item);

            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    private Header<ItemApiResponse> response(Item item) {
        ItemApiResponse body = ItemApiResponse.builder()
                .id(item.getId())
                .status(item.getStatus())
                .name(item.getName())
                .title(item.getTitle())
                .content(item.getContent())
                .price(item.getPrice())
                .brandName(item.getBrandName())
                .registeredAt(item.getRegisteredAt())
                .unregisteredAt(item.getUnregisteredAt())
                .partnerId(item.getPartner().getId())
                .build();

        return Header.OK(body);
    }
}
