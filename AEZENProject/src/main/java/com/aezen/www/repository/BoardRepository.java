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

	// �������� �ֽű� ���� ����
	public String selectLatestNoticeTitle() {
        return session.selectOne(namespace + ".selectLatestNoticeTitle");
    }
	
	//�Խñ� ����Ʈ �ҷ�����
	public List<BoardVO> selectBoardList() {
		return session.selectList(namespace+".selectBoardList");
	}
	
	// �Խñ� ���� (BoardVO)
    public void insertBoard(BoardVO vo) {
        session.insert(namespace + ".insertBoard", vo);

    }

    // ���� ���� (FileVO)
    public void insertFile(FileVO file) {
        session.insert(namespace + ".insertFile", file);
    }
    
    //���� �ٿ�
    public FileVO selectFileByNo(int fileNo) {
        FileVO vo = session.selectOne(namespace + ".selectFileByNo", fileNo);
        return vo;
    }
    
    // �±� ��ȸ
    public TagVO selectTagByName(String tagName) {
        return session.selectOne(namespace + ".selectTagByName", tagName);
    }

    // �±� ����
    public void insertTag(TagVO tagVO) {
        session.insert(namespace + ".insertTag", tagVO);
    }

    // �Խñ�-�±� ���� ����
    public void insertTagBoard(TagBoardVO tb) {
        session.insert(namespace + ".insertTagBoard", tb);
    }
    
    // ���� �Խñ� ��ȸ (����/�����)
    public BoardVO selectBoardByNo(int boardNo) {
        return session.selectOne(namespace + ".selectBoardByNo", boardNo);
    }
    
    // �Խñ� ����
    public void updateBoard(BoardVO vo) {
        session.update(namespace + ".updateBoard", vo);
    }
    
	// �Խñ�-�±� ���� ���� ���� üũ
    public boolean isTagBoardExists(int boardNo, int tagNo) {
        Integer count = session.selectOne(namespace + ".countTagBoard", Map.of("boardNo", boardNo, "tagNo", tagNo));
        return count != null && count > 0;
    }
    
    // �Խñ� ���� ó��
    public void deleteBoard(int boardNo) {
        session.update(namespace + ".deleteBoard", boardNo);
    }
    
    // �Խñ� ��õ/����õ
    public void updateBoardVote(int boardNo, String action) {
        if ("like".equals(action)) {
            session.update(namespace + ".incrementLike", boardNo);
        } else if ("dislike".equals(action)) {
            session.update(namespace + ".incrementDislike", boardNo);
        }
    }

    // ��õ/����õ �� �ֽ� ī��Ʈ ��ȸ
    public int selectBoardVoteCount(int boardNo, String action) {
        if ("like".equals(action)) {
            return session.selectOne(namespace + ".selectLikeCount", boardNo);
        } else {
            return session.selectOne(namespace + ".selectDislikeCount", boardNo);
        }
    }
    
    // ī�װ��� �Խñ� ����Ʈ �ҷ�����
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
