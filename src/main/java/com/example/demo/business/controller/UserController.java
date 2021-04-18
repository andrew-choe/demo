package com.example.demo.business.controller;

import com.example.demo.business.dto.UserDTO;
import com.example.demo.business.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/list", produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<UserDTO>> list(@RequestParam(value="userId") String userId
            , @Nullable @RequestParam(value="userNm") String userNm) {
        ResponseEntity<List<UserDTO>> response;

        try {
            UserDTO input = new UserDTO(userId, userNm);

            List<UserDTO> result = userService.selectUserList(input);
            response = new ResponseEntity(result, HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @PostMapping(value = "/user", produces = "application/json;charset=UTF-8")
    public ResponseEntity user(@RequestBody HashMap<String, String> input) {
        UserDTO userDTO = new UserDTO();
        ResponseEntity response = null;

        try {
            userDTO.setUserId(input.get("userId").toString());
            userDTO.setUserPw(input.get("userPw").toString());
            userDTO.setUserNm(input.get("userNm").toString());

            int resultCount = userService.insertUser(userDTO);

            if (resultCount > 0) {
                response = new ResponseEntity(HttpStatus.OK);
            } else {
                response = new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            response = new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return response;
    }
}
