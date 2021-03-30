package com.example.demo.business.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

@Data
@AllArgsConstructor
@Getter
@Setter
public class ResponseDTO {
    private String message;
    private List<HashMap<String, Object>> result;
}
