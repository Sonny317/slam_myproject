package com.slam.vo;

public class Profile {
    private Long profileId;
    private String userId;
    private String affiliation;
    private String introduction;
    private String interests;
    private String languagesSpoken;
    private String languagesLearning;
    private String phoneNumber;
    private String major;
    private String nationality;
    
    // 기본 생성자
    public Profile() {}
    
    // Getter/Setter 메서드들
    public Long getProfileId() { return profileId; }
    public void setProfileId(Long profileId) { this.profileId = profileId; }
    
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    
    public String getAffiliation() { return affiliation; }
    public void setAffiliation(String affiliation) { this.affiliation = affiliation; }
    
    public String getIntroduction() { return introduction; }
    public void setIntroduction(String introduction) { this.introduction = introduction; }
    
    public String getInterests() { return interests; }
    public void setInterests(String interests) { this.interests = interests; }
    
    public String getLanguagesSpoken() { return languagesSpoken; }
    public void setLanguagesSpoken(String languagesSpoken) { this.languagesSpoken = languagesSpoken; }
    
    public String getLanguagesLearning() { return languagesLearning; }
    public void setLanguagesLearning(String languagesLearning) { this.languagesLearning = languagesLearning; }
    
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    
    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }
    
    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }
}