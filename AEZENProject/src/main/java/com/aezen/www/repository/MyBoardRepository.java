package com.aezen.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aezen.www.vo.BoardVO;

/**
 * ? �� �Խñ�(MyBoard) ���� ���� Repository
 * - �� �� ���, ����, ���� ���� ���� ����
 */
@Repository
public class MyBoardRepository {

    @Autowired
    private SqlSession session;

    private static final String namespace = "com.aezen.www.myboard";

    // �� �� ���
    public List<BoardVO> selectMyBoardListByUserId(String userId) {
        return session.selectList(namespace + ".selectMyBoardListByUserId", userId);
    }

    // �Խñ� ����
    public int deleteMyBoard(int boardNo) {
        return session.update(namespace + ".deleteMyBoard", boardNo);
    }

    // �Խñ� ����
    public int updateMyBoard(BoardVO board) {
        return session.update(namespace + ".updateMyBoard", board);
    }
}
