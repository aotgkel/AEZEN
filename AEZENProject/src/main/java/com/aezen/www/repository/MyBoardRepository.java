package com.aezen.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aezen.www.vo.BoardVO;

/**
 * ? 내 게시글(MyBoard) 관련 전용 Repository
 * - 내 글 목록, 삭제, 수정 관련 쿼리 관리
 */
@Repository
public class MyBoardRepository {

    @Autowired
    private SqlSession session;

    private static final String namespace = "com.aezen.www.myboard";

    // 내 글 목록
    public List<BoardVO> selectMyBoardListByUserId(String userId) {
        return session.selectList(namespace + ".selectMyBoardListByUserId", userId);
    }

    // 게시글 삭제
    public int deleteMyBoard(int boardNo) {
        return session.update(namespace + ".deleteMyBoard", boardNo);
    }

    // 게시글 수정
    public int updateMyBoard(BoardVO board) {
        return session.update(namespace + ".updateMyBoard", board);
    }
}
