package com.slam.service;

import com.slam.vo.Profile;

public interface ProfileService {
    
    // 사용자 ID로 프로필 조회
    Profile getProfileByUserId(String userId);
    
    // 프로필 등록
    boolean createProfile(Profile profile);
    
    // 프로필 수정
    boolean updateProfile(Profile profile);
    
    // 프로필 삭제
    boolean deleteProfile(String userId);
    
    // 프로필 ID로 조회
    Profile getProfileById(Long profileId);
}