package com.aezen.www.repository;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.aezen.www.vo.*;

@Repository
public class UserRepository {
	@Autowired
	private SqlSession session;

	private static final String namespace = "com.aezen.www.header";

	// �α���
	public UserVO login(String id, String pw)
	{
		UserVO param = new UserVO();
		param.setId(id.trim());
		param.setPassword(pw.trim()); // ���� ����

		UserVO vo = session.selectOne(namespace + ".login", param);
		return vo;
	}
	
	// �α��� �ð� ������Ʈ
    public void updateLastLoginAt(String id) {
        session.update(namespace + ".updateLastLoginAt", id);
    }
}
