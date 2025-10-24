package com.aezen.www.vo;



/*
 * Ŭ���� ��� : �˸� ���� (alert ���̺�)
 */
public class AlertVO {
    private int alertNo; // �˸� ��ȣ (Primary Key)
    private String id; // �˸��� �޴� ��� ID
    private String alertId; // �˸��� �߻���Ų ID (��: ��� �ۼ���)
    private int alertType; // �˸� Ÿ�� (��: 1: ���, 2: ���ƿ�, 3: �ȷο� ��)
    private String alertContent; // �˸� ����
    private String alertedAt; // �˸� �߻� �Ͻ�
    private Integer targetBoardNo; // ���� �Խñ� ��ȣ (NULL ���)
    private Integer targetCommentAnswerNo; // ���� ���/�亯 ��ȣ (NULL ���)

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