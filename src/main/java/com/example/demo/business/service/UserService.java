package com.example.demo.business.service;

import com.example.demo.business.dao.UserDAO;
import com.example.demo.business.dto.FileDTO;
import com.example.demo.business.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    public List<UserDTO> selectUserList(UserDTO input) throws Exception {
        return userDAO.selectUserList(input);
    }

    public int insertUser(UserDTO input) throws Exception {
        return userDAO.insertUser(input);
    }

    public List<FileDTO> selectUserFileList(UserDTO input) throws Exception {
        return userDAO.selectUserFileList(input);
    }
}
