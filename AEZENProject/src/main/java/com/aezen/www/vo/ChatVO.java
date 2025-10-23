package com.aezen.www.vo;

import java.time.LocalDateTime;

/*
 * Ŭ���� ��� : ���� ä�� ���� (chat ���̺�)
 */
public class ChatVO {
    private int chatNo; // ä�� �޽��� ��ȣ (Primary Key)
    private String id; // �߽��� ID
    private String chatContent; // ä�� ����
    private String chatCreatedAt; // ���� �Ͻ�

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