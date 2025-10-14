package com.aezen.www.vo;

import java.time.LocalDateTime;

/*
 * Ŭ���� ��� : ���� ���� (message ���̺�)
 */
public class MessageVO {
    private int messageNo; // ���� ��ȣ (Primary Key)
    private int roomNo; // ������ ��ȣ (Foreign Key)
    private String senderId; // �߽��� ID
    private String receiverId; // ������ ID
    private String messageContent; // ���� ����
    private LocalDateTime messageSentAt; // ���� �Ͻ�
    private LocalDateTime messageReceivedAt; // ���� �Ͻ�
    private LocalDateTime readAt; // ���� Ȯ�� �Ͻ� (NULL ���)
    private boolean messageDeleted; // ���� ���� (boolean Ÿ��, true: ������)

    // =============================
    // Getters
    // =============================

    public int getMessageNo() {
        return messageNo;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public LocalDateTime getMessageSentAt() {
        return messageSentAt;
    }

    public LocalDateTime getMessageReceivedAt() {
        return messageReceivedAt;
    }

    public LocalDateTime getReadAt() {
        return readAt;
    }

    // boolean Ÿ���� is ���λ縦 ����մϴ�.
    public boolean isMessageDeleted() {
        return messageDeleted;
    }

    // =============================
    // Setters
    // =============================

    public void setMessageNo(int messageNo) {
        this.messageNo = messageNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public void setMessageSentAt(LocalDateTime messageSentAt) {
        this.messageSentAt = messageSentAt;
    }

    public void setMessageReceivedAt(LocalDateTime messageReceivedAt) {
        this.messageReceivedAt = messageReceivedAt;
    }

    public void setReadAt(LocalDateTime readAt) {
        this.readAt = readAt;
    }

    public void setMessageDeleted(boolean messageDeleted) {
        this.messageDeleted = messageDeleted;
    }
}
