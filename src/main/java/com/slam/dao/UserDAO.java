package com.slam.dao;

import java.util.List;
import com.slam.vo.User;

public interface UserDAO {
    
    // 전체 사용자 목록 조회
    List<User> selectAllUsers();
    
    // 사용자 ID로 조회
    User selectUserById(String userId);
    
    // 이메일로 사용자 조회
    User selectUserByEmail(String email);
    
    // 사용자 등록
    int insertUser(User user);
    
    // 사용자 정보 수정
    int updateUser(User user);
    
    // 사용자 삭제
    int deleteUser(String userId);
    
    // 로그인 검증
    User loginUser(String email, String password);
    
    // 사용자 상태 변경
    int updateUserStatus(String userId, String status);
}