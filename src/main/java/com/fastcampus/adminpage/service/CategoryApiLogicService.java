package com.fastcampus.adminpage.service;

import com.fastcampus.adminpage.ifs.CrudInterface;
import com.fastcampus.adminpage.model.entity.Category;
import com.fastcampus.adminpage.model.network.Header;
import com.fastcampus.adminpage.model.network.request.CategoryApiRequest;
import com.fastcampus.adminpage.model.network.response.CategoryApiResponse;
import com.fastcampus.adminpage.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryApiLogicService implements CrudInterface<CategoryApiRequest, CategoryApiResponse> {

    private final CategoryRepository categoryRepository;

    @Override
    public Header<CategoryApiResponse> create(Header<CategoryApiRequest> request) {
        /*
        1. 데이터 복사
        2. body 생성
        3. 데이터 commit
        4. return response
         */

        CategoryApiRequest body = request.getData();

        Category category = Category.builder()
                .type(body.getType())
                .title(body.getTitle())
                .build();

        Category newCategory = categoryRepository.save(category);

        return response(newCategory);
    }

    @Override
    public Header<CategoryApiResponse> read(Long id) {

        return categoryRepository.findById(id)
                .map(category -> response(category))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<CategoryApiResponse> update(Header<CategoryApiRequest> request) {
        /*
        1. request body 가져오기
        2. request body의 id를 가져와서 데이터 가져오기
        3. 데이터 modify -> commit
        4. return response
         */

        CategoryApiRequest requestBody = request.getData();

        return categoryRepository.findById(requestBody.getId())
                .map(entityCategory -> {
                    entityCategory.setType(requestBody.getType())
                            .setTitle(requestBody.getTitle());

                    return entityCategory;
                })
                .map(category -> categoryRepository.save(category))
                .map(category -> response(category))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        /*
        1. id에 해당하는 db의 데이터 가져오기
        2. 삭제하기
        3. return response (with no body)
         */

        return categoryRepository.findById(id)
                .map(category -> {
                    categoryRepository.delete(category);
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    private Header<CategoryApiResponse> response(Category category) {
        CategoryApiResponse body = CategoryApiResponse.builder()
                .id(category.getId())
                .type(category.getType())
                .title(category.getTitle())
                .build();

        return Header.OK(body);
    }
}
