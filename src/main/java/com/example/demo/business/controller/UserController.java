package com.example.demo.business.controller;

import com.example.demo.business.constants.Constants;
import com.example.demo.business.dto.ResponseDTO;
import com.example.demo.business.dto.UserDTO;
import com.example.demo.business.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "user", description = "사용자 API")
@RequestMapping("/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "사용자 목록 조회", response = ResponseDTO.class, responseContainer = "LIST")
    @GetMapping(value = "/list", produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<UserDTO>> list(
              @ApiParam(name = "userId", type = "String", value = "사용자ID", required = false)
              @Nullable @RequestParam(value="userId") String userId
            , @ApiParam(name = "userNm", type = "String", value = "사용자명", required = false)
              @Nullable @RequestParam(value="userNm") String userNm) throws Exception {
        UserDTO input = new UserDTO(userId, userNm);
        List<UserDTO> result = userService.selectUserList(input);
        ResponseDTO<UserDTO> responseDTO = new ResponseDTO<>(HttpStatus.OK.toString(), Constants.RESULT_MSG_SUCCESS_SEARCH, result);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }

    @ApiOperation(value = "사용자 등록", response = ResponseDTO.class, responseContainer = "LIST")
    @PostMapping(value = "/user", produces = "application/json;charset=UTF-8")
    public ResponseEntity user(@ApiParam(name = "userInfo", value = "사용자정보", required = true)
                               @RequestBody UserDTO userInfo) throws Exception {
        UserDTO userDTO = new UserDTO(userInfo.getUserId(), userInfo.getUserPw(), userInfo.getUserNm());
        userService.insertUser(userDTO);
        ResponseDTO responseDTO = new ResponseDTO<>(HttpStatus.CREATED.toString(), Constants.RESULT_MSG_SUCCESS);
        return new ResponseEntity(responseDTO, HttpStatus.CREATED);
    }
}
