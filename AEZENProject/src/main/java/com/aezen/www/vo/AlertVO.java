package com.aezen.www.vo;



/*
 * 클래스 기능 : 알림 정보 (alert 테이블)
 */
public class AlertVO {
    private int alertNo; // 알림 번호 (Primary Key)
    private String id; // 알림을 받는 사람 ID
    private String alertId; // 알림을 발생시킨 ID (예: 댓글 작성자)
    private int alertType; // 알림 타입 (예: 1: 댓글, 2: 좋아요, 3: 팔로우 등)
    private String alertContent; // 알림 내용
    private String alertedAt; // 알림 발생 일시
    private Integer targetBoardNo; // 관련 게시글 번호 (NULL 허용)
    private Integer targetCommentAnswerNo; // 관련 댓글/답변 번호 (NULL 허용)

    // =============================
    // Getters
    // =============================

    public int getAlertNo() {
        return alertNo;
    }

    public String getId() {
        return id;
    }

    public String getAlertId() {
        return alertId;
    }

    public int getAlertType() {
        return alertType;
    }

    public String getAlertContent() {
        return alertContent;
    }

    public String getAlertedAt() {
        return alertedAt;
    }

    public Integer getTargetBoardNo() {
        return targetBoardNo;
    }

    public Integer getTargetCommentAnswerNo() {
        return targetCommentAnswerNo;
    }

    // =============================
    // Setters
    // =============================

    public void setAlertNo(int alertNo) {
        this.alertNo = alertNo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAlertId(String alertId) {
        this.alertId = alertId;
    }

    public void setAlertType(int alertType) {
        this.alertType = alertType;
    }

    public void setAlertContent(String alertContent) {
        this.alertContent = alertContent;
    }

    public void setAlertedAt(String alertedAt) {
        this.alertedAt = alertedAt;
    }

    public void setTargetBoardNo(Integer targetBoardNo) {
        this.targetBoardNo = targetBoardNo;
    }

    public void setTargetCommentAnswerNo(Integer targetCommentAnswerNo) {
        this.targetCommentAnswerNo = targetCommentAnswerNo;
    }
}