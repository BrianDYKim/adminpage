package com.fastcampus.adminpage.repository;

import com.fastcampus.adminpage.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void create() {
        User user = User.builder()
                .account("Test02")
                .password("Test02")
                .status("REGISTERED")
                .email("Test02@naver.com")
                .phoneNumber("010-0000-0002")
                .registeredAt(LocalDateTime.now())
                .build();

        User newUser = userRepository.save(user);

        Assert.notNull(newUser, "User is Null!");

        System.out.println(newUser);
    }

    @Test
    @Transactional
    public void read() {
        Optional<User> user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-0000-0001");

        user.ifPresent(u -> {
            u.getOrderGroupList().forEach(orderGroup -> {
                System.out.println("---------- 주문묶음 ----------");
                System.out.println("수령인 : " + orderGroup.getRevName());
                System.out.println("수령지 : " + orderGroup.getRevAddress());
                System.out.println("총금액 : " + orderGroup.getTotalPrice());
                System.out.println("총수량 : " + orderGroup.getTotalQuantity());

                System.out.println("---------- 주문상세 ----------");

                orderGroup.getOrderDetailList().forEach(orderDetail -> {
                    System.out.println("파트너사 이름 : " + orderDetail.getItem().getPartner().getName());
                    System.out.println("파트너사 카테고리 : " + orderDetail.getItem().getPartner().getCategory().getType());
                    System.out.println("주문 상품 : " + orderDetail.getItem().getName());
                    System.out.println("고객센터 번호 : " + orderDetail.getItem().getPartner().getCallCenter());
                    System.out.println("주문의 상태 : " + orderDetail.getStatus());
                    System.out.println("도착 예정일자 : " + orderDetail.getArrivalDate());
                });
            });
        });
    }
}