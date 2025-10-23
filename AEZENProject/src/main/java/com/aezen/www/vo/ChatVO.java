package com.aezen.www.vo;

import java.time.LocalDateTime;

/*
 * 클래스 기능 : 공용 채팅 정보 (chat 테이블)
 */
public class ChatVO {
    private int chatNo; // 채팅 메시지 번호 (Primary Key)
    private String id; // 발신자 ID
    private String chatContent; // 채팅 내용
    private String chatCreatedAt; // 전송 일시

    // =============================
    // Getters
    // =============================

    public int getChatNo() {
        return chatNo;
    }

    public String getId() {
        return id;
    }

    public String getChatContent() {
        return chatContent;
    }

    public String getChatCreatedAt() {
        return chatCreatedAt;
    }

    // =============================
    // Setters
    // =============================

    public void setChatNo(int chatNo) {
        this.chatNo = chatNo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setChatContent(String chatContent) {
        this.chatContent = chatContent;
    }

    public void setChatCreatedAt(String chatCreatedAt) {
        this.chatCreatedAt = chatCreatedAt;
    }
}