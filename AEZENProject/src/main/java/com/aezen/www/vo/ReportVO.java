package com.aezen.www.vo;

import java.time.LocalDateTime;

/*
 * 클래스 기능 : 신고 정보 (report 테이블)
 */
public class ReportVO {
    private int reportNo; // 신고 번호 (Primary Key)
    private String reportUserId; // 신고한 사용자 ID
    private int reportCategory; // 신고 카테고리 (예: 1: 욕설, 2: 광고 등)
    private String reportContent; // 신고 내용
    private int boardNo; // 신고된 게시글 번호
    private LocalDateTime reportedAt; // 신고 일시
    private int reportStatus; // 처리 상태 (예: 0: 대기, 1: 처리 중, 2: 완료)
    private LocalDateTime reportUpdatedAt; // 신고 처리 업데이트 일시

    // =============================
    // Getters
    // =============================

    public int getReportNo() {
        return reportNo;
    }

    public String getReportUserId() {
        return reportUserId;
    }

    public int getReportCategory() {
        return reportCategory;
    }

    public String getReportContent() {
        return reportContent;
    }

    public int getBoardNo() {
        return boardNo;
    }

    public LocalDateTime getReportedAt() {
        return reportedAt;
    }

    public int getReportStatus() {
        return reportStatus;
    }

    public LocalDateTime getReportUpdatedAt() {
        return reportUpdatedAt;
    }

    // =============================
    // Setters
    // =============================

    public void setReportNo(int reportNo) {
        this.reportNo = reportNo;
    }

    public void setReportUserId(String reportUserId) {
        this.reportUserId = reportUserId;
    }

    public void setReportCategory(int reportCategory) {
        this.reportCategory = reportCategory;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    public void setBoardNo(int boardNo) {
        this.boardNo = boardNo;
    }

    public void setReportedAt(LocalDateTime reportedAt) {
        this.reportedAt = reportedAt;
    }

    public void setReportStatus(int reportStatus) {
        this.reportStatus = reportStatus;
    }

    public void setReportUpdatedAt(LocalDateTime reportUpdatedAt) {
        this.reportUpdatedAt = reportUpdatedAt;
    }
}