package com.aezen.www.vo;

import java.time.LocalDateTime;

/*
 * 클래스 기능 : 포인트 기록 정보 (point 테이블)
 */
public class PointVO {
    private int pointNo; // 포인트 기록 번호 (Primary Key)
    private int pointAmount; // 포인트 변동량 (+/- 값)
    private int pointType; // 포인트 변동 타입 (예: 1: 적립, 2: 사용, 3: 보너스)
    private String pointReason; // 포인트 변동 사유
    private LocalDateTime pointCreatedAt; // 포인트 기록 일시
    private String id; // 대상 사용자 ID
    private Integer boardNo; // 관련 게시글 번호 (NULL 허용)

    // =============================
    // Getters
    // =============================

    public int getPointNo() {
        return pointNo;
    }

    public int getPointAmount() {
        return pointAmount;
    }

    public int getPointType() {
        return pointType;
    }

    public String getPointReason() {
        return pointReason;
    }

    public LocalDateTime getPointCreatedAt() {
        return pointCreatedAt;
    }

    public String getId() {
        return id;
    }

    public Integer getBoardNo() {
        return boardNo;
    }

    // =============================
    // Setters
    // =============================

    public void setPointNo(int pointNo) {
        this.pointNo = pointNo;
    }

    public void setPointAmount(int pointAmount) {
        this.pointAmount = pointAmount;
    }

    public void setPointType(int pointType) {
        this.pointType = pointType;
    }

    public void setPointReason(String pointReason) {
        this.pointReason = pointReason;
    }

    public void setPointCreatedAt(LocalDateTime pointCreatedAt) {
        this.pointCreatedAt = pointCreatedAt;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBoardNo(Integer boardNo) {
        this.boardNo = boardNo;
    }
}