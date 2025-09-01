package com.slam.dao;

import com.slam.vo.Profile;

public interface ProfileDAO {
    
    // 사용자 ID로 프로필 조회
    Profile selectProfileByUserId(String userId);
    
    // 프로필 등록
    int insertProfile(Profile profile);
    
    // 프로필 수정
    int updateProfile(Profile profile);
    
    // 프로필 삭제
    int deleteProfile(String userId);
    
    // 프로필 ID로 조회
    Profile selectProfileById(Long profileId);
}