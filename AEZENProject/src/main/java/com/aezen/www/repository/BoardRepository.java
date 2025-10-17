package com.aezen.www.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.aezen.www.vo.*;

@Repository
public class BoardRepository {
	@Autowired
	private SqlSession session;

	private static final String namespace = "com.aezen.www.board";

	// �������� �ֽű� ���� ����
	public String selectLatestNoticeTitle() {
        return session.selectOne(namespace + ".selectLatestNoticeTitle");
    }
	
	//�Խñ� ����Ʈ �ҷ�����
	public List<BoardVO> selectBoardList() {
		return session.selectList(namespace+".selectBoardList");
	}
}
