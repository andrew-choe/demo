package com.example.demo.business.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class ResponseDTO<T> {
    @NonNull
    private String resultCd;

    @NonNull
    private String resultMsg;

    private List<T> resultList;
}
