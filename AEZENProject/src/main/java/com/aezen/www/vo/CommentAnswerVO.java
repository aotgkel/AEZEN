package com.aezen.www.vo;

import java.time.LocalDateTime;

/*
 * 클래스 기능 : 댓글/답변 정보 (comment_answer 테이블)
 */
public class CommentAnswerVO {
    private int commentAnswerNo; // 댓글/답변 번호 (Primary Key)
    private int boardNo; // 관련 게시글 번호 (Foreign Key)
    private String id; // 작성자 ID
    private String commentAnswerContent; // 댓글/답변 내용
    private int commentLikeCount; // 좋아요 수
    private int commentDislikeCount; // 싫어요 수
    private LocalDateTime commentAnswerAt; // 작성일시
    private LocalDateTime commentAnswerUpdatedAt; // 수정일시
    private int commentAnswerType; // 타입 (0: 댓글, 1: 답변)
    private boolean answerAccepted; // 답변 채택 여부 (boolean 타입)

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

    public LocalDateTime getCommentAnswerAt() {
        return commentAnswerAt;
    }

    public LocalDateTime getCommentAnswerUpdatedAt() {
        return commentAnswerUpdatedAt;
    }

    public int getCommentAnswerType() {
        return commentAnswerType;
    }

    // boolean 타입은 is 접두사를 사용합니다.
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

    public void setCommentAnswerAt(LocalDateTime commentAnswerAt) {
        this.commentAnswerAt = commentAnswerAt;
    }

    public void setCommentAnswerUpdatedAt(LocalDateTime commentAnswerUpdatedAt) {
        this.commentAnswerUpdatedAt = commentAnswerUpdatedAt;
    }

    public void setCommentAnswerType(int commentAnswerType) {
        this.commentAnswerType = commentAnswerType;
    }

    public void setAnswerAccepted(boolean answerAccepted) {
        this.answerAccepted = answerAccepted;
    }
}