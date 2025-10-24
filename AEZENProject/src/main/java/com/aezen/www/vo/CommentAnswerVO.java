package com.aezen.www.vo;



/*
 * Ŭ���� ��� : ���/�亯 ���� (comment_answer ���̺�)
 */
public class CommentAnswerVO {
    private int commentAnswerNo; // ���/�亯 ��ȣ (Primary Key)
    private int boardNo; // ���� �Խñ� ��ȣ (Foreign Key)
    private String id; // �ۼ��� ID
    private String commentAnswerContent; // ���/�亯 ����
    private int commentLikeCount; // ���ƿ� ��
    private int commentDislikeCount; // �Ⱦ�� ��
    private String commentAnswerAt; // �ۼ��Ͻ�
    private String commentAnswerUpdatedAt; // �����Ͻ�
    private int commentAnswerType; // Ÿ�� (0: ���, 1: �亯)
    private boolean answerAccepted; // �亯 ä�� ���� (boolean Ÿ��)

    // =============================
    // Getters
    // =============================

    public int getCommentAnswerNo() {
        return commentAnswerNo;
    }

    public int getBoardNo() {
        return boardNo;
    }

    public String getId() {
        return id;
    }

    public String getCommentAnswerContent() {
        return commentAnswerContent;
    }

    public int getCommentLikeCount() {
        return commentLikeCount;
    }

    public int getCommentDislikeCount() {
        return commentDislikeCount;
    }

    public String getCommentAnswerAt() {
        return commentAnswerAt;
    }

    public String getCommentAnswerUpdatedAt() {
        return commentAnswerUpdatedAt;
    }

    public int getCommentAnswerType() {
        return commentAnswerType;
    }

    // boolean Ÿ���� is ���λ縦 ����մϴ�.
    public boolean isAnswerAccepted() {
        return answerAccepted;
    }

    // =============================
    // Setters
    // =============================

    public void setCommentAnswerNo(int commentAnswerNo) {
        this.commentAnswerNo = commentAnswerNo;
    }

    public void setBoardNo(int boardNo) {
        this.boardNo = boardNo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCommentAnswerContent(String commentAnswerContent) {
        this.commentAnswerContent = commentAnswerContent;
    }

    public void setCommentLikeCount(int commentLikeCount) {
        this.commentLikeCount = commentLikeCount;
    }

    public void setCommentDislikeCount(int commentDislikeCount) {
        this.commentDislikeCount = commentDislikeCount;
    }

    public void setCommentAnswerAt(String commentAnswerAt) {
        this.commentAnswerAt = commentAnswerAt;
    }

    public void setCommentAnswerUpdatedAt(String commentAnswerUpdatedAt) {
        this.commentAnswerUpdatedAt = commentAnswerUpdatedAt;
    }

    public void setCommentAnswerType(int commentAnswerType) {
        this.commentAnswerType = commentAnswerType;
    }

    public void setAnswerAccepted(boolean answerAccepted) {
        this.answerAccepted = answerAccepted;
    }
}