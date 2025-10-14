package com.aezen.www.vo;

/*
 * 클래스 기능 : 게시글-태그 연결 정보 (tag_board 테이블)
 */
public class TagBoardVO {
    private int boardNo; // 게시글 번호 (Primary Key 일부, Foreign Key)
    private int tagNo; // 태그 번호 (Primary Key 일부, Foreign Key)

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