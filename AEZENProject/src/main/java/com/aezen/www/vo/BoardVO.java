package com.aezen.www.vo;

import java.time.LocalDateTime;
import java.util.List;

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
    private String boardCreatedAt; // 작성일시
    private String boardUpdatedAt; // 수정일시
	private boolean board_deleted;        // 글삭제여부
    private List<CommentAnswerVO> comments;  //댓글 리스트
    private List<FileVO> files;				 //파일 리스트
    private List<TagVO> tags;				 //태그 리스트

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

    public String getBoardCreatedAt() {
        return boardCreatedAt;
    }

    public String getBoardUpdatedAt() {
        return boardUpdatedAt;
    }
    public boolean isBoard_deleted() {
		return board_deleted;
	}
    public List<CommentAnswerVO> getComments() {
		return comments;
	}
    public List<FileVO> getFiles() {
		return files;
	}
    public List<TagVO> getTags() {
		return tags;
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

    public void setBoardCreatedAt(String boardCreatedAt) {
        this.boardCreatedAt = boardCreatedAt;
    }

    public void setBoardUpdatedAt(String boardUpdatedAt) {
        this.boardUpdatedAt = boardUpdatedAt;
    }
    
    public void setBoard_deleted(boolean board_deleted) {
		this.board_deleted = board_deleted;
	}

	public void setComments(List<CommentAnswerVO> comments) {
		this.comments = comments;
	}

	public void setFiles(List<FileVO> files) {
		this.files = files;
	}

	public void setTags(List<TagVO> tags) {
		this.tags = tags;
	}
    
}