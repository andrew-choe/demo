package com.example.demo.business.controller;

import com.example.demo.business.dto.ResponseDTO;
import com.example.demo.business.dto.UserDTO;
import com.example.demo.business.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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
            , @Nullable @RequestParam(value="userNm") String userNm) throws Exception {
        ResponseEntity<List<UserDTO>> response;
        UserDTO input = new UserDTO(userId, userNm);
        List<UserDTO> result = userService.selectUserList(input);
        ResponseDTO<UserDTO> responseDTO = new ResponseDTO<UserDTO>(HttpStatus.OK.toString(), "성공적으로 조회되었습니다.", result);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/user", produces = "application/json;charset=UTF-8")
    public ResponseEntity user(@RequestBody HashMap<String, String> input) throws Exception {
        UserDTO userDTO = new UserDTO(input.get("userId"), input.get("userPw"), input.get("userNm"));
        userService.insertUser(userDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    ResponseEntity duplicateKeyExceptionHandler(DuplicateKeyException e) {
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity exceptionHandler(Exception e) {
        return new ResponseEntity(HttpStatus.BAD_GATEWAY);
    }
}
