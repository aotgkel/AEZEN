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

	// 공지사항 최신글 제목만 노출
	public String selectLatestNoticeTitle() {
        return session.selectOne(namespace + ".selectLatestNoticeTitle");
    }
	
	//게시글 리스트 불러오기
	public List<BoardVO> selectBoardList() {
		return session.selectList(namespace+".selectBoardList");
	}
}
