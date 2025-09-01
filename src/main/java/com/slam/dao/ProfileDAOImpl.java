package com.slam.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.slam.vo.Profile;

@Repository
public class ProfileDAOImpl implements ProfileDAO {
    
    @Autowired
    private SqlSession sqlSession;
    
    @Override
    public Profile selectProfileByUserId(String userId) {
        return sqlSession.selectOne("com.slam.dao.ProfileDAO.selectProfileByUserId", userId);
    }
    
    @Override
    public int insertProfile(Profile profile) {
        return sqlSession.insert("com.slam.dao.ProfileDAO.insertProfile", profile);
    }
    
    @Override
    public int updateProfile(Profile profile) {
        return sqlSession.update("com.slam.dao.ProfileDAO.updateProfile", profile);
    }
    
    @Override
    public int deleteProfile(String userId) {
        return sqlSession.delete("com.slam.dao.ProfileDAO.deleteProfile", userId);
    }
    
    @Override
    public Profile selectProfileById(Long profileId) {
        return sqlSession.selectOne("com.slam.dao.ProfileDAO.selectProfileById", profileId);
    }
}