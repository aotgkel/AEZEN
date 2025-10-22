package com.aezen.www.vo;

import java.time.LocalDateTime;

/*
 * 클래스 기능 : 태그 정보 (tag 테이블)
 */
public class TagVO {
    private Integer tagNo; // 태그 번호 (Primary Key)
    private String tagName; // 태그 이름 (Unique)
    private String createdAt; // 생성 일시

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