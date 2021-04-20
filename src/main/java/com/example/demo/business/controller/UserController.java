package com.example.demo.business.controller;

import com.example.demo.business.constants.Constants;
import com.example.demo.business.dto.ResponseDTO;
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
            , @Nullable @RequestParam(value="userNm") String userNm) throws Exception {
        UserDTO input = new UserDTO(userId, userNm);
        List<UserDTO> result = userService.selectUserList(input);
        ResponseDTO<UserDTO> responseDTO = new ResponseDTO<>(HttpStatus.OK.toString(), Constants.RESULT_MSG_SUCCESS_SEARCH, result);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/user", produces = "application/json;charset=UTF-8")
    public ResponseEntity user(@RequestBody HashMap<String, String> input) throws Exception {
        UserDTO userDTO = new UserDTO(input.get("userId"), input.get("userPw"), input.get("userNm"));
        userService.insertUser(userDTO);
        ResponseDTO responseDTO = new ResponseDTO<>(HttpStatus.CREATED.toString(), Constants.RESULT_MSG_SUCCESS);
        return new ResponseEntity(responseDTO, HttpStatus.CREATED);
    }
}
