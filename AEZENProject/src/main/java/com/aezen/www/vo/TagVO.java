package com.aezen.www.vo;

import java.time.LocalDateTime;

/*
 * Ŭ���� ��� : �±� ���� (tag ���̺�)
 */
public class TagVO {
    private int tagNo; // �±� ��ȣ (Primary Key)
    private String tagName; // �±� �̸� (Unique)
    private LocalDateTime createdAt; // ���� �Ͻ�

    // =============================
    // Getters
    // =============================

    public int getTagNo() {
        return tagNo;
    }

    public String getTagName() {
        return tagName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // =============================
    // Setters
    // =============================

    public void setTagNo(int tagNo) {
        this.tagNo = tagNo;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}