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

	// 로그인
	public UserVO login(String id, String pw)
	{
		UserVO param = new UserVO();
		param.setId(id.trim());
		param.setPassword(pw.trim()); // 공백 제거

		UserVO vo = session.selectOne(namespace + ".login", param);
		return vo;
	}
	
	// 로그인 시간 업데이트
    public void updateLastLoginAt(String id) {
        session.update(namespace + ".updateLastLoginAt", id);
    }
}
