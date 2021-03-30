package com.example.demo.business.dao;

import com.example.demo.business.dto.UserDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class UserDAO {
    private final SqlSession sqlSession;
    private final String queryPass = "business.dao.UserDAO.";

    public UserDAO(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<UserDTO> selectUserList(HashMap<String, Object> input) throws Exception {
        return sqlSession.selectList(queryPass.concat("selectUserList"), input);
    }
}
