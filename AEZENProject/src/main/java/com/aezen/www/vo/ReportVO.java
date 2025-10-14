package com.aezen.www.vo;

import java.time.LocalDateTime;

/*
 * Ŭ���� ��� : �Ű� ���� (report ���̺�)
 */
public class ReportVO {
    private int reportNo; // �Ű� ��ȣ (Primary Key)
    private String reportUserId; // �Ű��� ����� ID
    private int reportCategory; // �Ű� ī�װ� (��: 1: �弳, 2: ���� ��)
    private String reportContent; // �Ű� ����
    private int boardNo; // �Ű�� �Խñ� ��ȣ
    private LocalDateTime reportedAt; // �Ű� �Ͻ�
    private int reportStatus; // ó�� ���� (��: 0: ���, 1: ó�� ��, 2: �Ϸ�)
    private LocalDateTime reportUpdatedAt; // �Ű� ó�� ������Ʈ �Ͻ�

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