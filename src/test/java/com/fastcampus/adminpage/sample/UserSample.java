package com.fastcampus.adminpage.sample;

import com.fastcampus.adminpage.model.entity.User;
import com.fastcampus.adminpage.model.enumClass.UserStatus;
import com.fastcampus.adminpage.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Random;

@Slf4j
@SpringBootTest
public class UserSample {

    private Random random;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void sampleCreate(){

        random = new Random();

        for(int i = 1 ; i < 1001; i++){
            // 가입 상태 랜덤
            int div = (random.nextInt(10)+1) % 2;
            UserStatus status = (div == 0 ? UserStatus.REGISTERED : UserStatus.UNREGISTERED);

            User user = User.builder()
                    .account("TestUser"+i)
                    .password("password"+i)
                    .status(status)
                    .email("TestUser"+i+"@gmail.com")
                    .phoneNumber("010-1111-"+String.format("%04d", i))
                    .registeredAt(getRandomDate())
                    .unregisteredAt(status.equals(UserStatus.UNREGISTERED) ? getRandomDate() : null )
                    .build();

            userRepository.save(user);
        }


    }


    private LocalDateTime getRandomDate(){
        return LocalDateTime.of(2019,getRandomNumber(),getRandomNumber(),getRandomNumber(),getRandomNumber(),getRandomNumber());
    }

    private int getRandomNumber(){
        return random.nextInt(11)+1;
    }
}
