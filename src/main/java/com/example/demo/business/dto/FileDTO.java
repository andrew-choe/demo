package com.example.demo.business.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class FileDTO {
    @NonNull
    private Long fileNo;

    private String fileName;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String uploadDt;
}
