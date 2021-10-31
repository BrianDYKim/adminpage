package com.fastcampus.adminpage.service;

import com.fastcampus.adminpage.ifs.CrudInterface;
import com.fastcampus.adminpage.model.entity.Item;
import com.fastcampus.adminpage.model.network.Header;
import com.fastcampus.adminpage.model.network.request.ItemApiRequest;
import com.fastcampus.adminpage.model.network.response.ItemApiResponse;
import com.fastcampus.adminpage.repository.ItemRepository;
import com.fastcampus.adminpage.repository.PartnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
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
        return null;
    }

    @Override
    public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {
        return null;
    }

    @Override
    public Header delete(Long id) {
        return null;
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
