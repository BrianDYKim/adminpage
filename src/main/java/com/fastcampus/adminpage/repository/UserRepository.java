package com.fastcampus.adminpage.repository;

import com.fastcampus.adminpage.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 휴대폰 번호로 유저를 검색하여 아이디 기준 내림차순 첫번째(i.e 마지막 유저)에 위치한 유저를 리턴한다. 없으면 Optional에 null이 담긴다.
    Optional<User> findFirstByPhoneNumberOrderByIdDesc(String phoneNumber);
}
