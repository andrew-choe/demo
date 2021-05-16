package com.example.demo.business.dao;

import com.example.demo.business.dto.FileDTO;
import com.example.demo.business.dto.UserDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO {
    private SqlSession sqlSession;
    private final String queryPath = "business.dao.UserDAO.";

    public UserDAO(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<UserDTO> selectUserList(UserDTO input) throws Exception {
        return sqlSession.selectList(queryPath.concat("selectUserList"), input);
    }

    public int insertUser(UserDTO input) throws Exception {
        return sqlSession.insert(queryPath.concat("insertUser"), input);
    }

    public List<FileDTO> selectUserFileList(UserDTO input) throws Exception {
        return sqlSession.selectList(queryPath.concat("selectUserFileList"), input);
    }
}
