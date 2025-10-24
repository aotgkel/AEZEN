package com.aezen.www.vo;



/*
 * 클래스 기능 : 쪽지 정보 (message 테이블)
 */
public class MessageVO {
    private int messageNo; // 쪽지 번호 (Primary Key)
    private int roomNo; // 쪽지방 번호 (Foreign Key)
    private String senderId; // 발신자 ID
    private String receiverId; // 수신자 ID
    private String messageContent; // 쪽지 내용
    private String messageSentAt; // 전송 일시
    private String messageReceivedAt; // 수신 일시
    private String readAt; // 읽음 확인 일시 (NULL 허용)
    private boolean messageDeleted; // 삭제 여부 (boolean 타입, true: 삭제됨)

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

    // boolean 타입은 is 접두사를 사용합니다.
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
