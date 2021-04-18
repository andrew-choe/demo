package com.example.demo.business.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {
    @NonNull
    private String userId;

    private String userPw;

    private String userNm;

    public UserDTO(String userId, String userNm) {
        this.userId = userId;
        this.userNm = userNm;
    }
}
