package com.aezen.www.vo;

import java.time.LocalDateTime;

/*
 * Ŭ���� ��� : �±� ���� (tag ���̺�)
 */
public class TagVO {
    private Integer tagNo; // �±� ��ȣ (Primary Key)
    private String tagName; // �±� �̸� (Unique)
    private String createdAt; // ���� �Ͻ�

    // =============================
    // Getters
    // =============================

    public Integer getTagNo() {
        return tagNo;
    }

    public String getTagName() {
        return tagName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    // =============================
    // Setters
    // =============================

    public void setTagNo(Integer tagNo) {
        this.tagNo = tagNo;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}