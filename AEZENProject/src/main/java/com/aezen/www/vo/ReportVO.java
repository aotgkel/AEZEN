package com.aezen.www.vo;



/*
 * Ŭ���� ��� : �Ű� ���� (report ���̺�)
 */
public class ReportVO {
    private int reportNo; // �Ű� ��ȣ (Primary Key)
    private String reportUserId; // �Ű��� ����� ID
    private int reportCategory; // �Ű� ī�װ� (��: 1: �弳, 2: ���� ��)
    private String reportContent; // �Ű� ����
    private int boardNo; // �Ű�� �Խñ� ��ȣ
    private String reportedAt; // �Ű� �Ͻ�
    private int reportStatus; // ó�� ���� (��: 0: ���, 1: ó�� ��, 2: �Ϸ�)
    private String reportUpdatedAt; // �Ű� ó�� ������Ʈ �Ͻ�

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

    public String getReportedAt() {
        return reportedAt;
    }

    public int getReportStatus() {
        return reportStatus;
    }

    public String getReportUpdatedAt() {
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

    public void setReportedAt(String reportedAt) {
        this.reportedAt = reportedAt;
    }

    public void setReportStatus(int reportStatus) {
        this.reportStatus = reportStatus;
    }

    public void setReportUpdatedAt(String reportUpdatedAt) {
        this.reportUpdatedAt = reportUpdatedAt;
    }
}