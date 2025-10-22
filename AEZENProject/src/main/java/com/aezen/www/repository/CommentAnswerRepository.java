package com.aezen.www.repository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.aezen.www.vo.FileVO;
import com.aezen.www.vo.*;

@Repository
public class CommentAnswerRepository {
	@Autowired
	private SqlSession session;

	private static final String namespace = "com.aezen.www.commentAnswer";

	public void insertComment(CommentAnswerVO comment) {
        session.insert(namespace + ".insertComment", comment);
    }

    public void insertAnswer(CommentAnswerVO answer) {
        session.insert(namespace + ".insertAnswer", answer);
    }

    public CommentAnswerVO selectById(int id) {
        return session.selectOne(namespace + ".selectById", id);
    }

    public void update(CommentAnswerVO vo) {
        session.update(namespace + ".update", vo);
    }

    public void delete(int id) {
        session.delete(namespace + ".delete", id); // 즉시 삭제
    }
    
    // 댓글/답변 추천/비추천
    public void updateCommentVote(int commentNo, String action) {
        if ("like".equals(action)) {
            session.update(namespace + ".incrementLike", commentNo);
        } else if ("dislike".equals(action)) {
            session.update(namespace + ".incrementDislike", commentNo);
        }
    }

    // 추천/비추천 후 최신 카운트 조회
    public int selectCommentVoteCount(int commentNo, String action) {
        if ("like".equals(action)) {
            return session.selectOne(namespace + ".selectLikeCount", commentNo);
        } else {
            return session.selectOne(namespace + ".selectDislikeCount", commentNo);
        }
    }
    
}
