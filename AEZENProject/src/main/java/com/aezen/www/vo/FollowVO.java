package com.aezen.www.vo;



/*
 * 클래스 기능 : 팔로우 정보 (follow 테이블)
 */
public class FollowVO {
    private String followingId; // 팔로우를 요청한 사용자 ID (Primary Key 일부)
    private String followedId; // 팔로우를 당한 사용자 ID (Primary Key 일부)
    private String followStartedAt; // 팔로우 시작 일시

    // =============================
    // Getters
    // =============================

    public String getFollowingId() {
        return followingId;
    }

    public String getFollowedId() {
        return followedId;
    }

    public String getFollowStartedAt() {
        return followStartedAt;
    }

    // =============================
    // Setters
    // =============================

    public void setFollowingId(String followingId) {
        this.followingId = followingId;
    }

    public void setFollowedId(String followedId) {
        this.followedId = followedId;
    }

    public void setFollowStartedAt(String followStartedAt) {
        this.followStartedAt = followStartedAt;
    }
}