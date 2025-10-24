package com.aezen.www.repository;

import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.aezen.www.vo.FileVO;
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
	
	// 게시글 저장 (BoardVO)
    public void insertBoard(BoardVO vo) {
        session.insert(namespace + ".insertBoard", vo);

    }

    // 파일 저장 (FileVO)
    public void insertFile(FileVO file) {
        session.insert(namespace + ".insertFile", file);
    }
    
    //파일 다운
    public FileVO selectFileByNo(int fileNo) {
        FileVO vo = session.selectOne(namespace + ".selectFileByNo", fileNo);
        return vo;
    }
    
    // 태그 조회
    public TagVO selectTagByName(String tagName) {
        return session.selectOne(namespace + ".selectTagByName", tagName);
    }

    // 태그 저장
    public void insertTag(TagVO tagVO) {
        session.insert(namespace + ".insertTag", tagVO);
    }

    // 게시글-태그 연결 저장
    public void insertTagBoard(TagBoardVO tb) {
        session.insert(namespace + ".insertTagBoard", tb);
    }
    
    // 단일 게시글 조회 (수정/보기용)
    public BoardVO selectBoardByNo(int boardNo) {
        return session.selectOne(namespace + ".selectBoardByNo", boardNo);
    }
    
    // 게시글 수정
    public void updateBoard(BoardVO vo) {
        session.update(namespace + ".updateBoard", vo);
    }
    
	// 게시글-태그 연결 존재 여부 체크
    public boolean isTagBoardExists(int boardNo, int tagNo) {
        Integer count = session.selectOne(namespace + ".countTagBoard", Map.of("boardNo", boardNo, "tagNo", tagNo));
        return count != null && count > 0;
    }
    
    // 게시글 삭제 처리
    public void deleteBoard(int boardNo) {
        session.update(namespace + ".deleteBoard", boardNo);
    }
    
    // 게시글 추천/비추천
    public void updateBoardVote(int boardNo, String action) {
        if ("like".equals(action)) {
            session.update(namespace + ".incrementLike", boardNo);
        } else if ("dislike".equals(action)) {
            session.update(namespace + ".incrementDislike", boardNo);
        }
    }

    // 추천/비추천 후 최신 카운트 조회
    public int selectBoardVoteCount(int boardNo, String action) {
        if ("like".equals(action)) {
            return session.selectOne(namespace + ".selectLikeCount", boardNo);
        } else {
            return session.selectOne(namespace + ".selectDislikeCount", boardNo);
        }
    }
    
    // 카테고리별 게시글 리스트 불러오기
    public List<BoardVO> selectBoardByCategory(int category, String sort) {
        return session.selectList(namespace + ".selectBoardByCategory",
                                  Map.of("category", category, "sort", sort));
    }
    
    public List<BoardVO> selectBoardByCategory(int category, String sort, String id) {
    	
    	Map<String, Object> map = new LinkedHashMap<String, Object>();
    	map.put("category", category);
    	map.put("sort", sort);
    	map.put("id", id);
    	
        return session.selectList(namespace + ".selectBoardByCategory",map);
    }
    
    
}
