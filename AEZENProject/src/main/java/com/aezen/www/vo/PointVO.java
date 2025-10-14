package com.aezen.www.vo;

import java.time.LocalDateTime;

/*
 * Ŭ���� ��� : ����Ʈ ��� ���� (point ���̺�)
 */
public class PointVO {
    private int pointNo; // ����Ʈ ��� ��ȣ (Primary Key)
    private int pointAmount; // ����Ʈ ������ (+/- ��)
    private int pointType; // ����Ʈ ���� Ÿ�� (��: 1: ����, 2: ���, 3: ���ʽ�)
    private String pointReason; // ����Ʈ ���� ����
    private LocalDateTime pointCreatedAt; // ����Ʈ ��� �Ͻ�
    private String id; // ��� ����� ID
    private Integer boardNo; // ���� �Խñ� ��ȣ (NULL ���)

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