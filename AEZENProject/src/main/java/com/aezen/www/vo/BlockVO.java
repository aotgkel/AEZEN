package com.aezen.www.vo;

import java.time.LocalDateTime;

/*
 * Ŭ���� ��� : ���� ���� (block ���̺�)
 */
public class BlockVO {
    private String blockerId; // ������ ��û�� ����� ID (Primary Key �Ϻ�)
    private String blockedId; // ������ ���� ����� ID (Primary Key �Ϻ�)
    private String blockedAt; // ���� ���� �Ͻ�

    // =============================
    // Getters
    // =============================

    public String getBlockerId() {
        return blockerId;
    }

    public String getBlockedId() {
        return blockedId;
    }

    public String getBlockedAt() {
        return blockedAt;
    }

    // =============================
    // Setters
    // =============================

    public void setBlockerId(String blockerId) {
        this.blockerId = blockerId;
    }

    public void setBlockedId(String blockedId) {
        this.blockedId = blockedId;
    }

    public void setBlockedAt(String blockedAt) {
        this.blockedAt = blockedAt;
    }
}