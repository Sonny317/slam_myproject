package com.slam.service;

import java.util.List;
import com.slam.vo.User;

public interface UserService {
    
    // 전체 사용자 목록 조회
    List<User> getAllUsers();
    
    // 사용자 ID로 조회
    User getUserById(String userId);
    
    // 이메일로 사용자 조회
    User getUserByEmail(String email);
    
    // 사용자 등록
    boolean registerUser(User user);
    
    // 사용자 정보 수정
    boolean updateUser(User user);
    
    // 사용자 삭제
    boolean deleteUser(String userId);
    
    // 로그인 처리
    User loginUser(String email, String password);
    
    // 사용자 상태 변경
    boolean updateUserStatus(String userId, String status);
    
    // 이메일 중복 확인
    boolean isEmailDuplicate(String email);
    
    // 사용자 ID 중복 확인
    boolean isUserIdDuplicate(String userId);
}