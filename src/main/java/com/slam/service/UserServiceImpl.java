package com.slam.service;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.slam.dao.UserDAO;
import com.slam.vo.User;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserDAO userDAO;
    
    @Override
    public List<User> getAllUsers() {
        return userDAO.selectAllUsers();
    }
    
    @Override
    public User getUserById(String userId) {
        return userDAO.selectUserById(userId);
    }
    
    @Override
    public User getUserByEmail(String email) {
        return userDAO.selectUserByEmail(email);
    }
    
    @Override
    public boolean registerUser(User user) {
        try {
            // 기본값 설정
            if (user.getRole() == null || user.getRole().isEmpty()) {
                user.setRole("Member");
            }
            if (user.getStatus() == null || user.getStatus().isEmpty()) {
                user.setStatus("pre-member");
            }
            if (user.getCreatedAt() == null) {
                user.setCreatedAt(new Date());
            }
            
            int result = userDAO.insertUser(user);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean updateUser(User user) {
        try {
            int result = userDAO.updateUser(user);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean deleteUser(String userId) {
        try {
            int result = userDAO.deleteUser(userId);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public User loginUser(String email, String password) {
        try {
            return userDAO.loginUser(email, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public boolean updateUserStatus(String userId, String status) {
        try {
            int result = userDAO.updateUserStatus(userId, status);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean isEmailDuplicate(String email) {
        try {
            User user = userDAO.selectUserByEmail(email);
            return user != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean isUserIdDuplicate(String userId) {
        try {
            User user = userDAO.selectUserById(userId);
            return user != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}