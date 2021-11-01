package com.fastcampus.adminpage.repository;

import com.fastcampus.adminpage.model.entity.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void create() {

    }

    @Test
    public void read() {
        Optional<Item> searchItem = itemRepository.findById(4L);

        searchItem.ifPresent(item -> {
            System.out.println(item);
        });
    }
}