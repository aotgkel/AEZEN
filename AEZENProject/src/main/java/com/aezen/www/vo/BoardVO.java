package com.aezen.www.vo;

import java.time.LocalDateTime;
import java.util.List;

/*
 * Ŭ���� ��� : �Խñ� ���� (board ���̺�)
 */
public class BoardVO {
    private int boardNo; // �Խñ� ��ȣ (Primary Key)
    private String id; // �ۼ��� ID
    private String boardTitle; // �Խñ� ����
    private String boardContent; // �Խñ� ����
    private Integer boardCategory; // �Խñ� ī�װ� (NULLable)
    private int hit; // ��ȸ��
    private int boardLike; // ���ƿ� ��
    private int boardDislike; // �Ⱦ�� ��
    private String boardCreatedAt; // �ۼ��Ͻ�
    private String boardUpdatedAt; // �����Ͻ�
	private boolean board_deleted;        // �ۻ�������
    private List<CommentAnswerVO> comments;  //��� ����Ʈ
    private List<FileVO> files;				 //���� ����Ʈ
    private List<TagVO> tags;				 //�±� ����Ʈ

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