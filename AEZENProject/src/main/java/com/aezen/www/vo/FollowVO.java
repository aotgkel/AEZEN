package com.aezen.www.vo;



/*
 * Ŭ���� ��� : �ȷο� ���� (follow ���̺�)
 */
public class FollowVO {
    private String followingId; // �ȷο츦 ��û�� ����� ID (Primary Key �Ϻ�)
    private String followedId; // �ȷο츦 ���� ����� ID (Primary Key �Ϻ�)
    private String followStartedAt; // �ȷο� ���� �Ͻ�

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