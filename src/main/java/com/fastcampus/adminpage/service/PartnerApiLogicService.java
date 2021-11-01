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
        /*
        1. request body 가져오기
        2. body에서 id를 가져와서 db에서 데이터 추출
        3. 데이터 수정
        4. return response
         */

        PartnerApiRequest requestBody = request.getData();

        return partnerRepository.findById(requestBody.getId())
                .map(entityPartner -> {
                    entityPartner.setName(requestBody.getName())
                            .setStatus(requestBody.getStatus())
                            .setAddress(requestBody.getAddress())
                            .setCallCenter(requestBody.getCallCenter())
                            .setPartnerNumber(requestBody.getPartnerNumber())
                            .setBusinessNumber(requestBody.getBusinessNumber())
                            .setCeoName(requestBody.getCeoName())
                            .setRegisteredAt(requestBody.getRegisteredAt())
                            .setUnregisteredAt(requestBody.getUnregisteredAt())
                            .setCategory(categoryRepository.getById(requestBody.getCategoryId()));

                    return entityPartner;
                })
                .map(partner -> partnerRepository.save(partner))
                .map(partner -> response(partner))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        /*
        1. id를 통해 db에서 데이터 불러오기
        2. 데이터 삭제
        3. return response with no data
         */

        return partnerRepository.findById(id)
                .map(partner -> {
                    partnerRepository.delete(partner);
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("데이터 없음"));
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
