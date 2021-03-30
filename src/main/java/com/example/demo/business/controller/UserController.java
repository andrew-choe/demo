package com.example.demo.business.controller;

import com.example.demo.business.dto.UserDTO;
import com.example.demo.business.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/list", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<List<UserDTO>> list(@Nullable @RequestBody HashMap<String, Object> input) throws Exception {
        // TODO: return값을 ResponseDTO로 변경 필요
        List<UserDTO> result = userService.selectUserList(input);
        ResponseEntity<List<UserDTO>> rtn = new ResponseEntity(result, HttpStatus.OK);
        //ResponseDTO response = new ResponseDTO(HttpStatus.OK.getReasonPhrase(), result);
        return rtn;
    }
}
