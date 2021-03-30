package com.example.demo.business.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {
    private String userId;
    private String userPw;
    private String userNm;
}
