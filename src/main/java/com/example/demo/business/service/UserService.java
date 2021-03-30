package com.example.demo.business.service;

import com.example.demo.business.dao.UserDAO;
import com.example.demo.business.dto.UserDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class UserService {
    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<UserDTO> selectUserList(HashMap<String, Object> input) throws Exception {
        return userDAO.selectUserList(input);
    }
}
