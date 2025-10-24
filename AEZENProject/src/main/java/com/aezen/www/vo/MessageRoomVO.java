package com.aezen.www.vo;

import java.time.LocalDateTime;

/*
 * 클래스 기능 : 쪽지방 정보 (message_room 테이블)
 */
public class MessageRoomVO {
    private int roomNo; // 쪽지방 번호 (Primary Key)
    private String userIdA; // 사용자 A ID (두 사용자 중 ID가 사전 순으로 빠른 사람)
    private String userIdB; // 사용자 B ID
    private String messageRoomCreatedAt; // 쪽지방 생성 일시
    private String lastMessageAt; // 마지막 쪽지 전송 일시

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

    public String getMessageRoomCreatedAt() {
        return messageRoomCreatedAt;
    }

    public String getLastMessageAt() {
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

    public void setMessageRoomCreatedAt(String messageRoomCreatedAt) {
        this.messageRoomCreatedAt = messageRoomCreatedAt;
    }

    public void setLastMessageAt(String lastMessageAt) {
        this.lastMessageAt = lastMessageAt;
    }
}