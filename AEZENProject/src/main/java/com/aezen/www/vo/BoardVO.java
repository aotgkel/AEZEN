package com.aezen.www.vo;

import java.time.LocalDateTime;

/*
 * 클래스 기능 : 게시글 정보 (board 테이블)
 */
public class BoardVO {
    private int boardNo; // 게시글 번호 (Primary Key)
    private String id; // 작성자 ID
    private String boardTitle; // 게시글 제목
    private String boardContent; // 게시글 내용
    private Integer boardCategory; // 게시글 카테고리 (NULLable)
    private int hit; // 조회수
    private int boardLike; // 좋아요 수
    private int boardDislike; // 싫어요 수
    private LocalDateTime boardCreatedAt; // 작성일시
    private LocalDateTime boardUpdatedAt; // 수정일시
    private boolean board_deleted;        // 글삭제여부

    // =============================
    // Getters
    // =============================

	public int getBoardNo() {
        return boardNo;
    }

    public String getId() {
        return id;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public String getBoardContent() {
        return boardContent;
    }

    public Integer getBoardCategory() {
        return boardCategory;
    }

    public int getHit() {
        return hit;
    }

    public int getBoardLike() {
        return boardLike;
    }

    public int getBoardDislike() {
        return boardDislike;
    }

    public LocalDateTime getBoardCreatedAt() {
        return boardCreatedAt;
    }

    public LocalDateTime getBoardUpdatedAt() {
        return boardUpdatedAt;
    }
    public boolean isBoard_deleted() {
		return board_deleted;
	}

    // =============================
    // Setters
    // =============================

    public void setBoardNo(int boardNo) {
        this.boardNo = boardNo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    public void setBoardContent(String boardContent) {
        this.boardContent = boardContent;
    }

    public void setBoardCategory(Integer boardCategory) {
        this.boardCategory = boardCategory;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public void setBoardLike(int boardLike) {
        this.boardLike = boardLike;
    }

    public void setBoardDislike(int boardDislike) {
        this.boardDislike = boardDislike;
    }

    public void setBoardCreatedAt(LocalDateTime boardCreatedAt) {
        this.boardCreatedAt = boardCreatedAt;
    }

    public void setBoardUpdatedAt(LocalDateTime boardUpdatedAt) {
        this.boardUpdatedAt = boardUpdatedAt;
    }
    public void setBoard_deleted(boolean board_deleted) {
		this.board_deleted = board_deleted;
	}
}