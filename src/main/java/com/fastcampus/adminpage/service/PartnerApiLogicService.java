package com.fastcampus.adminpage.service;

import com.fastcampus.adminpage.ifs.CrudInterface;
import com.fastcampus.adminpage.model.entity.Partner;
import com.fastcampus.adminpage.model.network.Header;
import com.fastcampus.adminpage.model.network.request.PartnerApiRequest;
import com.fastcampus.adminpage.model.network.response.PartnerApiResponse;
import com.fastcampus.adminpage.repository.CategoryRepository;
import com.fastcampus.adminpage.repository.PartnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PartnerApiLogicService implements CrudInterface<PartnerApiRequest, PartnerApiResponse> {

    private final CategoryRepository categoryRepository;
    private final PartnerRepository partnerRepository;

    @Override
    public Header<PartnerApiResponse> create(Header<PartnerApiRequest> request) {

        /*
        1. request body 가져오기
        2. body를 통해 데이터 생성
        3. 데이터 db에 밀어넣기
        4. return response
         */

        PartnerApiRequest requestBody = request.getData();

        Partner partner = Partner.builder()
                .name(requestBody.getName())
                .status(requestBody.getStatus())
                .address(requestBody.getAddress())
                .callCenter(requestBody.getCallCenter())
                .partnerNumber(requestBody.getPartnerNumber())
                .businessNumber(requestBody.getBusinessNumber())
                .ceoName(requestBody.getCeoName())
                .registeredAt(LocalDateTime.now())
                .category(categoryRepository.getById(requestBody.getCategoryId()))
                .build();

        Partner newPartner = partnerRepository.save(partner);

        return response(newPartner);
    }

    @Override
    public Header<PartnerApiResponse> read(Long id) {
        return partnerRepository.findById(id)
                .map(partner -> response(partner))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<PartnerApiResponse> update(Header<PartnerApiRequest> request) {
        return null;
    }

    @Override
    public Header delete(Long id) {
        return null;
    }

    private Header<PartnerApiResponse> response(Partner partner) {
        PartnerApiResponse body = PartnerApiResponse.builder()
                .id(partner.getId())
                .name(partner.getName())
                .status(partner.getStatus())
                .address(partner.getAddress())
                .callCenter(partner.getCallCenter())
                .partnerNumber(partner.getPartnerNumber())
                .businessNumber(partner.getBusinessNumber())
                .ceoName(partner.getCeoName())
                .registeredAt(partner.getRegisteredAt())
                .unregisteredAt(partner.getUnregisteredAt())
                .categoryId(partner.getCategory().getId())
                .build();

        return Header.OK(body);
    }
}
