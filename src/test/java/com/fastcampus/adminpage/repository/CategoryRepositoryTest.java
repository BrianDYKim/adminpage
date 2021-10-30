package com.fastcampus.adminpage.repository;

import com.fastcampus.adminpage.model.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void create() {
        Category category = Category.builder()
                .type("COMPUTER")
                .title("컴퓨터")
                .createdAt(LocalDateTime.now())
                .createdBy("AdminServer")
                .build();

        Category newCategory = categoryRepository.save(category);
        System.out.println(newCategory);
    }

    @Test
    public void read() {
        // SELECT * from category where type = 'COMPUTER'
        Optional<Category> category = categoryRepository.findByType("COMPUTER");

        category.ifPresent(System.out::println);
    }

    @Test
    public void test() {
        categoryRepository.findById(1L).ifPresent(category -> {
            categoryRepository.delete(category);
        });
    }
}