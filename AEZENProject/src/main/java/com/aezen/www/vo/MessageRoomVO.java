package com.aezen.www.vo;

import java.time.LocalDateTime;

/*
 * Ŭ���� ��� : ������ ���� (message_room ���̺�)
 */
public class MessageRoomVO {
    private int roomNo; // ������ ��ȣ (Primary Key)
    private String userIdA; // ����� A ID (�� ����� �� ID�� ���� ������ ���� ���)
    private String userIdB; // ����� B ID
    private LocalDateTime messageRoomCreatedAt; // ������ ���� �Ͻ�
    private LocalDateTime lastMessageAt; // ������ ���� ���� �Ͻ�

    // =============================
    // Getters
    // =============================

    public int getRoomNo() {
        return roomNo;
    }

    public String getUserIdA() {
        return userIdA;
    }

    public String getUserIdB() {
        return userIdB;
    }

    public LocalDateTime getMessageRoomCreatedAt() {
        return messageRoomCreatedAt;
    }

    public LocalDateTime getLastMessageAt() {
        return lastMessageAt;
    }

    // =============================
    // Setters
    // =============================

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public void setUserIdA(String userIdA) {
        this.userIdA = userIdA;
    }

    public void setUserIdB(String userIdB) {
        this.userIdB = userIdB;
    }

    public void setMessageRoomCreatedAt(LocalDateTime messageRoomCreatedAt) {
        this.messageRoomCreatedAt = messageRoomCreatedAt;
    }

    public void setLastMessageAt(LocalDateTime lastMessageAt) {
        this.lastMessageAt = lastMessageAt;
    }
}