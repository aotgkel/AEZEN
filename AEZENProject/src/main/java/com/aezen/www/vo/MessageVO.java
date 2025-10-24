package com.aezen.www.vo;



/*
 * Ŭ���� ��� : ���� ���� (message ���̺�)
 */
public class MessageVO {
    private int messageNo; // ���� ��ȣ (Primary Key)
    private int roomNo; // ������ ��ȣ (Foreign Key)
    private String senderId; // �߽��� ID
    private String receiverId; // ������ ID
    private String messageContent; // ���� ����
    private String messageSentAt; // ���� �Ͻ�
    private String messageReceivedAt; // ���� �Ͻ�
    private String readAt; // ���� Ȯ�� �Ͻ� (NULL ���)
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

    public String getMessageSentAt() {
        return messageSentAt;
    }

    public String getMessageReceivedAt() {
        return messageReceivedAt;
    }

    public String getReadAt() {
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

    public void setMessageSentAt(String messageSentAt) {
        this.messageSentAt = messageSentAt;
    }

    public void setMessageReceivedAt(String messageReceivedAt) {
        this.messageReceivedAt = messageReceivedAt;
    }

    public void setReadAt(String readAt) {
        this.readAt = readAt;
    }

    public void setMessageDeleted(boolean messageDeleted) {
        this.messageDeleted = messageDeleted;
    }

	public void setReceiverId(int roomNo2, String senderId2) {
		// TODO Auto-generated method stub
		
	}
}
