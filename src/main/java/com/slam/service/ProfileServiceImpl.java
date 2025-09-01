package com.slam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.slam.dao.ProfileDAO;
import com.slam.vo.Profile;

@Service
public class ProfileServiceImpl implements ProfileService {
    
    @Autowired
    private ProfileDAO profileDAO;
    
    @Override
    public Profile getProfileByUserId(String userId) {
        try {
            return profileDAO.selectProfileByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public boolean createProfile(Profile profile) {
        try {
            int result = profileDAO.insertProfile(profile);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean updateProfile(Profile profile) {
        try {
            int result = profileDAO.updateProfile(profile);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean deleteProfile(String userId) {
        try {
            int result = profileDAO.deleteProfile(userId);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public Profile getProfileById(Long profileId) {
        try {
            return profileDAO.selectProfileById(profileId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}