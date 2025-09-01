package com.slam.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.slam.vo.User;

@Repository
public class UserDAOImpl implements UserDAO {
    
    @Autowired
    private SqlSession sqlSession;
    
    @Override
    public List<User> selectAllUsers() {
        return sqlSession.selectList("com.slam.dao.UserDAO.selectAllUsers");
    }
    
    @Override
    public User selectUserById(String userId) {
        return sqlSession.selectOne("com.slam.dao.UserDAO.selectUserById", userId);
    }
    
    @Override
    public User selectUserByEmail(String email) {
        return sqlSession.selectOne("com.slam.dao.UserDAO.selectUserByEmail", email);
    }
    
    @Override
    public int insertUser(User user) {
        return sqlSession.insert("com.slam.dao.UserDAO.insertUser", user);
    }
    
    @Override
    public int updateUser(User user) {
        return sqlSession.update("com.slam.dao.UserDAO.updateUser", user);
    }
    
    @Override
    public int deleteUser(String userId) {
        return sqlSession.delete("com.slam.dao.UserDAO.deleteUser", userId);
    }
    
    @Override
    public User loginUser(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        return sqlSession.selectOne("com.slam.dao.UserDAO.loginUser", user);
    }
    
    @Override
    public int updateUserStatus(String userId, String status) {
        User user = new User();
        user.setUserId(userId);
        user.setStatus(status);
        return sqlSession.update("com.slam.dao.UserDAO.updateUserStatus", user);
    }
}