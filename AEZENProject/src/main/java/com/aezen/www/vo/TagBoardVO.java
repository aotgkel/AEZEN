package com.aezen.www.vo;

/*
 * Ŭ���� ��� : �Խñ�-�±� ���� ���� (tag_board ���̺�)
 */
public class TagBoardVO {
    private int boardNo; // �Խñ� ��ȣ (Primary Key �Ϻ�, Foreign Key)
    private int tagNo; // �±� ��ȣ (Primary Key �Ϻ�, Foreign Key)

    // =============================
    // Getters
    // =============================

    public int getBoardNo() {
        return boardNo;
    }

    public int getTagNo() {
        return tagNo;
    }

    // =============================
    // Setters
    // =============================

    public void setBoardNo(int boardNo) {
        this.boardNo = boardNo;
    }

    public void setTagNo(int tagNo) {
        this.tagNo = tagNo;
    }
}