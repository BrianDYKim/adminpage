package com.fastcampus.adminpage.model.network;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Header<T> {

    // api 통신 시간
    @JsonProperty("transaction_time")
    private LocalDateTime transactionTime; // Front-End와 통신할 때는 String을 사용해야함. 하지만 우리는 표준을 따를 예정이라 LocalDateTime 사용

    // api 응답 코드
    @JsonProperty("result_code")
    private String resultCode;

    // api 부가 설명
    private String description;

    // data 부분 (data는 보통 제네릭으로 구현함.)
    private T data;

    // Pagination
    private Pagination pagination;

    // OK method with no data
    public static <T> Header<T> OK() {
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .build();
    }

    // OK method with data
    public static <T> Header<T> OK(T data) {
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .data(data)
                .build();
    }

    public static <T> Header<T> OK(T data, Pagination pagination) {
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .data(data)
                .pagination(pagination)
                .build();
    }

    // Error method
    public static <T> Header<T> ERROR(String description) {
        return (Header<T>) Header.<T>builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("ERROR")
                .description(description)
                .build();
    }

}
